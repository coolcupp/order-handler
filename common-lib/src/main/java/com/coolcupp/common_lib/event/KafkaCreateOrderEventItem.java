package com.coolcupp.common_lib.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaCreateOrderEventItem {
    private Long productId;
    private Integer quantityToOrder;
    private BigDecimal price;
    private BigDecimal discountPercent;
    private BigDecimal totalItemPrice;
}
