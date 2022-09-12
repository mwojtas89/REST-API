package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Data
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;

    public void send (final Mail mail) {
        log.info("Starting email preparation .... ");
        try {
            SimpleMailMessage newMail = createMailMassage(mail);
            javaMailSender.send(newMail);
            log.info("Email has been sent");
        } catch (MailException e) {
            log.error("Filed to send email : " + e.getMessage());
        }

    }

    private SimpleMailMessage createMailMassage (final Mail mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getMailTo());
        Optional.ofNullable(mail.getToCc()).ifPresent(simpleMailMessage::setCc);
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getMassage());
        return simpleMailMessage;
    }
}
