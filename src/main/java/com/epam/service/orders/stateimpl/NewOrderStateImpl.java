package com.epam.service.orders.stateimpl;

import com.epam.service.orders.Order;
import com.epam.service.orders.State;

public class NewOrderStateImpl implements State {
    @Override
    public void addProduct(Order order) {
        order.addProductToList();
    }

    @Override
    public void register(Order order) {
        order.setState(new RegisteredStateImpl());
        System.out.println("Order is registered");
    }

    @Override
    public void cancel(Order order) {
        order.setState(new CancelStateImpl());
        System.out.println("Order is cancelled");
    }
}
