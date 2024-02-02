package com.project.ecommerce.app.request;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CartUpdateRequest {
    private Long cartId;
    private List<CartItemRequest> cartItemRequests;
}
