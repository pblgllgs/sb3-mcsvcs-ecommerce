package com.pblgllgs.customer.mapper;
/*
 *
 * @author pblgl
 * Created on 03-06-2024
 *
 */

import com.pblgllgs.customer.dto.CustomerRequest;
import com.pblgllgs.customer.dto.CustomerResponse;
import com.pblgllgs.customer.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .firstname(customerRequest.firstname())
                .lastname(customerRequest.lastname())
                .email(customerRequest.email())
                .address(customerRequest.address())
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
