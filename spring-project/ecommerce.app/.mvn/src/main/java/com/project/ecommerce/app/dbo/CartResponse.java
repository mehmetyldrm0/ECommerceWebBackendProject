package com.project.ecommerce.app.dbo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecommerce.app.entities.CartItem;
import com.project.ecommerce.app.entities.Customer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
