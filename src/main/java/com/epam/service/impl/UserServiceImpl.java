package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exception.NotSaveException;
import com.epam.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserServiceImpl implements UserService {

    private CrudDaoImpl<User> userCrudDao;

    public UserServiceImpl() {
        userCrudDao = new UserDaoImpl();
    }

    @Override
    public boolean login(String email, String password) throws NoSuchElementException {
        return userCrudDao.getByField(email)
                .get()
                .getPassword()
                .equals(password);
    }

    @Override
    public boolean changePassword(String newPassword, String email) {

        User user = userCrudDao.getByField(email).get();

        user.setPassword(newPassword);

        return userCrudDao.update(user.getId(), user);
    }

    @Override
    public boolean save(User user) throws NotSaveException {
        return userCrudDao.save(user);
    }

    @Override
    public boolean delete(Long id) {
        return userCrudDao.delete(id);
    }

    @Override
    public boolean addNewUser(User user) {
        return userCrudDao.save(user);
    }

    @Override
    public boolean checkForSimilarityOfEmails(String email) {
        return userCrudDao.getAll().stream()
                .map(User::getEmail)
                .collect(Collectors.toList())
                .contains(email);
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        System.out.println(userService.checkForSimilarityOfEmails("vitaliy.polishchuk11@gmail.com"));
            }
}
