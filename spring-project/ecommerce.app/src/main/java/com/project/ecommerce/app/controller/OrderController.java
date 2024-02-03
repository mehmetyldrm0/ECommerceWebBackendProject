package com.project.ecommerce.app.controller;

import com.project.ecommerce.app.dbo.OrderResponse;
import com.project.ecommerce.app.request.PlaceOrderRequest;
import com.project.ecommerce.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderRepositoryService;


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
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        var order = orderRepositoryService.getOrderById(orderId);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        var orders = orderRepositoryService.getAllOrdersForCustomer(customerId);
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
    }
}
