package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.model.Inventory;
import com.example.onlinebookstore.repository.InventoryRepository;
import com.example.onlinebookstore.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryByUsername(String username) {
        return inventoryRepository.findByUsername(username);
    }

    @Override
    public List<Inventory> getVendorInventory(String username) {
        return inventoryRepository.findByUsernameAndBookIdIn(username, getVendorBookIds());
    }

    private List<String> getVendorBookIds() {
        return List.of("B001", "B002", "B003");
    }

    @Override
    public Inventory createInventory(Inventory newInventory) {
        Inventory existingInventory = inventoryRepository.findByUsername(newInventory.getUsername());

        if (existingInventory == null) {
            inventoryRepository.save(newInventory);
            return newInventory;
        } else {
            return null;
        }
    }

    @Override
    public Inventory updateInventory(String username, Inventory updatedInventory) {
        Inventory existingInventory = inventoryRepository.findByUsername(username);

        if (existingInventory != null) {
            updatedInventory.setUsername(existingInventory.getUsername());
            inventoryRepository.save(updatedInventory);
            return updatedInventory;
        } else {
            return null;
        }
    }

    @Override
    public String deleteInventory(String username) {
        Boolean existingInventory = inventoryRepository.existsByUsername(username);

        if (existingInventory) {
            inventoryRepository.deleteByUsername(username);
            return "Deleted Successfully";
        } else {
            return "Username not found, provide a valid username";
        }
    }
}
