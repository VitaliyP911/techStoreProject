package com.epam.service.impl;

import com.epam.dao.CrudDao;
import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.AdminDaoImpl;
import com.epam.dao.impl.HistoryDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.Admin;
import com.epam.entity.History;
import com.epam.entity.User;
import com.epam.exception.NotSaveException;
import com.epam.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserService {

    private CrudDaoImpl<User> userCrudDao;
    private CrudDaoImpl<Admin> adminCrudDao;

    public UserServiceImpl() {
        userCrudDao = new UserDaoImpl();
        adminCrudDao = new AdminDaoImpl();
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
    public boolean update(Long id ,User user) {
        return userCrudDao.update(id, user);
    }

    @Override
    public boolean delete(Long id) {

        CrudDao<History> historyCrudDao = new HistoryDaoImpl();

        historyCrudDao.delete(id);

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

    @Override
    public boolean checkAdmin(String email) {
        return  adminCrudDao.getAll().stream()
                .map(Admin::getEmail)
                .collect(Collectors.toList())
                .contains(email);
    }

    @Override
    public Optional<User> getDataUser(String email) {
        return userCrudDao.getByField(email);
    }

    @Override
    public List<User> getUserList() {
        return userCrudDao.getAll();
    }


    /*public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        System.out.println(userService.checkAdmin("vitaliy.polishchuk11@gmail.co"));
    }*/
}
