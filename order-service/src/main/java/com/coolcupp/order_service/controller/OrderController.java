package com.coolcupp.order_service.controller;

import com.coolcupp.inventoryService.grpc.ProductResponse;
import com.coolcupp.order_service.model.ProductResponseDTO;
import com.coolcupp.order_service.service.InventoryServiceGrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class OrderController {

    private final InventoryServiceGrpcClient inventoryServiceGrpcClient;

    public OrderController(InventoryServiceGrpcClient inventoryServiceGrpcClient) {
        this.inventoryServiceGrpcClient = inventoryServiceGrpcClient;
    }

    @GetMapping("order/{productId}")
    public ProductResponseDTO checkAvailability(@PathVariable("productId") Integer productId) {
        return inventoryServiceGrpcClient.checkAvailability(productId);
    }

}
