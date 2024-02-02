package com.project.ecommerce.app.service.Impl;

import com.project.ecommerce.app.entities.Customer;
import com.project.ecommerce.app.repos.CustomerRepository;
import com.project.ecommerce.app.service.CustomerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerRepositoryServiceImpl implements CustomerRepositoryService {

    private CustomerRepository customerRepository;
    private CustomerRepositoryService customerRepositoryService;
    @Lazy
    @Autowired
    public CustomerRepositoryServiceImpl(CustomerRepository customerRepository, CustomerRepositoryService customerRepositoryService) {
        this.customerRepository = customerRepository;
        this.customerRepositoryService = customerRepositoryService;
    }
    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
