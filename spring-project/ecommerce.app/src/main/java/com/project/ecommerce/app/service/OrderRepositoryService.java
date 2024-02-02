package com.project.ecommerce.app.service;

import com.project.ecommerce.app.entities.Order;
import com.project.ecommerce.app.request.PlaceOrderRequest;

import java.util.List;

public interface OrderRepositoryService {
    void placeOrder(PlaceOrderRequest request);
    Order getOrderById(Long orderId);
    List<Order> getAllOrdersForCustomer(Long customerId);
}
