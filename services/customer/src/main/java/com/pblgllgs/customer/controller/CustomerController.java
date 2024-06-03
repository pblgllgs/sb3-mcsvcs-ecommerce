package com.pblgllgs.customer.controller;
/*
 *
 * @author pblgl
 * Created on 03-06-2024
 *
 */

import com.pblgllgs.customer.dto.CustomerRequest;
import com.pblgllgs.customer.dto.CustomerResponse;
import com.pblgllgs.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ServerProperties serverProperties;

    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.create(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @Valid @RequestBody CustomerRequest customerRequest
    ) {
        customerService.updateCustomer(customerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/exists/${customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String customerId) {
        return new ResponseEntity<>(customerService.existsById(customerId),HttpStatus.OK);
    }

    @GetMapping("/${customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customer-id") String customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId),HttpStatus.OK);
    }

    @DeleteMapping("/${customer-id}")
    public ResponseEntity<Void> deleteById(@PathVariable("customer-id") String customerId) {
        customerService.deleteByCustomerId(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
