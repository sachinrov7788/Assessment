package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.repository.OrderRepository;
import com.example.onlinebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Date Date = null;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public Order createOrder(Order newOrder) {
        Order existingOrder = orderRepository.findByOrderId(newOrder.getOrderId());

        if (existingOrder == null) {
            newOrder.setOrderDate(new Date());
            orderRepository.save(newOrder);
            return newOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order updateOrder(String orderId, Order updatedOrder) {
        Order existingOrder = orderRepository.findByOrderId(orderId);

        if (existingOrder != null) {
            updatedOrder.setOrderId(existingOrder.getOrderId());
            orderRepository.save(updatedOrder);
            return updatedOrder;
        } else {
            return null;
        }
    }

    @Override
    public String deleteOrder(String orderId) {
        Boolean existingOrder = orderRepository.existsByOrderId(orderId);

        if (existingOrder) {
            orderRepository.deleteByOrderId(orderId);
            return "Deleted Successfully";
        } else {
            return "OrderId not found, provide a valid orderId";
        }
    }

    @Override
    public List<Order> getOrdersByOrderItemId(String orderItemId) {
        return orderRepository.findByOrderItemId(orderItemId);
    }
}
