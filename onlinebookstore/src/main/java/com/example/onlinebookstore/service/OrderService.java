package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.Orderdto;

import java.util.Date;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(String orderId);

    Order createOrder(Orderdto newOrder);

    Order updateOrder(String orderId, Order updatedOrder);

    String deleteOrder(String orderId);

    List<Order> getOrdersByOrderItemId(String orderItemId);

    List<Order> getOrdersBetweenDates(Date startDate, Date endDate);

}
