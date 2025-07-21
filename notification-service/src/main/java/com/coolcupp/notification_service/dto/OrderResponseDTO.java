package com.coolcupp.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private UUID orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private List<OrderItemResponseDTO> orderItems;
}
