package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    String deleteByUsername(String username);
}
