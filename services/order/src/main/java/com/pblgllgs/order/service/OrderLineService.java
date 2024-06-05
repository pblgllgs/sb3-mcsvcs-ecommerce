package com.pblgllgs.order.service;
/*
 *
 * @author pblgl
 * Created on 05-06-2024
 *
 */

import com.pblgllgs.order.dto.OrderLineRequest;
import com.pblgllgs.order.mapper.OrderLineMapper;
import com.pblgllgs.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
