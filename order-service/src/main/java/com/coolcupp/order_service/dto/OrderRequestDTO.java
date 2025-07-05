package com.coolcupp.order_service.dto;

import java.util.List;
import java.util.Objects;

public class OrderRequestDTO {
    // TODO user id from auth
    private List<OrderItemDTO> orderItems;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequestDTO that = (OrderRequestDTO) o;
        return Objects.equals(orderItems, that.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderItems);
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "orderItems=" + orderItems +
                '}';
    }
}
