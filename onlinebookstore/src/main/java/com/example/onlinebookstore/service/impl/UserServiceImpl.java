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
        return userRepository.findByUsername(userName).get();
    }

    @Override
    public User createUser(User newUser) {
        User existingUser = userRepository.findByUsername(newUser.getUsername()).get();

        if (existingUser == null) {
            userRepository.save(newUser);
            return newUser;
        } else {
            return null;
        }
    }

    @Override
    public User updateUser(String userName, User updatedUser) {
        User existingUser = userRepository.findByUsername(userName).get();

        if (existingUser != null) {
            updatedUser.setUsername(existingUser.getUsername());
            userRepository.save(updatedUser);
            return updatedUser;
        } else {
            return null;
        }
    }

    @Override
    public String deleteUser(String username) {
        Boolean existingUser = userRepository.existsByUsername(username);

        if (existingUser) {
            userRepository.deleteByUsername(username);
            return "Deleted Successfully";
        } else {
            return "UserId not found, provide a valid userId";
        }
    }

    // Additional methods for role-based access...
    public String getUserDetailsBasedOnRole(String username) {
        User user = userRepository.findByUsername(username).get();

        if (user != null) {
            Role role = user.getRole();
            if ("customer".equals(role)) {
                // Return customer details excluding store name
                return "Customer Details: " + user.getUsername() + ", Role: " + user.getRole();
            } else if ("admin".equals(role)) {
                // Return all details
                return "Admin Details: " + user.getUsername() + ", Role: " + user.getRole() +
                        ", Store Name: " + user.getStoreName();
            } else if ("vendor".equals(role)) {
                // Return details with store name for vendor
                return "Vendor Details: " + user.getUsername() + ", Role: " + user.getRole() +
                        ", Store Name: " + user.getStoreName();
            }
        }
        return "User not found.";
    }
}
