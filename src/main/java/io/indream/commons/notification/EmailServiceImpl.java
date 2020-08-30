package io.indream.commons.notification;

import io.indream.commons.notification.configuration.EmailConfiguration;
import io.indream.commons.notification.exception.EmailException;
import io.indream.commons.notification.model.NotificationRequest;
import io.indream.commons.notification.model.NotificationResponse;
import io.indream.commons.notification.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
@ConditionalOnBean(EmailConfiguration.class)
public class EmailServiceImpl implements MailService {
    private static final Logger LOGGER=LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public NotificationResponse pushNotification(NotificationRequest notificationRequest) throws EmailException {
        LOGGER.debug(Constants.EMAIL_PUSH_STARTED,new Date());
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(notificationRequest.getFrom());
        helper.setTo(notificationRequest.getTo());
        helper.setSubject(notificationRequest.getSubject());
        helper.setText(notificationRequest.getContent());
        File attachmentFile = notificationRequest.getFile();
        if(attachmentFile !=null && attachmentFile.exists()){
            FileSystemResource file= new FileSystemResource(new File(attachmentFile.getName()));
            helper.addAttachment(attachmentFile.getName(), file);
        }
        } catch (MessagingException e) {
           throw new EmailException(e.getMessage());
        }
        javaMailSender.send(message);
        LOGGER.debug(Constants.EMAIL_PUSH_ENDED,new Date());
        return new NotificationResponse(true, Constants.SUCCESS_EMAIL);
    }
}
