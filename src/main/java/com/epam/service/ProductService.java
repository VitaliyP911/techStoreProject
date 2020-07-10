package com.epam.service;

import com.epam.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProductsList();

    Optional<Product> getProduct(Long id);

    boolean delete(Long id);

    boolean update(Long id, Product product);
}
