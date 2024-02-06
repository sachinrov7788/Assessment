package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.model.Orderdto;
import com.example.onlinebookstore.repository.OrderItemRepository;
import com.example.onlinebookstore.repository.OrderRepository;
import com.example.onlinebookstore.service.InventoryService;
import com.example.onlinebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Date Date = null;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public Order createOrder(Orderdto newOrder) {
        try {
            Order order = new Order();
            order.setId(newOrder.getId());
            order.setOrderDate(newOrder.getOrderDate());
            order.setOrderId(newOrder.getOrderId());
            order.setUserId(newOrder.getUserId());
            Order existingOrder = orderRepository.findByOrderId(order.getOrderId());
            OrderItem orderItem = new OrderItem();
            ArrayList<OrderItem> orderItemsArray = newOrder.getOrderItemId();
            ArrayList<String> arr = new ArrayList<>();
            if (existingOrder == null) {
                for(int i=0;i<orderItemsArray.size();i++){
                arr.add(orderItemRepository.save(orderItemsArray.get(i)).getOrderItemId());
                }
                order.setOrderItemId(arr); 
                order.setOrderDate(new Date());
                orderRepository.save(order);
                return order;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating the order: " + e.getMessage());
        }
    }

    @Override
    public Order updateOrder(String orderId, Order updatedOrder) {
        try {
            Order existingOrder = orderRepository.findByOrderId(orderId);

            if (existingOrder != null) {
                updatedOrder.setOrderId(existingOrder.getOrderId());
                orderRepository.save(updatedOrder);
                return updatedOrder;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating the order: " + e.getMessage());
        }
    }

    @Override
    public String deleteOrder(String orderId) {
        try {
            Boolean existingOrder = orderRepository.existsByOrderId(orderId);

            if (existingOrder) {
                orderRepository.deleteByOrderId(orderId);
                return "Deleted Successfully";
            } else {
                return "OrderId not found, provide a valid orderId";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting the order: " + e.getMessage());
        }
    }

    @Override
    public List<Order> getOrdersByOrderItemId(String orderItemId) {
        return orderRepository.findByOrderItemId(orderItemId);
    }

    @Override
    public List<Order> getOrdersBetweenDates(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

}
