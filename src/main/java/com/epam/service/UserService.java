package com.epam.service;

import com.epam.entity.User;

public interface UserService {

    boolean login(String login, String password);

    boolean changePassword(String newPassword, String login);

    boolean save(User user);

    boolean delete(Long id);

    boolean addNewUser(User user);

    boolean checkForSimilarityOfEmails(String email);
}
