package com.coolcupp.notification_service.mapper;

import com.coolcupp.common_lib.event.KafkaCreateOrderEventItem;
import com.coolcupp.notification_service.dto.OrderItemResponseDTO;
import com.coolcupp.notification_service.model.Order;
import com.coolcupp.notification_service.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapper {

    // List<OrderItem> -> List<OrderItemResponseDTO>
    public List<OrderItemResponseDTO> toOrderItemResponseDTOListFromOrderItemList(List<OrderItem> orderItemList) {
        if (orderItemList == null || orderItemList.isEmpty()) {
            return null;
        }
        List<OrderItemResponseDTO> orderItemResponseDTOList = new ArrayList<>();

        orderItemResponseDTOList = orderItemList.stream()
                .map(orderItem -> new OrderItemResponseDTO(
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice(),
                        orderItem.getDiscountPercent(),
                        orderItem.getTotalItemPrice()
                ))
                .toList();

        return orderItemResponseDTOList;
    }


    public List<OrderItem> toOrderItemListFromKafkaEventItemList(Order order,
            List<KafkaCreateOrderEventItem> kafkaCreateOrderEventItems) {

        return kafkaCreateOrderEventItems.stream()
                .map(kafkaCreateOrderEventItem -> new OrderItem(
                        order,
                        kafkaCreateOrderEventItem.getProductId(),
                        kafkaCreateOrderEventItem.getQuantityToOrder(),
                        kafkaCreateOrderEventItem.getPrice(),
                        kafkaCreateOrderEventItem.getDiscountPercent(),
                        kafkaCreateOrderEventItem.getTotalItemPrice()
                ))
                .toList();
    }

}
