package com.coolcupp.order_service.service.grpc;


import com.coolcupp.inventoryService.grpc.InventoryServiceGrpc;
import com.coolcupp.inventoryService.grpc.ProductRequestItem;
import com.coolcupp.inventoryService.grpc.ProductTotalRequest;
import com.coolcupp.inventoryService.grpc.ProductTotalResponse;
import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import com.coolcupp.order_service.mapper.OrderItemMapper;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InventoryServiceGRPCClient {

    @GrpcClient("inventorygrpcclient")
    private InventoryServiceGrpc.InventoryServiceBlockingStub blockingStub;
    private final OrderItemMapper orderItemMapper;

    public InventoryServiceGRPCClient(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public ProductTotalResponse CheckAvailability(List<OrderItemDTO> orderItemDTOList) {
        log.info("Start checking availability, sending request to InventoryService....");
        // build request
        // todo mapper (OrderItemMapper)
//        List<ProductRequestItem> productRequestItemList = orderItemDTOList.stream()
//                .map(orderItemDTO -> ProductRequestItem.newBuilder()
//                        .setId(orderItemDTO.getProductId())
//                        .setRequestedQuantity(orderItemDTO.getQuantityToOrder())
//                        .build())
//                .toList();
        List<ProductRequestItem> productRequestItemList = orderItemMapper
                .toProductRequestItemListFromOrderItemDTOList(orderItemDTOList);

        ProductTotalRequest request = ProductTotalRequest.newBuilder()
                .addAllItems(productRequestItemList)
                .build();

        return blockingStub.checkAvailability(request);
    }

}
