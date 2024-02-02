package com.project.ecommerce.app.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartCreateRequest {
    private Long customerId;

}
