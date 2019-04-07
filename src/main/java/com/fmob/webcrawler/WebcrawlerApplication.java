package com.fmob.webcrawler;

import com.fmob.webcrawler.models.Flight;
import com.fmob.webcrawler.models.Frequency;
import com.fmob.webcrawler.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
