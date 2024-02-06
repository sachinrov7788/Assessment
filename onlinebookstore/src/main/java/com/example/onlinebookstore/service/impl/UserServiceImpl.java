package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.model.Role;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String userName) {
        try {
            return userRepository.findByUsername(userName).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching user by name: " + e.getMessage());
        }
    }

    @Override
    public User createUser(User newUser) {
        try {
            User existingUser = userRepository.findByUsername(newUser.getUsername()).orElse(null);

            if (existingUser == null) {
                userRepository.save(newUser);
                return newUser;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating the user: " + e.getMessage());
        }
    }

    @Override
    public User updateUser(String userName, User updatedUser) {
        try {
            User existingUser = userRepository.findByUsername(userName).orElse(null);

            if (existingUser != null) {
                updatedUser.setUsername(existingUser.getUsername());
                userRepository.save(updatedUser);
                return updatedUser;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating the user: " + e.getMessage());
        }
    }

    @Override
    public String deleteUser(String username) {
        try {
            Boolean existingUser = userRepository.existsByUsername(username);

            if (existingUser) {
                userRepository.deleteByUsername(username);
                return "Deleted Successfully";
            } else {
                return "Username not found, provide a valid username";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting the user: " + e.getMessage());
        }
    }

    public String getUserDetailsBasedOnRole(String username) {
        try {
            User user = userRepository.findByUsername(username).orElse(null);

            if (user != null) {
                Role role = user.getRole();
                if ("customer".equals(role)) {
                    return "Customer Details: " + user.getUsername() + ", Role: " + user.getRole();
                } else if ("admin".equals(role)) {
                    return "Admin Details: " + user.getUsername() + ", Role: " + user.getRole() +
                            ", Store Name: " + user.getStoreName();
                } else if ("vendor".equals(role)) {
                    return "Vendor Details: " + user.getUsername() + ", Role: " + user.getRole() +
                            ", Store Name: " + user.getStoreName();
                }
            }
            return "User not found.";
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching user details based on role: " + e.getMessage());
        }
    }
}
