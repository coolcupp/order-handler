package com.coolcupp.order_service.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    private UUID orderId;
    private Long userId;
    private List<OrderItemDTO> orderItems;

//    public CreateOrderDTO() {
//    }
//
//    public CreateOrderDTO(UUID orderId, Long userId, List<OrderItemDTO> orderItems) {
//        this.orderId = orderId;
//        this.userId = userId;
//        this.orderItems = orderItems;
//    }
//
//    public UUID getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(UUID orderId) {
//        this.orderId = orderId;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public List<OrderItemDTO> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItemDTO> orderItems) {
//        this.orderItems = orderItems;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CreateOrderDTO that = (CreateOrderDTO) o;
//        return Objects.equals(orderId, that.orderId) && Objects.equals(userId, that.userId) && Objects.equals(orderItems, that.orderItems);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(orderId, userId, orderItems);
//    }
//
//    @Override
//    public String toString() {
//        return "CreateOrderDTO{" +
//                "orderId=" + orderId +
//                ", userId=" + userId +
//                ", orderItems=" + orderItems +
//                '}';
//    }
}
