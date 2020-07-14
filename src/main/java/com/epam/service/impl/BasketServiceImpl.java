package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.BasketService;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class BasketServiceImpl implements BasketService {
    private CrudDaoImpl<Product> productCrudDao;

    public BasketServiceImpl() {
        productCrudDao = new ProductDaoImpl();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productCrudDao.getById(id);
    }

    @Override
    public Set<Product> getBasketList(User user) {
        return user.getProductList();
    }

    @Override
    public boolean addNewProductToBasket(Product product, User user) {

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
    }


    @Override
    public boolean deleteProductWithBasket(Product product, User user) {
        return user.getProductList().remove(product);
    }

    @Override
    public boolean clearBasket(User user) {
        user.getProductList().clear();
        return true;
    }

    @Override
    public Integer countAmountDue(User user) {
        return user.getProductList().stream().mapToInt( i -> i.getCount() * i.getPrice()).sum();
    }
}
