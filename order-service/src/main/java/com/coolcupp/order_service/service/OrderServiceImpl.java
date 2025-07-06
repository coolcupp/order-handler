package com.coolcupp.order_service.service;

import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;
import com.coolcupp.order_service.dto.grpcDTO.ProductAvailabilityResponseDTO;
import com.coolcupp.order_service.event.CreateOrderEvent;
import com.coolcupp.order_service.event.OrderItemEvent;
import com.coolcupp.order_service.service.grpc.InventoryServiceGrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final InventoryServiceGrpcClient inventoryServiceGrpcClient;
    private final KafkaTemplate<String, CreateOrderEvent> kafkaTemplate;


    public OrderServiceImpl(InventoryServiceGrpcClient inventoryServiceGrpcClient, KafkaTemplate<String, CreateOrderEvent> kafkaTemplate) {
        this.inventoryServiceGrpcClient = inventoryServiceGrpcClient;
        this.kafkaTemplate = kafkaTemplate;
    }


    public ResponseEntity<?> createNewOrder(CreateOrderDTO createOrderDTO) {
        // parsing orderItemDto
        List<OrderItemDTO> orderItems = createOrderDTO.getOrderItems();

        // items in kafka message
        List<OrderItemEvent> orderItemEvents = new ArrayList<>();

        for (OrderItemDTO orderItem : orderItems) {

            // get info about product from grpc
            ProductAvailabilityResponseDTO productResponseGrpcDTO =
                    inventoryServiceGrpcClient.checkAvailability(orderItem.getProductId());

            // check availability of product
            if (orderItem.getQuantityToOrder() > productResponseGrpcDTO.getQuantity()) {
                return new ResponseEntity<>("bad quantity of product: " + productResponseGrpcDTO.getName(),
                        HttpStatus.NOT_ACCEPTABLE);
            }

            // creating a kafka message item and add in list
            BigDecimal orderItemEventPrice = new BigDecimal(productResponseGrpcDTO.getPrice());
            BigDecimal orderItemEventDiscountPercent = new BigDecimal(productResponseGrpcDTO.getDiscountPercent());
            Integer quantityToOrder = orderItem.getQuantityToOrder();

            BigDecimal discount = orderItemEventPrice
                    .multiply(orderItemEventDiscountPercent)
                    .divide(BigDecimal.valueOf(100));
            BigDecimal finalItemPrice = orderItemEventPrice.subtract(discount);
            BigDecimal totalPrice = finalItemPrice.multiply(BigDecimal.valueOf(quantityToOrder));

            OrderItemEvent orderItemEvent = new OrderItemEvent(
                    orderItem.getProductId(),
                    quantityToOrder,
                    orderItemEventPrice,
                    orderItemEventDiscountPercent,
                    totalPrice
            );

            orderItemEvents.add(orderItemEvent);
        }

        CreateOrderEvent createOrderEvent = new CreateOrderEvent();
        createOrderEvent.setOrderItems(orderItemEvents);
        createOrderEvent.setOrderId(UUID.randomUUID().hashCode()); // generating random
        createOrderEvent.setUserId(111); // TEST todo

        kafkaTemplate.send("ordersTopic", createOrderEvent.getOrderId().toString(), createOrderEvent);
        System.out.println("sent to kafka: " + createOrderEvent);

        return new ResponseEntity<>("order sent to kafka", HttpStatus.OK);
    }
}
