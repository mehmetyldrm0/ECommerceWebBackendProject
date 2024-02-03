package com.project.ecommerce.app.dbo;

import com.project.ecommerce.app.entities.CartItem;
import com.project.ecommerce.app.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartResponse {

    private Customer customer;

    private List<CartItem> cartItemList;
}
