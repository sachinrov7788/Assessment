package com.example.onlinebookstore.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "inventory")
@Data
public class Inventory {

    @Id
    private String id;
    private String username;
    private String bookId;
    private int quantity;
}
