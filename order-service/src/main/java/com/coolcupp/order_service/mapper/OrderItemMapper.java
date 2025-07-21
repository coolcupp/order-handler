package com.coolcupp.order_service.mapper;

import com.coolcupp.inventoryService.grpc.ProductRequestItem;
import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemMapper {

    // List<OrderItemDTO> -> List<ProductRequestItem>
    public List<ProductRequestItem> toProductRequestItemListFromOrderItemDTOList(
            List<OrderItemDTO> orderItemDTOList) {
        if (orderItemDTOList == null || orderItemDTOList.isEmpty()) {
            return null;
        }

        return orderItemDTOList.stream()
                .map(orderItemDTO -> ProductRequestItem.newBuilder()
                        .setId(orderItemDTO.getProductId())
                        .setRequestedQuantity(orderItemDTO.getQuantityToOrder())
                        .build()
                )
                .toList();
    }

}
