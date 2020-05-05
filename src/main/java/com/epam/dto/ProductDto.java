package com.epam.dto;

import com.epam.entity.Category;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Category category;
    private Integer guarantee;

    public ProductDto(){

    }

    public ProductDto(Long id, String name, Integer price, Category category, Integer guarantee) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.guarantee = guarantee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                category == that.category &&
                Objects.equals(guarantee, that.guarantee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, guarantee);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", guarantee=" + guarantee +
                '}';
    }
}
