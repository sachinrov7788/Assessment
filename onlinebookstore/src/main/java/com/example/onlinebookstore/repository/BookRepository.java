package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    Book findByBookId(String bookId);

    Boolean existsByBookId(String bookId);

    String deleteByBookId(String bookId);

    List<Book> findByAuthorId(String authorId);

    List<Book> findByPublisherId(String publisherId);
}
