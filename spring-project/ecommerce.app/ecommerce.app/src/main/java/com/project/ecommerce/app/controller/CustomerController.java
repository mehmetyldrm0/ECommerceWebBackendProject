package com.project.ecommerce.app.controller;

import com.project.ecommerce.app.dbo.CustomerResponse;
import com.project.ecommerce.app.entities.Customer;
import com.project.ecommerce.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerRepositoryService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        var customers = customerRepositoryService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        var customer = customerRepositoryService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
        customerRepositoryService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
