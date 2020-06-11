package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.AdminDaoImpl;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.Admin;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private CrudDaoImpl<Product> productCrudDao;

    public ProductServiceImpl() {
        productCrudDao = new ProductDaoImpl();
    }

    @Override
    public List<Product> getProductsList() {
        return productCrudDao.getAll();
    }
}
