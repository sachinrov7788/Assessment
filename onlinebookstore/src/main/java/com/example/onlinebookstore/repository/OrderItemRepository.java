package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {

    OrderItem findByOrderItemId(String orderItemId);

    Boolean existsByOrderItemId(String orderItemId);

    String deleteByOrderItemId(String orderItemId);

    List<OrderItem> findByBookId(String bookId);
}
