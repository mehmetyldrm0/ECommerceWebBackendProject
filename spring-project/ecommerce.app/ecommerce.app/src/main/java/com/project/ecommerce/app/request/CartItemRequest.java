package com.project.ecommerce.app.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemRequest {
    private Long productId;
    private int quantity;
    private double totalPrice;
}
