package com.epam.entity;

import java.util.List;
import java.util.Objects;

public class User extends Entity{

    private String name;
    private String password;
    private List<Basket> productList;

    public User(){

    }

    public User(long id, String name, String password, List<Basket> productList) {
        super(id);
        this.name = name;
        this.password = password;
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Basket> getProductList() {
        return productList;
    }

    public void setProductList(List<Basket> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(productList, user.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, password, productList);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", productList=" + productList +
                '}';
    }
}

