package com.coolcupp.order_service.service.grpc;


import com.coolcupp.inventoryService.grpc.InventoryServiceGrpc;
import com.coolcupp.inventoryService.grpc.ProductRequestItem;
import com.coolcupp.inventoryService.grpc.ProductTotalRequest;
import com.coolcupp.inventoryService.grpc.ProductTotalResponse;
import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceGRPCClient {

    @GrpcClient("inventorygrpcclient")
    private InventoryServiceGrpc.InventoryServiceBlockingStub blockingStub;

    public ProductTotalResponse CheckAvailability(List<OrderItemDTO> orderItemDTOList) {
        // build request
        List<ProductRequestItem> productRequestItemList = orderItemDTOList.stream()
                .map(orderItemDTO -> ProductRequestItem.newBuilder()
                        .setId(orderItemDTO.getProductId())
                        .setRequestedQuantity(orderItemDTO.getQuantityToOrder())
                        .build())
                .toList();

        ProductTotalRequest request = ProductTotalRequest.newBuilder()
                .addAllItems(productRequestItemList)
                .build();

        return blockingStub.checkAvailability(request);
    }

}
