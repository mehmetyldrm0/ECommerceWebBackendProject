package com.project.ecommerce.app.dbo;

import com.project.ecommerce.app.entities.Customer;
import com.project.ecommerce.app.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderResponse {

    private Customer customer;

    private List<OrderItem> orderItems;

}
