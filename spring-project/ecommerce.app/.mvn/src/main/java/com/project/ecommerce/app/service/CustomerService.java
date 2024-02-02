package com.project.ecommerce.app.service;

import com.project.ecommerce.app.dbo.CustomerResponse;
import com.project.ecommerce.app.entities.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse addCustomer(Customer customer);

}
