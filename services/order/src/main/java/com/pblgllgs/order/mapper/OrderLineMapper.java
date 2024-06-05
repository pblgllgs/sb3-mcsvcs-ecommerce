package com.pblgllgs.order.mapper;
/*
 *
 * @author pblgl
 * Created on 05-06-2024
 *
 */

import com.pblgllgs.order.dto.OrderLineRequest;
import com.pblgllgs.order.entity.Order;
import com.pblgllgs.order.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .productId(orderLineRequest.productId())
                .build();
    }
}
