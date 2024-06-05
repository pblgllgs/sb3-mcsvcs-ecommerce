package com.pblgllgs.order.service;

import com.pblgllgs.order.client.CustomerClient;
import com.pblgllgs.order.client.ProductClient;
import com.pblgllgs.order.dto.*;
import com.pblgllgs.order.exception.BusinessException;
import com.pblgllgs.order.kafka.OrderProducer;
import com.pblgllgs.order.mapper.OrderMapper;
import com.pblgllgs.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest orderRequest) {
        CustomerResponse customerResponse = customerClient.getCustomerById(orderRequest.customerId()).orElseThrow(
                () -> new BusinessException("Can not create order -> No customer with id " + orderRequest.customerId()));
        List<PurchaseResponse> purchasedProducts = productClient.purchaseProduct(orderRequest.products());

        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                customerResponse,
                purchasedProducts
        ));

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Order with id %s not found", orderId)));
    }
}
