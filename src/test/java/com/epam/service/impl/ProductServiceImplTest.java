package com.epam.service.impl;

import com.epam.dao.impl.ProductDaoImpl;
import com.epam.entity.Product;
import com.epam.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    @Mock
    private ProductDaoImpl productCrudDao;

    @InjectMocks
    private ProductService productService= new ProductServiceImpl();

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    Product testProduct = new Product(1L,"name", "nameCompany", 1000,2);

    List<Product> testProductList = Arrays.asList(testProduct);

    @Test
    public void getProductsListTest() {
        when(productCrudDao.getAll()).thenReturn(testProductList);
        assertEquals(productService.getProductsList(), testProductList);
    }

    @Test
    public void getProductsListExceptionTest() {
        when(productCrudDao.getAll()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> productService.getProductsList());
    }

    @Test
    public void getProductTest() {
        when(productCrudDao.getById(anyLong())).thenReturn(Optional.of(testProduct));
        assertEquals(productService.getProduct(1L), Optional.of(testProduct));
    }

    @Test
    public void getProductExceptionTest() {
        when(productCrudDao.getById(anyLong())).thenReturn(Optional.empty());
        assertEquals(productService.getProduct(1L), Optional.empty());
    }

    @Test
    public void deleteTest() {
        when(productCrudDao.delete(anyLong())).thenReturn(true);
        assertTrue(productService.delete(1L));
    }

    @Test
    public void deleteExceptionTest() {
        when(productCrudDao.delete(anyLong())).thenReturn(false);
        assertFalse(productService.delete(1L));
    }

    @Test
    public void updateTest() {
        when(productCrudDao.update(anyLong(), any())).thenReturn(true);
        assertTrue(productService.update(1L, testProduct));
    }

    @Test
    public void updateExceptionTest() {
        when(productCrudDao.update(anyLong(), any())).thenReturn(false);
        assertFalse(productService.update(1L, testProduct));
    }

    @Test
    public void addNewProductTest() {
        when(productCrudDao.save(any())).thenReturn(true);
        assertTrue(productService.addNewProduct(testProduct));
    }

    @Test
    public void addNewProductExceptionTest() {
        when(productCrudDao.save(any())).thenReturn(false);
        assertFalse(productService.addNewProduct(testProduct));
    }

}
