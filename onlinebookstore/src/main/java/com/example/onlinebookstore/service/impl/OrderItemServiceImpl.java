package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.repository.OrderItemRepository;
import com.example.onlinebookstore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItemById(String orderItemId) {
        return orderItemRepository.findByOrderItemId(orderItemId);
    }

    @Override
    public OrderItem createOrderItem(OrderItem newOrderItem) {
        try {
            OrderItem existingOrderItem = orderItemRepository.findByOrderItemId(newOrderItem.getOrderItemId());

            if (existingOrderItem == null) {
                orderItemRepository.save(newOrderItem);
                return newOrderItem;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating order item: " + e.getMessage());
        }
    }

    @Override
    public OrderItem updateOrderItem(String orderItemId, OrderItem updatedOrderItem) {
        try {
            OrderItem existingOrderItem = orderItemRepository.findByOrderItemId(orderItemId);

            if (existingOrderItem != null) {
                updatedOrderItem.setOrderItemId(existingOrderItem.getOrderItemId());
                orderItemRepository.save(updatedOrderItem);
                return updatedOrderItem;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating order item: " + e.getMessage());
        }
    }

    @Override
    public String deleteOrderItem(String orderItemId) {
        try {
            Boolean existingOrderItem = orderItemRepository.existsByOrderItemId(orderItemId);

            if (existingOrderItem) {
                orderItemRepository.deleteByOrderItemId(orderItemId);
                return "Deleted Successfully";
            } else {
                return "OrderItemId not found, provide a valid orderItemId";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting order item: " + e.getMessage());
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByBookId(String bookId) {
        return orderItemRepository.findByBookId(bookId);
    }
}
