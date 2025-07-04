package com.coolcupp.order_service.service;

import com.coolcupp.inventoryService.grpc.InventoryServiceGrpc;
import com.coolcupp.inventoryService.grpc.ProductRequest;
import com.coolcupp.inventoryService.grpc.ProductResponse;
import com.coolcupp.order_service.model.ProductResponseDTO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceGrpcClient {

    @GrpcClient("InventoryServiceGrpcClient")
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryServiceBlockingStub;

    public ProductResponseDTO checkAvailability(Integer productId) {
        ProductRequest request = ProductRequest.newBuilder().setId(productId).build();

        ProductResponse response = inventoryServiceBlockingStub.checkAvailability(request);

        return new ProductResponseDTO(
                response.getId(),
                response.getName(),
                response.getQuantity(),
                response.getPrice(),
                response.getDiscountPercent()
        );


    }

}
