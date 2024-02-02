package com.project.ecommerce.app.repos;

import com.project.ecommerce.app.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
