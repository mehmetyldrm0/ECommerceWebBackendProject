package com.project.ecommerce.app.service;

import com.project.ecommerce.app.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryService {
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    void addCustomer(Customer customer);

}
