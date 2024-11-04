package com.example.controller;

import com.example.dto.request.CreateCustomerRequest;
import com.example.dto.response.CustomerResponse;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(
            value = "/customers/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CustomerResponse getCustomer(@PathVariable("id") Long id) {
        return this.customerRepository.findById(id)
                .map(CustomerResponse::of)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                ));
    }

    @PostMapping(
            value = "/customers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        var customer = new Customer();
        customer.setFirstName(createCustomerRequest.firstName());
        customer.setLastName(createCustomerRequest.lastName());
        return CustomerResponse.of(this.customerRepository.save(customer));
    }

}
