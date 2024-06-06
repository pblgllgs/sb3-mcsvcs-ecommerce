package com.pblgllgs.order.controller;

import com.pblgllgs.order.dto.OrderLineResponse;
import com.pblgllgs.order.entity.OrderLine;
import com.pblgllgs.order.service.OrderLineService;
import com.pblgllgs.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *
 * @author pblgl
 * Created on 06-06-2024
 *
 */
@RestController
@RequestMapping("/í/v1/order-line")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;
    private final OrderService orderService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findAllByOrderId(@PathVariable("order-id") Integer orderId) {
        return new ResponseEntity<>(orderService.findAllByOrderId(orderId), HttpStatus.OK);
    }
}
