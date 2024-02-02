package com.project.ecommerce.app.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreateRequest {
    private String customerName;
    private String email;
}
