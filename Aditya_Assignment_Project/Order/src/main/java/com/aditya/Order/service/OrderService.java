package com.aditya.Order.service;

import com.aditya.Order.entity.OrderProduct;

import java.net.URISyntaxException;
import java.util.List;

public interface OrderService {
    List<OrderProduct> getAllOrders();
    OrderProduct getOrderById(Long orderId);
    OrderProduct createOrder(OrderProduct order) throws URISyntaxException;
   // OrderProduct getOrderWithProductInventory(Long orderId);
}
