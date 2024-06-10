package com.pblgllgs.notification.entity;
/*
 *
 * @author pblgl
 * Created on 07-06-2024
 *
 */

import com.pblgllgs.notification.dto.OrderConfirmation;
import com.pblgllgs.notification.dto.PaymentConfirmation;
import com.pblgllgs.notification.enums.NotificationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
