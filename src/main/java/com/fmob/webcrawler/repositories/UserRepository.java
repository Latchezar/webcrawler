package com.fmob.webcrawler.repositories;

import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.repositories.base.UserRepositoryBase;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            Predicate originPredicate = builder.equal(root.get("origin"), origin);
            Predicate destinationPredicate = builder.equal(root.get("destination"), destination);
            criteria.select(root).where(builder.and(originPredicate, destinationPredicate));
            List result = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            System.out.println( "Failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void confirmUser(T user) {
        if (user instanceof User) {
            User userToUpdate = (User) user;
            if (userToUpdate.isConfirmed()){
                Session session = sessionFactory.openSession();
                try {
                    session.beginTransaction();
                    session.update(userToUpdate);
                    session.getTransaction().commit();
                    session.close();
                } catch (HibernateException e){
                    System.out.println(e.toString());
                }
            }
        }
        System.out.println("Incompatible object");
    }

    @Override
    public T getUserByEmail(String email) {
        
        return null;
    }
}
