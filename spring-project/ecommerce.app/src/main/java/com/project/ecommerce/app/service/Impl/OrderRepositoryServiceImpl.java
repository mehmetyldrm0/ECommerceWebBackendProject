package com.project.ecommerce.app.service.Impl;

import com.project.ecommerce.app.entities.Order;
import com.project.ecommerce.app.entities.OrderItem;
import com.project.ecommerce.app.repos.CustomerRepository;
import com.project.ecommerce.app.repos.OrderRepository;
import com.project.ecommerce.app.repos.ProductRepository;
import com.project.ecommerce.app.request.OrderItemRequest;
import com.project.ecommerce.app.request.PlaceOrderRequest;
import com.project.ecommerce.app.service.OrderRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepositoryServiceImpl implements OrderRepositoryService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    @Autowired
    public OrderRepositoryServiceImpl(OrderRepository orderRepository,
                                      CustomerRepository customerRepository,
                                      ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void placeOrder(PlaceOrderRequest request) {
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
    }


    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
