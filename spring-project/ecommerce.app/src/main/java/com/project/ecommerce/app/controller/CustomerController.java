package com.project.ecommerce.app.controller;

import com.project.ecommerce.app.entities.Customer;
import com.project.ecommerce.app.service.CustomerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    private CustomerRepositoryService customerRepositoryService;

    @Autowired
    public CustomerController(CustomerRepositoryService customerRepositoryService) {
        this.customerRepositoryService = customerRepositoryService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepositoryService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepositoryService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
        customerRepositoryService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
