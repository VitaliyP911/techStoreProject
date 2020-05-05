package com.epam.dto;

import com.epam.entity.Category;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class HistoryDto {
    private Long id;
    private String nameProduct;
    private Integer price;
    private Category category;
    private Integer guarantee;
    private Long userID;
    private Date date;

    public HistoryDto(){

    }

    public HistoryDto(Long id, String nameProduct, Integer price, Category category, Integer guarantee, Long userID, Date date) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.category = category;
        this.guarantee = guarantee;
        this.userID = userID;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        HistoryDto that = (HistoryDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameProduct, that.nameProduct) &&
                Objects.equals(price, that.price) &&
                category == that.category &&
                Objects.equals(guarantee, that.guarantee) &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameProduct, price, category, guarantee, userID, date);
    }

    @Override
    public String toString() {
        return "HistoryDto{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", guarantee=" + guarantee +
                ", userID=" + userID +
                ", date=" + date +
                '}';
    }
}
