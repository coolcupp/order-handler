package com.coolcupp.order_service.dto;

import java.util.Objects;

public class ProductResponseGrpcDTO {

    private Integer productId;
    private String name;
    private Integer quantity;
    private String price;
    private String discountPercent;

    public ProductResponseGrpcDTO(Integer productId, String name, Integer quantity, String price, String discountPercent) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public ProductResponseGrpcDTO() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseGrpcDTO that = (ProductResponseGrpcDTO) o;
        return Objects.equals(productId, that.productId) && Objects.equals(name, that.name) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(discountPercent, that.discountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, quantity, price, discountPercent);
    }

    @Override
    public String toString() {
        return "ProductResponseGrpcDTO{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price='" + price + '\'' +
                ", discountPercent='" + discountPercent + '\'' +
                '}';
    }
}
