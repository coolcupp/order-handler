package com.coolcupp.notification_service.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderResponseDTO {

    private UUID orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private List<OrderItemResponseDTO> orderItems;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(UUID orderId, Long userId, BigDecimal totalPrice, List<OrderItemResponseDTO> orderItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemResponseDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponseDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderResponseDTO that = (OrderResponseDTO) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(userId, that.userId) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(orderItems, that.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, totalPrice, orderItems);
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", orderItems=" + orderItems +
                '}';
    }
}
