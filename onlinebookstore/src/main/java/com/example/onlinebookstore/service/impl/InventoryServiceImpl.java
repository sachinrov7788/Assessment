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
        try {
            Inventory existingInventory = inventoryRepository.findByUsername(newInventory.getUsername());

            if (existingInventory == null) {
                inventoryRepository.save(newInventory);
                return newInventory;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating inventory: " + e.getMessage());
        }
    }

    @Override
    public Inventory updateInventory(String username, Inventory updatedInventory) {
        try {
            Inventory existingInventory = inventoryRepository.findByUsername(username);

            if (existingInventory != null) {
                updatedInventory.setUsername(existingInventory.getUsername());
                inventoryRepository.save(updatedInventory);
                return updatedInventory;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating inventory: " + e.getMessage());
        }
    }

    @Override
    public String deleteInventory(String username) {
        try {
            Boolean existingInventory = inventoryRepository.existsByUsername(username);

            if (existingInventory) {
                inventoryRepository.deleteByUsername(username);
                return "Deleted Successfully";
            } else {
                return "Username not found, provide a valid username";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting inventory: " + e.getMessage());
        }
    }
}
