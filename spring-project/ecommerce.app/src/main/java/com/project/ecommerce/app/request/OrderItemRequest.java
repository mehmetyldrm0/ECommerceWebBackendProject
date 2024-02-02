package com.project.ecommerce.app.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private int quantity;
    private double unitPrice;
}
