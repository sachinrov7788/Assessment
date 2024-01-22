package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.Author;
import com.example.onlinebookstore.model.Book;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();

    Author getAuthorById(String authorId);

    Author createAuthor(Author newAuthor);

    Author updateAuthor(String authorId, Author updatedAuthor);

    String deleteAuthor(String authorId);
}
