package com.project.ecommerce.app.request;

import lombok.Data;

@Data
public class RemoveProductFromCartRequest {
    private Long cartId;
    private Long productId;
}
