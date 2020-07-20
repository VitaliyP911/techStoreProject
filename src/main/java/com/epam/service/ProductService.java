package com.epam.service;

import com.epam.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    /**
     * Method getting obtaining a list of user products.
     *
     * @return list of products
     */
    List<Product> getProductsList();
    /**
     * Method getting product data.
     *
     * @param id
     * @return Optional object
     */
    Optional<Product> getProduct(Long id);
    /**
     * Method for delete product.
     *
     * @param id
     * @return true if product deleted
     */
    boolean delete(Long id);
    /**
     * Method for updating product data.
     *
     * @param id
     * @param product
     * @return true if product data is updated
     */
    boolean update(Long id, Product product);
    /**
     * Method for add new product.
     *
     * @param product
     * @return true if added new product
     */
    boolean addNewProduct(Product product);
}
