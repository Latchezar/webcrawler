package com.fmob.webcrawler.repositories;

import com.fmob.webcrawler.models.Frequency;
import com.fmob.webcrawler.repositories.base.FrequencyRepositoryBase;
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
public class FrequencyRepository<T> implements FrequencyRepositoryBase<T> {
    private SessionFactory sessionFactory;

    @Autowired
    public FrequencyRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Frequency> criteria = builder.createQuery(Frequency.class);
            criteria.from(Frequency.class);
            List result = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public T getById(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Frequency frequency = session.get(Frequency.class, id);
            session.getTransaction().commit();
            session.close();
            return (T) frequency;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public T getByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Frequency> criteria = builder.createQuery(Frequency.class);
            Root<Frequency> root = criteria.from(Frequency.class);
            Predicate namePredicate = builder.equal(root.get("frequenctPeriod"), name);
            criteria.select(root).where(namePredicate);
            Frequency frequency = session.createQuery(criteria).getSingleResult();
            session.getTransaction().commit();
            session.close();
            return (T) frequency;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
