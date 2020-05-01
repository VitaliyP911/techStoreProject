package com.epam.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product extends Entity {

    private String name;
    private BigDecimal price;
    private Category category;
    private Long guarantee;

    public Product() {

    }

    public Product(long id, String name, BigDecimal price, Category category, Long guarantee) {
        super(id);
        this.name = name;
        this.price = price;
        this.category = category;
        this.guarantee = guarantee;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Long guarantee) {
        this.guarantee = guarantee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                category == product.category &&
                Objects.equals(guarantee, product.guarantee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, price, category, guarantee);
    }

    @Override
    public String toString() {
        return "Product{" + super.toString() +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", guarantee=" + guarantee +
                '}';
    }
}
