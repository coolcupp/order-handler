package com.coolcupp.order_service.service;


import com.coolcupp.common_lib.event.KafkaCreateOrderEvent;
import com.coolcupp.common_lib.event.KafkaCreateOrderEventItem;
import com.coolcupp.common_lib.exception_handling.exception.NotEnoughItemsInStorageException;
import com.coolcupp.inventoryService.grpc.ProductResponseItem;
import com.coolcupp.inventoryService.grpc.ProductTotalResponse;
import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;

import com.coolcupp.order_service.service.grpc.InventoryServiceGRPCClient;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, KafkaCreateOrderEvent> kafkaTemplate;
    private final InventoryServiceGRPCClient inventoryServiceGRPCClient;


    public OrderServiceImpl(KafkaTemplate<String, KafkaCreateOrderEvent> kafkaTemplate,
                            InventoryServiceGRPCClient inventoryServiceGRPCClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryServiceGRPCClient = inventoryServiceGRPCClient;
    }


    public ResponseEntity<UUID> createNewOrder(CreateOrderDTO createOrderDTO) {
        // PARSING CreateOrderDTO
        List<OrderItemDTO> orderItems = createOrderDTO.getOrderItems();
        UUID orderId = createOrderDTO.getOrderId();
        Long userId = createOrderDTO.getUserId();
        log.info("Start creating new order with ID: {}", orderId);

        // GRPC CHECK AVAILABILITY -> 2 lists: availability, unavailability
        log.info("Order ID: {} || checking availability items...", orderId);
        ProductTotalResponse productTotalResponse = inventoryServiceGRPCClient.CheckAvailability(orderItems);

        // 2 item lists from grpc
        List<ProductResponseItem> grpcAvailabilityItems = productTotalResponse.getAvailabilityItemsList();
        List<ProductResponseItem> grpcUnavailabilityItems = productTotalResponse.getUnavailabilityItemsList();

        // CREATING KAFKA EVENT
        if (grpcUnavailabilityItems.isEmpty() && !grpcAvailabilityItems.isEmpty()) {
            log.info("Order ID: {} || ready to creting order: enough items in storage", orderId);
            log.info("Order ID: {} || Start creating kafka message...", orderId);
            KafkaCreateOrderEvent kafkaCreateOrderEvent = new KafkaCreateOrderEvent();
            kafkaCreateOrderEvent.setOrderId(orderId);
            kafkaCreateOrderEvent.setUserId(userId);

            // create kafka message items
            List<KafkaCreateOrderEventItem> kafkaCreateOrderEventItems = new ArrayList<>();

            for (ProductResponseItem grpcAvailabilityItem : grpcAvailabilityItems) {
                Long productId = grpcAvailabilityItem.getId();
                Integer quantityToOrder = grpcAvailabilityItem.getRequestedQuantity();
                BigDecimal price = new BigDecimal(grpcAvailabilityItem.getPrice());
                BigDecimal discountPercent = new BigDecimal(grpcAvailabilityItem.getDiscountPercent());

                // calculating total price
                BigDecimal totalPrice = price
                        .multiply(BigDecimal.valueOf(quantityToOrder)) // цена * количество
                        .multiply(BigDecimal.ONE.subtract(discountPercent.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)));

                kafkaCreateOrderEventItems.add(new KafkaCreateOrderEventItem(
                        productId,
                        quantityToOrder,
                        price,
                        discountPercent,
                        totalPrice
                ));
            }
            kafkaCreateOrderEvent.setEventItemList(kafkaCreateOrderEventItems);
            log.info("Order ID: {} || Kafka message created.", orderId);


            // SENT TO KAFKA (BY PRODUCER)
            log.info("Order ID: {} || Sending order message to kafka...", orderId);
            kafkaTemplate.send("ordersTopic", orderId.toString(), kafkaCreateOrderEvent);

            return new ResponseEntity<>(orderId ,HttpStatus.OK);
        } else {
            log.error("Order ID: {} || not ready to creating order: not enough items in storage", orderId);
            throw new NotEnoughItemsInStorageException(
                    "Not enough items in storage for order with ID: " + orderId
            );
        }
    }
}
