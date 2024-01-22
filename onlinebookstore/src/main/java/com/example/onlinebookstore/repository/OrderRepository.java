package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    Order findByOrderId(String orderId);

    Boolean existsByOrderId(String orderId);

    String deleteByOrderId(String orderId);

    List<Order> findByOrderItemId(String orderItemId);
}
