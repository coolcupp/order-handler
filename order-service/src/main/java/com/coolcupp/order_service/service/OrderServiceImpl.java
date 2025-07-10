package com.coolcupp.order_service.service;


import com.coolcupp.common_lib.event.KafkaCreateOrderEvent;
import com.coolcupp.common_lib.event.KafkaCreateOrderEventItem;
import com.coolcupp.inventoryService.grpc.ProductResponseItem;
import com.coolcupp.inventoryService.grpc.ProductTotalResponse;
import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;

import com.coolcupp.order_service.service.grpc.InventoryServiceGRPCClient;
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
        // todo LOGGING request accepted

        // GRPC CHECK AVAILABILITY -> 2 lists: availability, unavailability
        ProductTotalResponse productTotalResponse = inventoryServiceGRPCClient.CheckAvailability(orderItems);
        // todo LOGGING availability is checked

        // if items in storage not enough (unavail. list is not empty)
        if (!productTotalResponse.getUnavailabilityItemsList().isEmpty()) {
            System.out.println("NOT READY TO CREATING ORDER: NOT ENOUGH ITEMS");
            // todo LOGGING ERROR
            // todo THROW CUSTOM EXCEPTION -> not enough items in storage
            return new ResponseEntity<>("not enough items", HttpStatus.NOT_ACCEPTABLE);
        }

        // CREATING KAFKA EVENT
        if (productTotalResponse.getUnavailabilityItemsList().isEmpty()) {

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

            // SENT TO KAFKA (BY PRODUCER)
            kafkaTemplate.send("ordersTopic", orderId.toString(), kafkaCreateOrderEvent);
            // todo logging sent to kafka

            System.out.println(kafkaCreateOrderEvent.getUserId());
            System.out.println(kafkaCreateOrderEvent.getOrderId());
            System.out.println(kafkaCreateOrderEvent.getEventItemList());
            return new ResponseEntity<>("order created", HttpStatus.CREATED);
        }

        return null;
    }
}
