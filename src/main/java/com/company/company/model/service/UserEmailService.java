package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@NotNullByDefault

@Service
public class UserEmailService {

    private JavaMailSender emailSender;
    private SimpleMailMessage message;

    @Lazy
    @Autowired
    public UserEmailService(JavaMailSender emailSender, SimpleMailMessage message) {
        this.emailSender = emailSender;
        this.message = message;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


    public void sendMessageWithAttachment (
            String to, String subject, String text, String pathToAttachment) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        emailSender.send(message);
    }


    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        /*message.setText(
                "This is the test email template for your email:\n%s\n");*/
        return message;
    }
}
