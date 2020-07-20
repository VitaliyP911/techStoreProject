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

public class UserServiceImpl implements UserService {

    private CrudDaoImpl<User> userCrudDao;
    private CrudDaoImpl<Admin> adminCrudDao;
    /**
     * Default constructor.
     */
    public UserServiceImpl() {
        userCrudDao = new UserDaoImpl();
        adminCrudDao = new AdminDaoImpl();
    }
    /**
     * Method for user validation.
     *
     * @param login
     * @param password
     * @return true if the user exists and the password matches
     */
    @Override
    public boolean login(String email, String password) throws NoSuchElementException {
        return userCrudDao.getByField(email)
                .get()
                .getPassword()
                .equals(password);
    }
    /**
     * Method for password changes.
     *
     * @param login
     * @param newPassword
     * @return true if the user password is changed
     */
    @Override
    public boolean changePassword(String newPassword, String email) {

        User user = userCrudDao.getByField(email).get();

        user.setPassword(newPassword);

        return userCrudDao.update(user.getId(), user);
    }
    /**
     * Method for updating user data.
     *
     * @param id
     * @param user
     * @return true if user data is updated
     */
    @Override
    public boolean update(Long id ,User user) {
        return userCrudDao.update(id, user);
    }
    /**
     * Method for delete user.
     *
     * @param id
     * @return true if user deleted
     */
    @Override
    public boolean delete(Long id) {

        CrudDao<History> historyCrudDao = new HistoryDaoImpl();

        historyCrudDao.delete(id);

        return userCrudDao.delete(id);
    }
    /**
     * Method for add new user.
     *
     * @param user
     * @return true if added new user
     */
    @Override
    public boolean addNewUser(User user) {
        return userCrudDao.save(user);
    }
    /**
     * Method for verify the existence of an email.
     *
     * @param email
     * @return true if the email exists
     */
    @Override
    public boolean checkForSimilarityOfEmails(String email) {
        return userCrudDao.getByField(email).isPresent();
    }
    /**
     * Method for check the admin.
     *
     * @param email
     * @return true if the email belongs to the admin
     */
    @Override
    public boolean checkAdmin(String email) {
        return  adminCrudDao.getByField(email).isPresent();
    }

    /**
     * Method getting user data.
     *
     * @param email
     * @return Optional object
     */
    @Override
    public Optional<User> getDataUser(String email) {
        return userCrudDao.getByField(email);
    }
    /**
     * Method getting list with all users.
     *
     * @return list of users
     */
    @Override
    public List<User> getUserList() {
        return userCrudDao.getAll();
    }
}
