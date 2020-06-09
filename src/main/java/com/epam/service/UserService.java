package com.epam.service;

import com.epam.entity.User;

import java.util.Optional;

public interface UserService {

    boolean login(String login, String password);

    boolean changePassword(String newPassword, String login);

    boolean save(User user);

    boolean update(Long id, User user);

    boolean delete(Long id);

    boolean addNewUser(User user);

    boolean checkForSimilarityOfEmails(String email);

    boolean checkAdmin(String email);

    Optional<User> getDataUser(String email);
}
