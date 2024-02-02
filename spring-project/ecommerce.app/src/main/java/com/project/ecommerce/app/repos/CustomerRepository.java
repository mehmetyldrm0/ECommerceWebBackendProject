package com.project.ecommerce.app.repos;

import com.project.ecommerce.app.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
