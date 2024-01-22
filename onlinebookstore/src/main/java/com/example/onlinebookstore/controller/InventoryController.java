package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.model.Inventory;
import com.example.onlinebookstore.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Inventory> getInventoryByUsername(@PathVariable String username) {
        Inventory inventory = inventoryService.getInventoryByUsername(username);
        if (inventory != null) {
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vendor/{username}")
    public ResponseEntity<List<Inventory>> getVendorInventory(@PathVariable String username) {
        List<Inventory> vendorInventory = inventoryService.getVendorInventory(username);
        return new ResponseEntity<>(vendorInventory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory newInventory) {
        Inventory createdInventory = inventoryService.createInventory(newInventory);
        if (createdInventory != null) {
            return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable String username, @RequestBody Inventory updatedInventory) {
        Inventory inventory = inventoryService.updateInventory(username, updatedInventory);
        if (inventory != null) {
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteInventory(@PathVariable String username) {
        String result = inventoryService.deleteInventory(username);
        if (result.equals("Deleted Successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
