package com.coolcupp.notification_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Data
public class Order {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderItem> orderItems;

    public Order(UUID id, Long userId, BigDecimal totalPrice) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }
}
