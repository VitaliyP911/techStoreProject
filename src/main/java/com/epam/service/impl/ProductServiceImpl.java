package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.entity.Product;
import com.epam.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private CrudDaoImpl<Product> productCrudDao;
    /**
     * Default constructor.
     */
    public ProductServiceImpl() {
        productCrudDao = new ProductDaoImpl();
    }
    /**
     * Method getting obtaining a list of user products.
     *
     * @return list of products
     */
    @Override
    public List<Product> getProductsList() {
        return productCrudDao.getAll();
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
     * Method for delete product.
     *
     * @param id
     * @return true if product deleted
     */
    @Override
    public boolean delete(Long id) {
        return productCrudDao.delete(id);
    }
    /**
     * Method for updating product data.
     *
     * @param id
     * @param product
     * @return true if product data is updated
     */
    @Override
    public boolean update(Long id , Product product) {
        return productCrudDao.update(id, product);
    }
    /**
     * Method for add new product.
     *
     * @param product
     * @return true if added new product
     */
    @Override
    public boolean addNewProduct(Product product) {
        return productCrudDao.save(product);
    }
}
