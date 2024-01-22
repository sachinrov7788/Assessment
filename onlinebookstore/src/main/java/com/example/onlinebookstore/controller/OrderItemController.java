package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("getbyorderitemid/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable String orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItemById(orderItemId);

        if (orderItem != null) {
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem newOrderItem) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(newOrderItem);

        if (createdOrderItem != null) {
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable String orderItemId,
                                                    @RequestBody OrderItem updatedOrderItem) {
        OrderItem updatedItem = orderItemService.updateOrderItem(orderItemId, updatedOrderItem);

        if (updatedItem != null) {
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{orderItemId}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable String orderItemId) {
        String result = orderItemService.deleteOrderItem(orderItemId);

        if (result.equals("Deleted Successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-book/{bookId}")
    public List<OrderItem> getOrderItemsByBookId(@PathVariable String bookId) {
        return orderItemService.getOrderItemsByBookId(bookId);
    }
}
