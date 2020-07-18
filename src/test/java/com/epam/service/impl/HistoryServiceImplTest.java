package com.epam.service.impl;
import com.epam.dao.impl.HistoryDaoImpl;
import com.epam.entity.History;
import com.epam.entity.User;
import com.epam.service.HistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
public class HistoryServiceImplTest {
    @Mock
    private HistoryDaoImpl historyCrudDao;

    @InjectMocks
    private HistoryService historyService= new HistoryServiceImpl();

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    History testHistory = new History(1L,"nameProduct", 1000, "nameCompany", 1L, new Timestamp(new Date().getTime()));

    User testUser = new User(1L,"name", "surname", "email", "password");

    List<History> testHistoryList = Arrays.asList(testHistory);

    @Test
    public void addPurchaseToHistoryTest() {
        when(historyCrudDao.save(any())).thenReturn(true);
        assertTrue(historyService.addPurchaseToHistory(testHistoryList));
    }

    @Test
    public void addPurchaseToHistoryExceptionTest() {
        when(historyCrudDao.save(any())).thenThrow(RuntimeException.class);
        assertFalse(historyService.addPurchaseToHistory(testHistoryList));
    }

    @Test
    public void clearHistoryTest() {
        when(historyCrudDao.deleteListByFiled(anyString())).thenReturn(true);
        assertTrue(historyService.clearHistory(testUser));
    }

    @Test
    public void clearHistoryExceptionTest() {
        when(historyCrudDao.deleteListByFiled(anyString())).thenReturn(false);
        assertFalse(historyService.clearHistory(testUser));
    }

    @Test
    public void getHistoryTest() {
        when(historyCrudDao.getListByField(anyString())).thenReturn(testHistoryList);
        assertEquals(historyService.getHistory(testUser), testHistoryList);
    }

    @Test
    public void getHistoryExceptionTest() {
        when(historyCrudDao.getListByField(anyString())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> historyService.getHistory(testUser));
    }
}
