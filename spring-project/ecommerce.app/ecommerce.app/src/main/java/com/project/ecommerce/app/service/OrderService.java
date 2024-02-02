package com.project.ecommerce.app.service;

import com.project.ecommerce.app.dbo.OrderResponse;
import com.project.ecommerce.app.entities.Order;
import com.project.ecommerce.app.request.PlaceOrderRequest;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(PlaceOrderRequest request);
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> getAllOrdersForCustomer(Long customerId);
}
