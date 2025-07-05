package com.coolcupp.order_service.service;

import com.coolcupp.order_service.dto.OrderItemDTO;
import com.coolcupp.order_service.dto.OrderRequestDTO;
import com.coolcupp.order_service.dto.ProductResponseGrpcDTO;
import com.coolcupp.order_service.grpc.InventoryServiceGrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final InventoryServiceGrpcClient inventoryServiceGrpcClient;

    public OrderServiceImpl(InventoryServiceGrpcClient inventoryServiceGrpcClient) {
        this.inventoryServiceGrpcClient = inventoryServiceGrpcClient;
    }


    public ResponseEntity<?> createNewOrder(OrderRequestDTO orderRequestDTO) {
        // parsing orderItemDto
        List<OrderItemDTO> orderItems = orderRequestDTO.getOrderItems();

        for (OrderItemDTO orderItem : orderItems) {
            // check availability for product
            ProductResponseGrpcDTO productResponseGrpcDTO =
                    inventoryServiceGrpcClient.checkAvailability(orderItem.getProductId());

            // WORKING!!!!
            if (orderItem.getQuantityToOrder() > productResponseGrpcDTO.getQuantity()) {
                return new ResponseEntity<>("bad quantity of product: " + productResponseGrpcDTO.getName(),
                        HttpStatus.NOT_ACCEPTABLE);
            }
        }

        return new ResponseEntity<>("availability is good, ready to create an order", HttpStatus.OK);
    }
}
