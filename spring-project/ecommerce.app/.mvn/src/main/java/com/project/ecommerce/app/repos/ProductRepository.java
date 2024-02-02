package com.project.ecommerce.app.repos;

import com.project.ecommerce.app.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
