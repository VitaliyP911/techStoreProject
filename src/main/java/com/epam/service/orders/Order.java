package com.epam.service.orders;

import com.epam.entity.Product;
import com.epam.service.orders.stateimpl.NewOrderStateImpl;

import java.util.LinkedList;
import java.util.List;

public class Order {

    private State state;
    private List<Product> products = new LinkedList<>();

    public Order(State state) {
        state = new NewOrderStateImpl();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addProduct() {
        state.addProduct(this);
    }

    public void grant() {
        state.grant(this);
    }

    public void invoice() {
        state.invoice(this);
    }

    public void ship() {
        state.ship(this);
    }

    public void addProductToList() {
        Product product = new Product();
        products.add(product);
    }

    public void getProductList() {
        System.out.println(products);
    }

    public void register() {
        if (!products.isEmpty()) {
            state.register(this);
        }
    }
}
