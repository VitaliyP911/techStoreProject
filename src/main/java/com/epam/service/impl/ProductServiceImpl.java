package com.epam.service.impl;

import com.epam.dao.CrudDao;
import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.HistoryDaoImpl;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.entity.History;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.ProductService;

import java.util.List;
import java.util.Optional;

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
    public Optional<Product> getProduct(Long id) {
        return productCrudDao.getById(id);
    }

    @Override
    public boolean delete(Long id) {
        return productCrudDao.delete(id);
    }

    @Override
    public boolean update(Long id , Product product) {
        return productCrudDao.update(id, product);
    }
}
