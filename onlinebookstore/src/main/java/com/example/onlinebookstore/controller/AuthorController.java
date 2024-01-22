package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.model.Author;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.service.AuthorService;
import com.example.onlinebookstore.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
    
    @GetMapping("getauthorbyid/{authorId}")
    public ResponseEntity<Author> getAuthorById(@PathVariable String authorId) {
        Author author = authorService.getAuthorById(authorId);
        if(author!=null){
            return new ResponseEntity<>(author,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author newAuthor) {
        Author createdAuthor = authorService.createAuthor(newAuthor);
        if(createdAuthor!=null){
            return new ResponseEntity<>(createdAuthor, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable String authorId, @RequestBody Author updatedAuthor) {
        Author author = authorService.updateAuthor(authorId, updatedAuthor);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String authorId) {
        String result = authorService.deleteAuthor(authorId);
        if (result.equals("Deleted Successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}