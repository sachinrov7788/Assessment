package com.example.onlinebookstore.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderdto {
    private String id;
    private String orderId;
    private String userId;
    private ArrayList<OrderItem> orderItemId;
    private Date orderDate;

}
