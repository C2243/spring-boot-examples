package com.example.dto.response;

import com.example.entity.Customer;

public record CustomerResponse(
        String firstName,
        String lastName
) {
    public static CustomerResponse of(Customer customer) {
        return new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName()
        );
    }
}