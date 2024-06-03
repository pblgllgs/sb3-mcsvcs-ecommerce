package com.pblgllgs.customer.dto;

import com.pblgllgs.customer.entity.Address;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer first name cant be null")
        String firstname,
        @NotNull(message = "Customer last name cant be null")
        String lastname,
        @NotNull(message = "Customer Email name cant be null")
        String email,
        Address address
) {
}
