package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByName(String userName);

    User createUser(User newUser);

    User updateUser(String userName, User updatedUser);

    String deleteUser(String username);
}
