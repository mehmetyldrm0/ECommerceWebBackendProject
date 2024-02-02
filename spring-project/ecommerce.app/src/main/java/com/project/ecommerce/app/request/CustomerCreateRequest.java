package com.project.ecommerce.app.request;

import lombok.Data;

@Data
public class CustomerCreateRequest {
    private String customerName;
    private String email;
}
