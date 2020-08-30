package io.indream.commons.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationResponse {
    private boolean isNotified;
    private String message;
}
