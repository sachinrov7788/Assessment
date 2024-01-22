package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.Inventory;

import java.util.List;

public interface InventoryService {

    List<Inventory> getAllInventories();

    Inventory getInventoryByUsername(String username);

    List<Inventory> getVendorInventory(String username);

    Inventory createInventory(Inventory newInventory);

    Inventory updateInventory(String username, Inventory updatedInventory);

    String deleteInventory(String username);
}
