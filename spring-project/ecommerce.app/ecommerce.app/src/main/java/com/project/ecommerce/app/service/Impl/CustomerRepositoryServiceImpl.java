package com.project.ecommerce.app.service.Impl;

import com.project.ecommerce.app.dbo.CustomerResponse;
import com.project.ecommerce.app.entities.Customer;
import com.project.ecommerce.app.repos.CustomerRepository;
import com.project.ecommerce.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerRepositoryServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse getCustomerById(Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        return CustomerResponse.builder()
                .cart(customer.getCart())
                .customerName(customer.getCustomerName())
                .orders(customer.getOrders())
                .build();
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        var customers = customerRepository.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerResponse customerResponse = CustomerResponse.builder()
                    .customerName(customer.getCustomerName())
                    .cart(customer.getCart())
                    .orders(customer.getOrders())
                    .build();

            customerResponses.add(customerResponse);
        }

        return customerResponses;
    }

    @Override
    public CustomerResponse addCustomer(Customer customer) {
        customer = new Customer();

        CustomerResponse customerResponse = CustomerResponse.builder()
                .customerName(customer.getCustomerName())
                .orders(customer.getOrders())
                .cart(customer.getCart())
                .build();

        customerRepository.save(customer);

        return customerResponse;

    }
}
