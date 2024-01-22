package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getAllPublishers();

    Publisher getPublisherById(String publisherId);

    Publisher createPublisher(Publisher newPublisher);

    Publisher updatePublisher(String publisherId, Publisher updatedPublisher);

    String deletePublisher(String publisherId);
}
