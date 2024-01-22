package com.example.onlinebookstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_items")
@Data
public class OrderItem {

    @Id
    private String id;
    private String orderItemId;
    private String bookId;  // This represents the book they purchased
    private int quantity;
    private double price;
}
