//package com.coolcupp.order_service.service.grpc;
//
//import com.coolcupp.inventoryService.grpc.InventoryServiceGrpc;
//import com.coolcupp.inventoryService.grpc.ProductRequest;
//import com.coolcupp.inventoryService.grpc.ProductResponse;
//import com.coolcupp.order_service.dto.grpcDTO.ProductAvailabilityResponseDTO;
//import net.devh.boot.grpc.client.inject.GrpcClient;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InventoryServiceGrpcClient {
//
//    @GrpcClient("InventoryServiceGrpcClient")
//    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryServiceBlockingStub;
//
//    public ProductAvailabilityResponseDTO checkAvailability(Integer productId) {
//        ProductRequest request = ProductRequest.newBuilder().setId(productId).build();
//
//        ProductResponse response = inventoryServiceBlockingStub.checkAvailability(request);
//
//        return new ProductAvailabilityResponseDTO(
//                response.getId(),
//                response.getName(),
//                response.getQuantity(),
//                response.getPrice(),
//                response.getDiscountPercent()
//        );
//    }
//}
