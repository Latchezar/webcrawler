package com.fmob.webcrawler.repositories;

import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.repositories.base.UserRepositoryBase;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository<T> implements UserRepositoryBase<T> {
    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String saveNew(T user) {
        if (user instanceof User) {
            User userToSave = (User) user;
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.save(userToSave);
                session.getTransaction().commit();
                session.close();
                return "Success";
            } catch (HibernateException e) {
                session.close();
                return "Failed: " + e.getMessage();
            }
        }
        return "incompatible object";
    }

    @Override
    public List<T> getAllByOriginAndDestination(String origin, String destination) {
        return null;
    }

    @Override
    public void confirmUser(T user) {

    }
}
