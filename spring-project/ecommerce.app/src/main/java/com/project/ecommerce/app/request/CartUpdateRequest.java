package com.project.ecommerce.app.request;

import lombok.Data;
import java.util.List;

@Data
public class CartUpdateRequest {
    private Long cartId;
    private List<CartItemRequest> cartItemRequests;
}
