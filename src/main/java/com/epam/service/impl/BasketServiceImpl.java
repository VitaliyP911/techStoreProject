package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.BasketService;

import java.util.Optional;
import java.util.Set;

public class BasketServiceImpl implements BasketService {

    private CrudDaoImpl<Product> productCrudDao;
    /**
     * Default constructor.
     */
    public BasketServiceImpl() {
        productCrudDao = new ProductDaoImpl();
    }
    /**
     * Method getting product data.
     *
     * @param id
     * @return Optional object
     */
    @Override
    public Optional<Product> getProduct(Long id) {
        return productCrudDao.getById(id);
    }
    /**
     * Method getting user basket.
     *
     * @param user
     * @return basket list
     */
    @Override
    public Set<Product> getBasketList(User user) {
        return user.getProductList();
    }
    /**
     * Method for add the product to the basket.
     *
     * @param product
     * @param user
     * @return true if the product is added to the basket
     */
    @Override
    public boolean addNewProductToBasket(Product product, User user) {
        try {
            Set<Product> products = user.getProductList();

            if (user.getProductList().contains(product)) {
                user.getProductList().stream()
                        .filter(i -> i.equals(product))
                        .forEach(i -> i.setCount(i.getCount() + product.getCount()));
                return true;
            } else {
                user.setProductToList(product);
                return true;
            }
        } catch (RuntimeException e) {
            return false;
        }
    }
    /**
     * Method for removing the product from the cart.
     *
     * @param product
     * @param user
     * @return true if the product is removed from the cart
     */
    @Override
    public boolean deleteProductWithBasket(Product product, User user) {
        return user.getProductList().remove(product);
    }
    /**
     * Method for clearing user basket.
     *
     * @param user
     * @return true if cleared basket
     */
    @Override
    public boolean clearBasket(User user) {
        try {
            user.getProductList().clear();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    /**
     * Method getting receiving amounts for payment.
     *
     * @param user
     * @return calculated amount
     */
    @Override
    public Integer calcAmountDue(User user) {
        return user.getProductList().stream().mapToInt(i -> i.getCount() * i.getPrice()).sum();
    }
}
