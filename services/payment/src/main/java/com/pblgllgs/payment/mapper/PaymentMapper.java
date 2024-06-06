package com.pblgllgs.payment.mapper;

import com.pblgllgs.payment.dto.PaymentRequest;
import com.pblgllgs.payment.entity.Payment;
import org.springframework.stereotype.Service;

/*
 *
 * @author pblgl
 * Created on 06-06-2024
 *
 */
@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .build();
    }
}
