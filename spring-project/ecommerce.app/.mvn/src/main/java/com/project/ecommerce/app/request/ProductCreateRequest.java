package com.project.ecommerce.app.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateRequest {
    private String productName;
    private double price;
    private Integer quantity;
}
