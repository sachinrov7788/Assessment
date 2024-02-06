package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.model.Publisher;
import com.example.onlinebookstore.repository.PublisherRepository;
import com.example.onlinebookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherById(String publisherId) {
        return publisherRepository.findByPublisherId(publisherId);
    }

    @Override
    public Publisher createPublisher(Publisher newPublisher) {
        try {
            Publisher existingPublisher = publisherRepository.findByPublisherId(newPublisher.getPublisherId());

            if (existingPublisher == null) {
                publisherRepository.save(newPublisher);
                return newPublisher;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating the publisher: " + e.getMessage());
        }
    }

    @Override
    public Publisher updatePublisher(String publisherId, Publisher updatedPublisher) {
        try {
            Publisher existingPublisher = publisherRepository.findByPublisherId(publisherId);

            if (existingPublisher != null) {
                updatedPublisher.setPublisherId(existingPublisher.getPublisherId());
                publisherRepository.save(updatedPublisher);
                return updatedPublisher;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating the publisher: " + e.getMessage());
        }
    }

    @Override
    public String deletePublisher(String publisherId) {
        try {
            Boolean existingPublisher = publisherRepository.existsByPublisherId(publisherId);

            if (existingPublisher) {
                publisherRepository.deleteByPublisherId(publisherId);
                return "Deleted Successfully";
            } else {
                return "PublisherId not found, provide a valid publisherId";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting the publisher: " + e.getMessage());
        }
    }
}
