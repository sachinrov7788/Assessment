package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findByBookId(bookId);
    }

    @Override
    public Book createBook(Book newBook) {
        Book existingBook = bookRepository.findByBookId(newBook.getBookId());

        if (existingBook == null) {
            bookRepository.save(newBook);
            return newBook;
        } else {
            return null;
        }
    }

    @Override
    public Book updateBook(String bookId, Book updatedBook) {
        Book existingBook = bookRepository.findByBookId(bookId);

        if (existingBook != null) {
            updatedBook.setBookId(existingBook.getBookId());
            bookRepository.save(updatedBook);
            return updatedBook;
        } else {
            return null;
        }
    }

    @Override
    public String deleteBook(String bookId) {
        Boolean existingBook = bookRepository.existsByBookId(bookId);

        if (existingBook) {
            bookRepository.deleteByBookId(bookId);
            return "Deleted Successfully";
        } else {
            return "BookId not found, provide a valid bookId";
        }
    }

    @Override
    public List<Book> getBooksByAuthor(String authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Book> getBooksByPublisher(String publisherId) {
        return bookRepository.findByPublisherId(publisherId);
    }
}
