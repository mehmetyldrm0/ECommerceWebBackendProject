package com.project.ecommerce.app.repos;

import com.project.ecommerce.app.dbo.CartResponse;
import com.project.ecommerce.app.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    CartResponse findByCustomerId(Long customerId);
}
