package com.project.ecommerce.app.controller;

import com.project.ecommerce.app.entities.Order;
import com.project.ecommerce.app.repos.OrderRepository;
import com.project.ecommerce.app.request.PlaceOrderRequest;
import com.project.ecommerce.app.service.OrderRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private OrderRepositoryService orderRepositoryService;

    @Autowired
    public OrderController(OrderRepositoryService orderRepositoryService) {
        this.orderRepositoryService = orderRepositoryService;
    }

    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@RequestBody PlaceOrderRequest request) {
        try {
            orderRepositoryService.placeOrder(request);
            return new ResponseEntity<>("Order placed successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to place order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderRepositoryService.getOrderById(orderId);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderRepositoryService.getAllOrdersForCustomer(customerId);
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
    }
}
