package com.epam.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class History extends Entity {

    private String nameProduct;
    private Integer price;
    private String nameCompany;
    private Integer count;
    private Long userID;
    private Timestamp time;
    private Integer amountDue;

    public History() {

    }

    public History(Long id, String nameProduct, Integer price, String nameCompany, Long userID, Timestamp time) {
        super(id);
        this.nameProduct = nameProduct;
        this.price = price;
        this.nameCompany = nameCompany;
        this.userID = userID;
        this.time = time;
    }

    public History(String nameProduct, Integer price, String nameCompany, Integer count,
                   Long userID, Timestamp time, Integer amountDue) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.nameCompany = nameCompany;
        this.count = count;
        this.userID = userID;
        this.time = time;
        this.amountDue = amountDue;
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

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Integer amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        History history = (History) o;
        return Objects.equals(nameProduct, history.nameProduct) &&
                Objects.equals(price, history.price) &&
                Objects.equals(nameCompany, history.nameCompany) &&
                Objects.equals(count, history.count) &&
                Objects.equals(userID, history.userID) &&
                Objects.equals(time, history.time) &&
                Objects.equals(amountDue, history.amountDue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameProduct, price, nameCompany, count, userID, time, amountDue);
    }

    @Override
    public String toString() {
        return "History{" +
                "nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", nameCompany='" + nameCompany + '\'' +
                ", count=" + count +
                ", userID=" + userID +
                ", time=" + time +
                ", amountDue=" + amountDue +
                '}';
    }
}
