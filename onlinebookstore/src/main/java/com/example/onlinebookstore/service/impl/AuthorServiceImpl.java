package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.model.Author;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.repository.AuthorRepository;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(String authorId) {
        return authorRepository.findByAuthorId(authorId);
    }

    @Override
    public Author createAuthor(Author newAuthor) {
        Author existingAuthor = authorRepository.findByAuthorId(newAuthor.getAuthorId());

        if (existingAuthor == null) {
            authorRepository.save(newAuthor);
            return newAuthor;
        } else {
            return null;
        }
    }

    @Override
    public Author updateAuthor(String authorId, Author updatedAuthor) {
        Author existingAuthor = authorRepository.findByAuthorId(authorId);

        if (existingAuthor != null) {
            updatedAuthor.setAuthorId(existingAuthor.getAuthorId());
            authorRepository.save(updatedAuthor);
            return updatedAuthor;
        } else {
            return null;
        }
    }

    @Override
    public String deleteAuthor(String authorId) {
        Boolean existingAuthor = authorRepository.existsByAuthorId(authorId);

        if (existingAuthor) {
            authorRepository.deleteByAuthorId(authorId);
            return "Deleted Successfully";
        } else {
            return "AuthorId not found, provide a valid authorId";
        }
    }
}
