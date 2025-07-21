package com.coolcupp.inventory_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "discount_percent", nullable = false)
    private BigDecimal discountPercent;

    public Product(String name, Integer quantity, BigDecimal price, BigDecimal discountPercent) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = discountPercent;
    }
}
