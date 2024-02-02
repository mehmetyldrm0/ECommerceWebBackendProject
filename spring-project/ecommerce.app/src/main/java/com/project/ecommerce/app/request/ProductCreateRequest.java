package com.project.ecommerce.app.request;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private String productName;
    private double price;
    private Integer quantity;
}
