package io.indream.commons.notification.model;

import lombok.Data;

import java.io.File;
import java.util.Map;

@Data
public class NotificationRequest {
    private String content;
    private File file;
    private String from;
    private String to;
    private String subject;
    private Map<String, String> properties;
}
