package com.epam.service;

import com.epam.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Method for user validation.
     *
     * @param login
     * @param password
     * @return true if the user exists and the password matches
     */
    boolean login(String login, String password);
    /**
     * Method for password changes.
     *
     * @param login
     * @param newPassword
     * @return true if the user password is changed
     */
    boolean changePassword(String newPassword, String login);
    /**
     * Method for updating user data.
     *
     * @param id
     * @param user
     * @return true if user data is updated
     */
    boolean update(Long id, User user);
    /**
     * Method for delete user.
     *
     * @param id
     * @return true if user deleted
     */
    boolean delete(Long id);
    /**
     * Method for add new user.
     *
     * @param user
     * @return true if added new user
     */
    boolean addNewUser(User user);
    /**
     * Method for verify the existence of an email.
     *
     * @param email
     * @return true if the email exists
     */
    boolean checkForSimilarityOfEmails(String email);
    /**
     * Method for check the admin.
     *
     * @param email
     * @return true if the email belongs to the admin
     */
    boolean checkAdmin(String email);
    /**
     * Method getting user data.
     *
     * @param email
     * @return Optional object
     */
    Optional<User> getDataUser(String email);
    /**
     * Method getting list with all users.
     *
     * @return list of users
     */
    List<User> getUserList();
}
