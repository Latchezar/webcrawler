package com.fmob.webcrawler.repositories;

import com.fmob.webcrawler.models.SentEmail;
import com.fmob.webcrawler.repositories.base.SentEmailRepositoryBase;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SentEmailRepository<T> implements SentEmailRepositoryBase<T> {
    private SessionFactory sessionFactory;

    @Autowired
    public SentEmailRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveEmail(T email) {
        if (email instanceof SentEmail) {
            SentEmail emailToSave = (SentEmail) email;
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.save(emailToSave);
                session.getTransaction().commit();
                session.close();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Incompatible object: not SentEmail.class");
        }
    }
}
