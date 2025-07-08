package com.coolcupp.notification_service.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItemResponseDTO {

    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discountPercent;
    private BigDecimal totalItemPrice;

    public OrderItemResponseDTO() {
    }

    public OrderItemResponseDTO(Long productId, Integer quantity, BigDecimal price, BigDecimal discountPercent,
                                BigDecimal totalItemPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = discountPercent;
        this.totalItemPrice = totalItemPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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

    public BigDecimal getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(BigDecimal totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemResponseDTO that = (OrderItemResponseDTO) o;
        return Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(discountPercent, that.discountPercent) && Objects.equals(totalItemPrice, that.totalItemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, price, discountPercent, totalItemPrice);
    }

    @Override
    public String toString() {
        return "OrderItemResponseDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discountPercent=" + discountPercent +
                ", totalItemPrice=" + totalItemPrice +
                '}';
    }
}
