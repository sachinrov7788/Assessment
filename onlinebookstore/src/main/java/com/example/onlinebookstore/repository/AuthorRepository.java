package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByAuthorId(String authorId);

    Boolean existsByAuthorId(String authorId);

    String deleteByAuthorId(String authorId);
}
