package com.pblgllgs.order.service;

import com.pblgllgs.order.client.CustomerClient;
import com.pblgllgs.order.client.ProductClient;
import com.pblgllgs.order.dto.CustomerResponse;
import com.pblgllgs.order.dto.OrderRequest;
import com.pblgllgs.order.exception.BusinessException;
import com.pblgllgs.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 *
 * @author pblgl
 * Created on 04-06-2024
 *
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createOrder(OrderRequest orderRequest) {
        CustomerResponse customerResponse = customerClient.getCustomerById(orderRequest.customerId()).orElseThrow(
                () -> new BusinessException("Can not create order -> No customer with id " + orderRequest.customerId()));
        return null;
    }
}
