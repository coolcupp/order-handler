package com.coolcupp.notification_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @Column(name = "total_price")
    private BigDecimal totalItemPrice;

    public OrderItem(Order order, Long productId, Integer quantity, BigDecimal price,
                     BigDecimal discountPercent, BigDecimal totalItemPrice) {
        this.id = id;
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = discountPercent;
        this.totalItemPrice = totalItemPrice;
    }
}
