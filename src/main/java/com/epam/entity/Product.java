package com.epam.entity;

import java.util.Objects;

public class Product extends Entity {

    private String name;
    private String nameCompany;
    private Integer price;
    private Category category;
    private Integer guarantee;

    public Product() {

    }

    public Product(String name, String nameCompany,
                   Integer price, Category category,
                   Integer guarantee) {
        this.name = name;
        this.nameCompany = nameCompany;
        this.price = price;
        this.category = category;
        this.guarantee = guarantee;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
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
                Objects.equals(nameCompany, product.nameCompany) &&
                Objects.equals(price, product.price) &&
                category == product.category &&
                Objects.equals(guarantee, product.guarantee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, nameCompany, price, category, guarantee);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", nameCompany='" + nameCompany + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", guarantee=" + guarantee +
                '}';
    }
}
