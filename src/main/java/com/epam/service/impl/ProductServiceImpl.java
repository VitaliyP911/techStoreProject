package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.AdminDaoImpl;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.Admin;
import com.epam.entity.Entity;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private CrudDaoImpl<Product> productCrudDao;

    public ProductServiceImpl() {
        productCrudDao = new ProductDaoImpl();
    }

    @Override
    public List<Product> getProductsList() {
        return productCrudDao.getAll();
    }

    @Override
    public List<Product> sort(String company, String price, String category) {
        List<Product> list = productCrudDao.getAll();

        if(!company.isEmpty()){
            list = list.stream().filter(x -> x.getNameCompany().equals(company)).collect(Collectors.toList());
        }
        if (!price.isEmpty()){
            if (price.equals("larger")) {
                list.sort((first, second) ->
                        first.getPrice() <= second.getPrice() ? 1 : -1);
            }
            if (price.equals("smaller")) {
                list.sort((first, second) ->
                        first.getPrice() >= second.getPrice() ? 1 : -1);
            }
        }
        if(!category.isEmpty()){
            list = list.stream().filter(x -> x.getCategory().getNameCategory().equals(category)).collect(Collectors.toList());
        }
        return list;
    }
}
