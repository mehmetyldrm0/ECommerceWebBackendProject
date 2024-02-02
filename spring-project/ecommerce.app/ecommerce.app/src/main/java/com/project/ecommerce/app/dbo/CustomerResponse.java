package com.project.ecommerce.app.dbo;

import com.project.ecommerce.app.entities.Cart;
import com.project.ecommerce.app.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerResponse {

    private String customerName;

    private Cart cart;

    private List<Order> orders;
}
