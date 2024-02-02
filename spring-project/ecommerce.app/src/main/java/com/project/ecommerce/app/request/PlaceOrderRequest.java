package com.project.ecommerce.app.request;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderRequest {
    private Long customerId;
    private List<OrderItemRequest> orderItems;
}
