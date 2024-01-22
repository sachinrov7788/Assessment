package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<Publisher, String> {

    Publisher findByPublisherId(String publisherId);

    Boolean existsByPublisherId(String publisherId);

    String deleteByPublisherId(String publisherId);
}
