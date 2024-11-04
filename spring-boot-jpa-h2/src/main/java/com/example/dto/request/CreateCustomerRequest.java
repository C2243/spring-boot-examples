package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName
) {
}
