package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.service.UserService;

import java.util.NoSuchElementException;


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
    public boolean changePassword(String newPassword, String password, String email) {

        User user = userCrudDao.getByField(email).get();

        user.setPassword(newPassword);

        return userCrudDao.update(user.getId(), user);
    }

    @Override
    public boolean save(User user) {
        return userCrudDao.save(user);
    }

    @Override
    public boolean delete(Long id) {
        return userCrudDao.delete(id);
    }

}
