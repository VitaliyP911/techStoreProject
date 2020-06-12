package com.epam.service;

import com.epam.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductsList();

    List<Product> sort(String company, String price, String category);
}
