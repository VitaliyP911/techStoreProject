package com.epam.entity;

import java.util.Objects;

public class Basket extends Entity{

    private Product product;
    private Long count;

    public Basket(){

    }

    public Basket(Long id, Product product, Long count) {
        super(id);
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Basket basket = (Basket) o;
        return Objects.equals(product, basket.product) &&
                Objects.equals(count, basket.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, count);
    }

    @Override
    public String toString() {
        return "Basket{" + super.toString() +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
