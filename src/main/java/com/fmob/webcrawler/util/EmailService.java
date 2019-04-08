package com.fmob.webcrawler.util;

import com.fmob.webcrawler.models.SentEmail;
import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.repositories.base.SentEmailRepositoryBase;
import com.fmob.webcrawler.util.base.EmailServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmailService implements EmailServiceBase {
    private JavaMailSender emailSender;
    private SentEmailRepositoryBase<SentEmail> emailRepository;

    @Autowired
    public EmailService(JavaMailSender emailSender, SentEmailRepositoryBase<SentEmail> emailRepository) {
        this.emailSender = emailSender;
        this.emailRepository = emailRepository;
    }

    @Override
    public void sendEmail(User to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to.getEmail());
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        SentEmail email = new SentEmail();
        email.setEmailText(text);
        email.setTimestam(new Date().getTime());
        email.setUserId(to.getUserID());
        this.emailRepository.saveEmail(email);
    }
}
