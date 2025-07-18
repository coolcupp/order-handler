package com.coolcupp.order_service.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long productId;
    private Integer quantityToOrder;

//    public OrderItemDTO() {
//    }
//
//    public OrderItemDTO(Long productId, Integer quantityToOrder) {
//        this.productId = productId;
//        this.quantityToOrder = quantityToOrder;
//    }
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public Integer getQuantityToOrder() {
//        return quantityToOrder;
//    }
//
//    public void setQuantityToOrder(Integer quantityToOrder) {
//        this.quantityToOrder = quantityToOrder;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        OrderItemDTO that = (OrderItemDTO) o;
//        return Objects.equals(productId, that.productId) && Objects.equals(quantityToOrder, that.quantityToOrder);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productId, quantityToOrder);
//    }
//
//    @Override
//    public String toString() {
//        return "OrderItemDTO{" +
//                "productId=" + productId +
//                ", quantityToOrder=" + quantityToOrder +
//                '}';
//    }
}
