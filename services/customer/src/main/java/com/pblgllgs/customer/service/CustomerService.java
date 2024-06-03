package com.pblgllgs.customer.service;
/*
 *
 * @author pblgl
 * Created on 03-06-2024
 *
 */

import com.pblgllgs.customer.dto.CustomerRequest;
import com.pblgllgs.customer.dto.CustomerResponse;
import com.pblgllgs.customer.entity.Customer;
import com.pblgllgs.customer.exception.CustomerNotFoundException;
import com.pblgllgs.customer.mapper.CustomerMapper;
import com.pblgllgs.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public String create(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        Customer customerInDb = customerRepository.findById(customerRequest.id()).orElseThrow(() ->
                new CustomerNotFoundException(String.format("Customer with id %s not found", customerRequest.id())));
        mergerCustomer(customerInDb, customerRequest);
        customerRepository.save(customerInDb);
    }

    private void mergerCustomer(Customer customerInDb, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstname())) {
            customerInDb.setFirstname(customerRequest.firstname());
        }
        if (StringUtils.isNotBlank(customerRequest.lastname())) {
            customerInDb.setLastname(customerRequest.lastname());
        }
        if (StringUtils.isNotBlank(customerRequest.email())) {
            customerInDb.setEmail(customerRequest.email());
        }
        if (customerRequest.address() != null) {
            customerInDb.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(
                customerMapper::toCustomerResponse
        ).toList();
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse getCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(() ->
                        new CustomerNotFoundException(String.format("Customer with id %s not found", customerId))
                );
    }

    public void deleteByCustomerId(String customerId) {
        boolean present = customerRepository.findById(customerId).isPresent();
        if (!present) {
            throw new CustomerNotFoundException(String.format("Customer with id %s not found", customerId));
        }
        customerRepository.deleteById(customerId);
    }
}
