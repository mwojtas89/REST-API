package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void shouldSendEmail () {
        //Given
        Mail mail = Mail.builder()
                .mailTo("test@test.com")
                .subject("test subject")
                .massage("test massage")
                .build();

        SimpleMailMessage newMail = new SimpleMailMessage();
        newMail.setTo(mail.getMailTo());
        newMail.setSubject(mail.getSubject());
        newMail.setText(mail.getMassage());

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(newMail);
    }

    @Test
    void ccTestSuite () {
        //Given
        Mail mail = Mail.builder()
                .mailTo("test@test.com")
                .toCc("cc@cc.com")
                .subject("test subject")
                .massage("test massage")
                .build();
        Mail mailNoCc = Mail.builder()
                .mailTo("test@test.com")
                .subject("test subject")
                .massage("test massage")
                .build();

        SimpleMailMessage newMail = new SimpleMailMessage();
        newMail.setTo(mail.getMailTo());
        Optional.ofNullable(mail.getToCc()).ifPresent(newMail::setCc);
        newMail.setSubject(mail.getSubject());
        newMail.setText(mail.getMassage());

        SimpleMailMessage newMailnoCc = new SimpleMailMessage();
        newMailnoCc.setTo(mailNoCc.getMailTo());
        Optional.ofNullable(mailNoCc.getToCc()).ifPresent(newMailnoCc::setCc);
        newMailnoCc.setSubject(mailNoCc.getSubject());
        newMailnoCc.setText(mailNoCc.getMassage());

        //When
        simpleEmailService.send(mail);
        simpleEmailService.send(mailNoCc);

        //Then
        verify(javaMailSender, times(1)).send(newMail);
        verify(javaMailSender, times(1)).send(newMailnoCc);
        assertNotNull(newMail.getCc());
        assertNull(newMailnoCc.getCc());
    }
}