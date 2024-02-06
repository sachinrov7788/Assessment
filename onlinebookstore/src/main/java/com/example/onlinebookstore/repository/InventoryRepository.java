package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory, String> {

    Inventory findByUsername(String username);

    List<Inventory> findByUsernameAndBookIdIn(String username, List<String> bookIds);

    String deleteByUsername(String username);

    Boolean existsByUsername(String username);

    Inventory findByBookId(String bookId);
}
