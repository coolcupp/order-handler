//package com.coolcupp.order_service.service.grpc;
//
//
//import com.coolcupp.inventoryService.grpc.InventoryServiceGrpc;
//import com.coolcupp.inventoryService.grpc.ProductTotalResponse;
//import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
//import net.devh.boot.grpc.client.inject.GrpcClient;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class InventoryServiceGRPCClient {
//
//    @GrpcClient("InventoryServiceGRPCClient")
//    private InventoryServiceGrpc.InventoryServiceBlockingStub blockingStub;
//
//    public ProductTotalResponse CheckAvailability(List<OrderItemDTO> orderItemDTOList) {
//        return null;
//        // todo grpc client
//    }
//
//}
