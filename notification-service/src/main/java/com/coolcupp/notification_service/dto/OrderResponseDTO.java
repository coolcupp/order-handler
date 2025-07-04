package com.coolcupp.notification_service.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderResponseDTO {

    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discountPercent;
    private BigDecimal totalPrice;
    private Integer userId;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Integer id, Integer orderId, Integer productId, Integer quantity, BigDecimal price,
                            BigDecimal discountPercent, BigDecimal totalPrice, Integer userId) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = discountPercent;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderResponseDTO that = (OrderResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(discountPercent, that.discountPercent) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, productId, quantity, price, discountPercent, totalPrice, userId);
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discountPercent=" + discountPercent +
                ", totalPrice=" + totalPrice +
                ", userId=" + userId +
                '}';
    }
}
