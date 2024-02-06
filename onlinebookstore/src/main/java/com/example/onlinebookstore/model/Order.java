package com.example.onlinebookstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
@Data
public class Order {

    @Id
    private String id;
    private String orderId;
    private String userId;
    private ArrayList<String> orderItemId;
    private Date orderDate;
}
