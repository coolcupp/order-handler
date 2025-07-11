package com.coolcupp.order_service.service;


import com.coolcupp.common_lib.event.KafkaCreateOrderEvent;
import com.coolcupp.common_lib.event.KafkaCreateOrderEventItem;
import com.coolcupp.inventoryService.grpc.ProductResponseItem;
import com.coolcupp.inventoryService.grpc.ProductTotalResponse;
import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;

import com.coolcupp.order_service.service.grpc.InventoryServiceGRPCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, KafkaCreateOrderEvent> kafkaTemplate;
    private final InventoryServiceGRPCClient inventoryServiceGRPCClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);


    public OrderServiceImpl(KafkaTemplate<String, KafkaCreateOrderEvent> kafkaTemplate,
                            InventoryServiceGRPCClient inventoryServiceGRPCClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryServiceGRPCClient = inventoryServiceGRPCClient;
    }


    public ResponseEntity<?> createNewOrder(CreateOrderDTO createOrderDTO) {
        // PARSING CreateOrderDTO
        List<OrderItemDTO> orderItems = createOrderDTO.getOrderItems();
        UUID orderId = createOrderDTO.getOrderId();
        Long userId = createOrderDTO.getUserId();
        LOGGER.info("Order ID: {} || Parsing createOrderDTO...", orderId);

        // GRPC CHECK AVAILABILITY -> 2 lists: availability, unavailability
        LOGGER.info("Order ID: {} || checking availability items...", orderId);
        ProductTotalResponse productTotalResponse = inventoryServiceGRPCClient.CheckAvailability(orderItems);


        // if items in storage not enough (unavail. list is not empty)
        if (!productTotalResponse.getUnavailabilityItemsList().isEmpty()) {
            LOGGER.error("Order ID: {} || not ready to creating order: not enough items in storage", orderId);
            // todo THROW CUSTOM EXCEPTION -> not enough items in storage
            return new ResponseEntity<>("not enough items", HttpStatus.NOT_ACCEPTABLE);
        }

        // CREATING KAFKA EVENT
        if (productTotalResponse.getUnavailabilityItemsList().isEmpty()) {
            LOGGER.info("Order ID: {} || ready to creting order: enough items in storage", orderId);
            LOGGER.info("Order ID: {} || Start creating kafka message...", orderId);
            KafkaCreateOrderEvent kafkaCreateOrderEvent = new KafkaCreateOrderEvent();
            kafkaCreateOrderEvent.setOrderId(orderId);
            kafkaCreateOrderEvent.setUserId(userId);

            // create kafka message items
            List<KafkaCreateOrderEventItem> kafkaCreateOrderEventItems = new ArrayList<>();
            List<ProductResponseItem> grpcAvailabilityItems = productTotalResponse.getAvailabilityItemsList();

            for (ProductResponseItem grpcAvailabilityItem : grpcAvailabilityItems) {
                Long productId = grpcAvailabilityItem.getId();
                Integer quantityToOrder = grpcAvailabilityItem.getRequestedQuantity();
                BigDecimal price = new BigDecimal(grpcAvailabilityItem.getPrice());
                BigDecimal discountPercent = new BigDecimal(grpcAvailabilityItem.getDiscountPercent());

                // calculating total price
                BigDecimal totalPrice = price
                        .multiply(BigDecimal.valueOf(quantityToOrder)) // цена * количество
                        .multiply(BigDecimal.ONE.subtract(discountPercent.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)));
                System.out.println("id: " + productId + " || total price: " + totalPrice);

                kafkaCreateOrderEventItems.add(new KafkaCreateOrderEventItem(
                        productId,
                        quantityToOrder,
                        price,
                        discountPercent,
                        totalPrice
                ));
            }
            kafkaCreateOrderEvent.setEventItemList(kafkaCreateOrderEventItems);
            LOGGER.info("Order ID: {} || Kafka message created.", orderId);


            // SENT TO KAFKA (BY PRODUCER)
            LOGGER.info("Order ID: {} || Sending order message to kafka...", orderId);
            kafkaTemplate.send("ordersTopic", orderId.toString(), kafkaCreateOrderEvent);

            return new ResponseEntity<>("order created", HttpStatus.CREATED);
        }

        return null;
    }
}
