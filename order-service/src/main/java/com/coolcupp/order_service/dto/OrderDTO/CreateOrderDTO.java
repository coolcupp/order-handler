package com.coolcupp.order_service.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    private UUID orderId;
    private Long userId;
    private List<OrderItemDTO> orderItems;
}
