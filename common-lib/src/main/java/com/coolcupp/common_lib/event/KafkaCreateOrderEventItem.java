package com.coolcupp.common_lib.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaCreateOrderEventItem {

    private Long productId;

    private Integer quantityToOrder;

    private BigDecimal price;

    private BigDecimal discountPercent;

    private BigDecimal totalItemPrice;

//    public KafkaCreateOrderEventItem() {
//    }
//
//    public KafkaCreateOrderEventItem(Long productId, Integer quantityToOrder, BigDecimal price,
//                                     BigDecimal discountPercent, BigDecimal totalItemPrice) {
//        this.productId = productId;
//        this.quantityToOrder = quantityToOrder;
//        this.price = price;
//        this.discountPercent = discountPercent;
//        this.totalItemPrice = totalItemPrice;
//    }

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
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public BigDecimal getDiscountPercent() {
//        return discountPercent;
//    }
//
//    public void setDiscountPercent(BigDecimal discountPercent) {
//        this.discountPercent = discountPercent;
//    }
//
//    public BigDecimal getTotalItemPrice() {
//        return totalItemPrice;
//    }
//
//    public void setTotalItemPrice(BigDecimal totalItemPrice) {
//        this.totalItemPrice = totalItemPrice;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        KafkaCreateOrderEventItem that = (KafkaCreateOrderEventItem) o;
//        return Objects.equals(productId, that.productId) && Objects.equals(quantityToOrder, that.quantityToOrder) && Objects.equals(price, that.price) && Objects.equals(discountPercent, that.discountPercent) && Objects.equals(totalItemPrice, that.totalItemPrice);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productId, quantityToOrder, price, discountPercent, totalItemPrice);
//    }
//
//    @Override
//    public String toString() {
//        return "KafkaCreateOrderEventItem{" +
//                "productId=" + productId +
//                ", quantityToOrder=" + quantityToOrder +
//                ", price=" + price +
//                ", discountPercent=" + discountPercent +
//                ", totalItemPrice=" + totalItemPrice +
//                '}';
//    }
}
