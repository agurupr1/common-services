package io.indream.commons.notification;

import io.indream.commons.notification.exception.EmailException;
import io.indream.commons.notification.model.NotificationRequest;
import io.indream.commons.notification.model.NotificationResponse;

public interface MailService {
    NotificationResponse pushNotification(NotificationRequest notificationRequest)throws EmailException;
}
