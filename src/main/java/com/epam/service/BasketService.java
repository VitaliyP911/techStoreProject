package com.epam.service;

import com.epam.entity.Product;
import com.epam.entity.User;

import java.util.Optional;
import java.util.Set;

public interface BasketService {
    /**
     * Method getting product data.
     *
     * @param id
     * @return Optional object
     */
    Optional<Product> getProduct(Long id);
    /**
     * Method getting user basket.
     *
     * @param user
     * @return basket list
     */
    Set<Product> getBasketList(User user);
    /**
     * Method for add the product to the basket.
     *
     * @param product
     * @param user
     * @return true if the product is added to the basket
     */
    boolean addNewProductToBasket(Product product, User user);
    /**
     * Method for removing the product from the cart.
     *
     * @param product
     * @param user
     * @return true if the product is removed from the cart
     */
    boolean deleteProductWithBasket(Product product, User user);
    /**
     * Method for clearing user basket.
     *
     * @param user
     * @return true if cleared basket
     */
    boolean clearBasket(User user);
    /**
     * Method getting receiving amounts for payment.
     *
     * @param user
     * @return calculated amount
     */
    Integer calcAmountDue(User user);
}
