package com.epam.service.orders;

public interface State {

    default void addProduct(Order order){}

    default void register(Order order){}

    default void grant(Order order){}

    default void invoice(Order order){}

    default void ship(Order order){}

    default void cancel(Order order){}
}
