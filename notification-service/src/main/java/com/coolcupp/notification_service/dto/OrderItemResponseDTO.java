package com.coolcupp.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discountPercent;
    private BigDecimal totalItemPrice;
}
