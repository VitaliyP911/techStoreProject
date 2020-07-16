package com.epam.service.impl;

import com.epam.dao.CrudDao;
import com.epam.dao.impl.AdminDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.Admin;
import com.epam.entity.User;
import com.epam.service.UserService;
import javassist.NotFoundException;
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

public class UserServiceImplTest {
    @Mock
    private UserDaoImpl userCrudDao;

    @Mock
    private AdminDaoImpl adminCrudDao;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    User testUser = new User(1L,"name", "surname", "email", "password");

    Admin testAdmin = new Admin(1L, "email");

    List<User> testUserList = Arrays.asList(testUser);

    @Test
    public void loginTest() {
        when(userCrudDao.getByField(anyString())).thenReturn(Optional.of(testUser));
        assertTrue(userService.login("email", "password"));
    }

    @Test
    public void loginExceptionTest() {
        when(userCrudDao.getByField(anyString())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> userService.login("email", "password"));
    }

    @Test
    public void changePasswordTest() {
        when(userCrudDao.getByField(anyString())).thenReturn(Optional.of(testUser));
        when(userCrudDao.update(anyLong(), any())).thenReturn(true);
        assertTrue(userService.changePassword("newPassword", "email"));
    }

    @Test
    public void changePasswordExceptionTest() {
        when(userCrudDao.update(anyLong(),any())).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> userService.changePassword("newPassword", "email"));
    }

    @Test
    public void updateTest() {
        when(userCrudDao.update(anyLong(), any())).thenReturn(true);
        assertTrue(userService.update(1L, testUser));
    }

    @Test
    public void updateExceptionTest() {
        when(userCrudDao.update(anyLong(), any())).thenReturn(false);
        assertFalse(userService.update(1L, testUser));
    }

    @Test
    public void deleteTest() {
        when(userCrudDao.delete(anyLong())).thenReturn(true);
        assertTrue(userService.delete(1L));
    }

    @Test
    public void deleteExceptionTest() {
        when(userCrudDao.delete(anyLong())).thenReturn(false);
        assertFalse(userService.delete(1L));
    }

    @Test
    public void addNewUserTest() {
        when(userCrudDao.save(any())).thenReturn(true);
        assertTrue(userService.addNewUser(testUser));
    }

    @Test
    public void addNewUserExceptionTest() {
        when(userCrudDao.save(any())).thenReturn(false);
        assertFalse(userService.addNewUser(testUser));
    }

    @Test
    public void checkForSimilarityOfEmailsTest() {
        when(userCrudDao.getByField(anyString())).thenReturn(Optional.of(testUser));
        assertTrue(userService.checkForSimilarityOfEmails("email"));
    }

    @Test
    public void checkForSimilarityOfEmailsExceptionTest() {
        when(userCrudDao.getByField(anyString())).thenReturn(Optional.empty());
        assertFalse(userService.checkForSimilarityOfEmails("email"));
    }

    @Test
    public void checkAdminTest() {


        when(adminCrudDao.getByField(anyString())).thenReturn(Optional.of(testAdmin));
        assertTrue(userService.checkAdmin("email"));
    }

    @Test
    public void checkAdminExceptionTest() {
        when(adminCrudDao.getByField(anyString())).thenReturn(Optional.empty());
        assertFalse(userService.checkAdmin("email"));
    }

    @Test
    public void getDataUserTest() {
        when(userCrudDao.getByField(anyString())).thenReturn(Optional.of(testUser));
        assertEquals(userService.getDataUser("email"), Optional.of(testUser));
    }

    @Test
    public void getDataUserExceptionTest() {
        when(userCrudDao.getByField(anyString())).thenReturn(Optional.empty());
        assertEquals(userService.getDataUser("email"), Optional.empty());
    }
    @Test
    public void getUserListTest() {
        when(userCrudDao.getAll()).thenReturn(testUserList);
        assertEquals(userService.getUserList(), testUserList);
    }

    @Test
    public void getUserListExceptionTest() {
        when(userCrudDao.getAll()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> userService.getUserList());
    }
}
