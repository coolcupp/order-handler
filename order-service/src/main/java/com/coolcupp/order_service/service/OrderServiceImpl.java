package com.coolcupp.order_service.service;

import com.coolcupp.common_lib.event.CreateOrderEvent;

import com.coolcupp.inventoryService.grpc.ProductTotalResponse;
import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;

//import com.coolcupp.order_service.service.grpc.InventoryServiceGRPCClient;
import com.coolcupp.order_service.service.grpc.InventoryServiceGRPCClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, CreateOrderEvent> kafkaTemplate;
    private final InventoryServiceGRPCClient inventoryServiceGRPCClient;


    public OrderServiceImpl(KafkaTemplate<String, CreateOrderEvent> kafkaTemplate,
                            InventoryServiceGRPCClient inventoryServiceGRPCClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryServiceGRPCClient = inventoryServiceGRPCClient;
    }


    public ResponseEntity<?> createNewOrder(CreateOrderDTO createOrderDTO) {
        // PARSING CreateOrderDTO
        List<OrderItemDTO> orderItems = createOrderDTO.getOrderItems();
        UUID orderId = createOrderDTO.getOrderId();
        Long userId = createOrderDTO.getUserId();
        // todo logging request accepted

        // GRPC CHECK AVAILABILITY -> 2 lists: availability, unavailability
        ProductTotalResponse productTotalResponse = inventoryServiceGRPCClient.CheckAvailability(orderItems);
        // todo logging availability is checked

        // if items in storage not enough
        if (!productTotalResponse.getUnavailabilityItemsList().isEmpty()) {
            System.out.println("NOT READY TO CREATING ORDER: NOT ENOUGH ITEMS");
            // todo throw custom exception -> not enough items in storage
        }

        if (productTotalResponse.getUnavailabilityItemsList().isEmpty()) {
            // todo sent to kafka
            // todo logging sent to kafka
        }

        return null;
    }
}
