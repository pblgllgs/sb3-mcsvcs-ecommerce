package com.pblgllgs.customer.dto;

import com.pblgllgs.customer.entity.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
