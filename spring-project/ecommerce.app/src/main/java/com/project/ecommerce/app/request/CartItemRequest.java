package com.project.ecommerce.app.request;

import lombok.Data;

@Data
public class CartItemRequest {
    private Long productId;
    private int quantity;
}
