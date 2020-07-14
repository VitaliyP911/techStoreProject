package com.epam.service;

import com.epam.entity.Product;
import com.epam.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BasketService {
    Optional<Product> getProduct(Long id);

    Set<Product> getBasketList(User user);

    boolean addNewProductToBasket(Product product, User user);

    boolean deleteProductWithBasket(Product product, User user);

    boolean clearBasket(User user);

    Integer countAmountDue(User user);
}
