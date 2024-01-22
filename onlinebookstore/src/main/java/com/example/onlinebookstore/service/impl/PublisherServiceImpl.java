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
        Publisher existingPublisher = publisherRepository.findByPublisherId(newPublisher.getPublisherId());

        if (existingPublisher == null) {
            publisherRepository.save(newPublisher);
            return newPublisher;
        } else {
            return null;
        }
    }

    @Override
    public Publisher updatePublisher(String publisherId, Publisher updatedPublisher) {
        Publisher existingPublisher = publisherRepository.findByPublisherId(publisherId);

        if (existingPublisher != null) {
            updatedPublisher.setPublisherId(existingPublisher.getPublisherId());
            publisherRepository.save(updatedPublisher);
            return updatedPublisher;
        } else {
            return null;
        }
    }

    @Override
    public String deletePublisher(String publisherId) {
        Boolean existingPublisher = publisherRepository.existsByPublisherId(publisherId);

        if (existingPublisher) {
            publisherRepository.deleteByPublisherId(publisherId);
            return "Deleted Successfully";
        } else {
            return "PublisherId not found, provide a valid publisherId";
        }
    }
}
