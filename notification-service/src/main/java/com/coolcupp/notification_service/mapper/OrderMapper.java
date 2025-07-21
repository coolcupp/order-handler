package com.coolcupp.notification_service.mapper;

import com.coolcupp.notification_service.dto.OrderItemResponseDTO;
import com.coolcupp.notification_service.dto.OrderResponseDTO;
import com.coolcupp.notification_service.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    // List<Order> -> List<OrderResponseDTO>
    public List<OrderResponseDTO> toOrderResponseDTOListFromOrderList(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return null;
        }
        return orders.stream()
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getUserId(),
                        order.getTotalPrice(),
                        order.getOrderItems().stream()
                                .map(orderItem -> new OrderItemResponseDTO(
                                        orderItem.getProductId(),
                                        orderItem.getQuantity(),
                                        orderItem.getPrice(),
                                        orderItem.getDiscountPercent(),
                                        orderItem.getTotalItemPrice()
                                )).toList()
                ))
                .toList();
    }
}
