package com.coolcupp.order_service.dto;

import java.util.Objects;

public class OrderItemDTO {

    private Integer productId;
    private Integer quantityToOrder;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer productId, Integer quantityToOrder) {
        this.productId = productId;
        this.quantityToOrder = quantityToOrder;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemDTO that = (OrderItemDTO) o;
        return Objects.equals(productId, that.productId) && Objects.equals(quantityToOrder, that.quantityToOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantityToOrder);
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "productId=" + productId +
                ", quantityToOrder=" + quantityToOrder +
                '}';
    }
}
