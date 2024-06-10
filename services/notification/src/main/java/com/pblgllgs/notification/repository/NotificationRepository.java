package com.pblgllgs.notification.repository;
/*
 *
 * @author pblgl
 * Created on 07-06-2024
 *
 */

import com.pblgllgs.notification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
