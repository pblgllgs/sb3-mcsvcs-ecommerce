package com.pblgllgs.payment.service;

import com.pblgllgs.payment.dto.PaymentNotificationRequest;
import com.pblgllgs.payment.dto.PaymentRequest;
import com.pblgllgs.payment.kafka.NotificationProducer;
import com.pblgllgs.payment.mapper.PaymentMapper;
import com.pblgllgs.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 *
 * @author pblgl
 * Created on 06-06-2024
 *
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstname(),
                        paymentRequest.customer().lastname(),
                        paymentRequest.customer().email()
                ));
        return payment.getId();
    }
}
