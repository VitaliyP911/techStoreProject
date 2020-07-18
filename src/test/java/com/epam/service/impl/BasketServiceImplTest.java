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
import java.util.Optional;

import org.junit.jupiter.api.Test;


import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;



public class BasketServiceImplTest {
    @Mock
    private ProductDaoImpl productCrudDao;

    @InjectMocks
    private BasketService basketService= new BasketServiceImpl();

    Set<Product> testProductSet = new LinkedHashSet<>();

    Product testProduct = new Product(1L,"name", "nameCompany", 1000,2);

    User testUser = new User(1L,"name", "surname", "email", "password", testProductSet);

    User testUserMock = Mockito.mock(User.class);

    @BeforeEach
    public void init() {
        initMocks(this);
        testProductSet.add(testProduct);
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
        when(testUserMock.getProductList()).thenReturn(testProductSet);
        assertTrue(basketService.addNewProductToBasket(testProduct, testUserMock));
    }

    @Test
    public void addNewProductToBasketExceptionTest() {
        when(testUserMock.getProductList()).thenThrow(RuntimeException.class);
        assertFalse(basketService.addNewProductToBasket(testProduct, testUserMock));
    }

    @Test
    public void deleteProductWithBasketTest() {

        when(testUserMock.getProductList()).thenReturn(testProductSet);
        assertTrue(basketService.deleteProductWithBasket(testProduct,testUserMock));
    }

    @Test
    public void deleteProductWithBasketExceptionTest() {
        Set<Product> emptyProductSet = new LinkedHashSet<>();

        when(testUserMock.getProductList()).thenReturn(emptyProductSet);
        assertFalse(basketService.deleteProductWithBasket(testProduct,testUserMock));
    }

    @Test
    public void clearBasketTest() {
        when(testUserMock.getProductList()).thenReturn(testProductSet);
        assertTrue(basketService.clearBasket(testUserMock));
    }

    @Test
    public void clearBasketExceptionTest() {
        when(testUserMock.getProductList()).thenThrow(RuntimeException.class);
        assertFalse(basketService.clearBasket(testUserMock));
    }
}
