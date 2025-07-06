package com.coolcupp.order_service.event;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItemEvent {

    private Integer productId;

    private Integer quantityToOrder;

    private BigDecimal productPrice;

    private BigDecimal discountPercent;

    private BigDecimal totalPrice;

    public OrderItemEvent() {
    }

    public OrderItemEvent(Integer productId, Integer quantityToOrder, BigDecimal productPrice,
                          BigDecimal discountPercent, BigDecimal totalPrice) {
        this.productId = productId;
        this.quantityToOrder = quantityToOrder;
        this.productPrice = productPrice;
        this.discountPercent = discountPercent;
        this.totalPrice = totalPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantityToOrder() {
        return quantityToOrder;
    }

    public void setQuantityToOrder(Integer quantityToOrder) {
        this.quantityToOrder = quantityToOrder;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEvent that = (OrderItemEvent) o;
        return Objects.equals(productId, that.productId) && Objects.equals(quantityToOrder, that.quantityToOrder) && Objects.equals(productPrice, that.productPrice) && Objects.equals(discountPercent, that.discountPercent) && Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantityToOrder, productPrice, discountPercent, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderItemEvent{" +
                "productId=" + productId +
                ", quantityToOrder=" + quantityToOrder +
                ", productPrice=" + productPrice +
                ", discountPercent=" + discountPercent +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
