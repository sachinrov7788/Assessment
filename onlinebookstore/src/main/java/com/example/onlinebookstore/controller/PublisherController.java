package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.model.Publisher;
import com.example.onlinebookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable String publisherId) {
        Publisher publisher = publisherService.getPublisherById(publisherId);
        if (publisher != null) {
            return new ResponseEntity<>(publisher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher newPublisher) {
        Publisher createdPublisher = publisherService.createPublisher(newPublisher);
        if (createdPublisher != null) {
            return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable String publisherId, @RequestBody Publisher updatedPublisher) {
        Publisher publisher = publisherService.updatePublisher(publisherId, updatedPublisher);
        if (publisher != null) {
            return new ResponseEntity<>(publisher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{publisherId}")
    public ResponseEntity<String> deletePublisher(@PathVariable String publisherId) {
        String result = publisherService.deletePublisher(publisherId);
        if (result.equals("Deleted Successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
