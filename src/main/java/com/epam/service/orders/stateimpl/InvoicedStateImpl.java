package com.epam.service.orders.stateimpl;

import com.epam.service.orders.Order;
import com.epam.service.orders.State;

public class InvoicedStateImpl implements State {
    @Override
    public void ship(Order order) {
        order.setState(new ShippedStateImpl());
        System.out.println("Order is shipped");
    }
}
