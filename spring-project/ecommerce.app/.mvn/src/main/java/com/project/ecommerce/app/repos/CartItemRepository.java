package com.project.ecommerce.app.repos;

import com.project.ecommerce.app.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
