package com.epam.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class History extends Entity {
    private String nameProduct;
    private Integer price;
    private Category category;
    private Integer guarantee;
    private Long userID;
    private Date date;

    public History() {

    }

    public History(String nameProduct, Integer price, Category category, Integer guarantee, Long userID, Date date) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.category = category;
        this.guarantee = guarantee;
        this.userID = userID;
        this.date = date;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        History history = (History) o;
        return Objects.equals(nameProduct, history.nameProduct) &&
                Objects.equals(price, history.price) &&
                category == history.category &&
                Objects.equals(guarantee, history.guarantee) &&
                Objects.equals(userID, history.userID) &&
                Objects.equals(date, history.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameProduct, price, category, guarantee, userID, date);
    }

    @Override
    public String toString() {
        return "History{" +
                "nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", guarantee=" + guarantee +
                ", userID=" + userID +
                ", date=" + date +
                '}';
    }
}
