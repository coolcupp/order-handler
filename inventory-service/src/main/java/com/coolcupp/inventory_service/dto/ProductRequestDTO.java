package com.coolcupp.inventory_service.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductRequestDTO {
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discountPercent;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, Integer quantity, BigDecimal price, BigDecimal discountPercent) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = discountPercent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequestDTO that = (ProductRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(discountPercent, that.discountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price, discountPercent);
    }

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
