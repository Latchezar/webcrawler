package com.fmob.webcrawler;

import com.fmob.webcrawler.models.Flight;
import com.fmob.webcrawler.models.Frequency;
import com.fmob.webcrawler.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class WebcrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebcrawlerApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory(){
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Flight.class)
                .addAnnotatedClass(Frequency.class)
                .buildSessionFactory();
    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("fxmchallenge@gmail.com");
        mailSender.setPassword("fxmtest123");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}