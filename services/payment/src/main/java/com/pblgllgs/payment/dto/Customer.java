package com.pblgllgs.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Customer first name cant be null")
        String firstname,
        @NotNull(message = "Customer last name cant be null")
        String lastname,
        @NotNull(message = "Customer Email name cant be null")
        @Email(message = "Email is not valid")
        String email
) {
}
