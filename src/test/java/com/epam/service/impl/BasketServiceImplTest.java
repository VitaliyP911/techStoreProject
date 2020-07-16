package com.epam.service.impl;

import com.epam.dao.impl.ProductDaoImpl;

import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.BasketService;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.LinkedHashSet;
import java.util.Set;
import com.epam.dao.impl.AdminDaoImpl;
import com.epam.dao.impl.HistoryDaoImpl;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.Admin;
import com.epam.entity.History;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.HistoryService;
import com.epam.service.ProductService;
import com.epam.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import static org.mockito.MockitoAnnotations.initMocks;


public class BasketServiceImplTest {
    @Mock
    private ProductDaoImpl productCrudDao;

    @InjectMocks
    private BasketService basketService= new BasketServiceImpl();

    Set<Product> testProductSet;

    Product testProduct = new Product(1L,"name", "nameCompany", 1000);

    User testUser = new User(1L,"name", "surname", "email", "password", testProductSet;

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @Test
    public void getProductTest() {
        when(productCrudDao.getById(anyLong())).thenReturn(Optional.of(testProduct));
        assertEquals(basketService.getProduct(1L), Optional.of(testProduct));
    }

    @Test
    public void getProductExceptionTest() {
        when(productCrudDao.getById(anyLong())).thenReturn(Optional.empty());
        assertEquals(basketService.getProduct(1L), Optional.empty());
    }

    @Test
    public void getBasketListTest() {
        assertEquals(basketService.getBasketList(testUser), testProductSet);
    }

    @Test
    public void addNewProductToBasketTest() {
        assertTrue(basketService.addNewProductToBasket(testProduct, testUser));
    }

    /*@Test
    public void deleteProductWithBasketTest() {
        assertTrue(basketService.deleteProductWithBasket(testProduct,testUser));
    }*/
}
