package com.project.ecommerce.app.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlaceOrderRequest {
    private Long customerId;
    private List<OrderItemRequest> orderItems;
}
