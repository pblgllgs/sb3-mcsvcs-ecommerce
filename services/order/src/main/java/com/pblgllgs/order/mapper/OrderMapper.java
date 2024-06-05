package com.pblgllgs.order.mapper;
/*
 *
 * @author pblgl
 * Created on 05-06-2024
 *
 */

import com.pblgllgs.order.dto.OrderRequest;
import com.pblgllgs.order.dto.OrderResponse;
import com.pblgllgs.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .paymentMethod(orderRequest.paymentMethod())
                .totalAmount(orderRequest.amount())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }

}
