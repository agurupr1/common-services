package io.indream.commons.notification.model;

import org.springframework.util.Assert;

import java.io.File;
import java.util.Map;

public class EmailNotificationBuilder {
    private Map<String, String> properties;
    private String subject;
    private String from;
    private String to;
    private File file;
    private String content;

    private EmailNotificationBuilder() {
    }

    public static EmailNotificationBuilder select() {
        return new EmailNotificationBuilder();
    }

    public EmailNotificationBuilder setEmailSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailNotificationBuilder setEmailFromAddress(String emailFromAddress) {
        this.from = emailFromAddress;
        return this;
    }

    public EmailNotificationBuilder setEmailToAddress(String emailToAddress) {
        this.to = emailToAddress;
        return this;
    }

    public EmailNotificationBuilder setEmailContent(String emailContent) {
        this.content = emailContent;
        return this;
    }

    public EmailNotificationBuilder setEmailAttachmentFile(File fileAttachment) {
        Assert.notNull(fileAttachment,"File cannot be null");
        this.file = fileAttachment;
        return this;
    }

    public EmailNotificationBuilder setEmailAdditionalProps(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public NotificationRequest build() {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setContent(this.content);
        notificationRequest.setFile(this.file);
        notificationRequest.setFrom(this.from);
        notificationRequest.setTo(this.to);
        notificationRequest.setProperties(this.properties);
        notificationRequest.setSubject(this.subject);
        return notificationRequest;
    }
}

