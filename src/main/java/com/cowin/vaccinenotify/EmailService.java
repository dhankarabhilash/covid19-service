package com.cowin.vaccinenotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendSimpleMessage() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("abhilash.dhankar.1992@gmail.com");
            message.setTo("dhankarabhilash@gmail.com");
            message.setSubject("Vaccine available !!!");
            message.setText("Go to cowin.gov.in NOW...");

            javaMailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

}
