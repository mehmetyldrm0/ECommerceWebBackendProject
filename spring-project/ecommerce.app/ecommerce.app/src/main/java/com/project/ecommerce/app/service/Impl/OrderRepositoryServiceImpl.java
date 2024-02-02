package com.project.ecommerce.app.service.Impl;

import com.project.ecommerce.app.dbo.OrderResponse;
import com.project.ecommerce.app.entities.Order;
import com.project.ecommerce.app.entities.OrderItem;
import com.project.ecommerce.app.repos.CustomerRepository;
import com.project.ecommerce.app.repos.OrderRepository;
import com.project.ecommerce.app.repos.ProductRepository;
import com.project.ecommerce.app.request.OrderItemRequest;
import com.project.ecommerce.app.request.PlaceOrderRequest;
import com.project.ecommerce.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderRepositoryServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponse placeOrder(PlaceOrderRequest request) {
        Order order = new Order();
        order.setCustomer(customerRepository.findById(request.getCustomerId()).orElse(null));

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : request.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productRepository.findById(itemRequest.getProductId()).orElse(null));

            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(itemRequest.getUnitPrice());
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        orderRepository.save(order);


        OrderResponse orderResponse = OrderResponse.builder()
                .customer(order.getCustomer())
                .orderItems(order.getOrderItems())
                .build();
        return orderResponse;

    }


    @Override
    public OrderResponse getOrderById(Long orderId) {
        var order = orderRepository.findById(orderId).orElse(null);

        return OrderResponse.builder()
                .orderItems(order.getOrderItems())
                .customer(order.getCustomer())
                .build();

    }

    @Override
    public List<OrderResponse> getAllOrdersForCustomer(Long customerId) {
        var orders = orderRepository.findByCustomerId(customerId);
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse orderResponse = OrderResponse.builder()
                    .customer(order.getCustomer())
                    .orderItems(order.getOrderItems())
                    .build();
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }
}
