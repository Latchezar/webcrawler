package com.fmob.webcrawler.repositories;

import com.fmob.webcrawler.models.Flight;
import com.fmob.webcrawler.repositories.base.FlightRepositoryBase;
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
public class FlightRepository<T> implements FlightRepositoryBase<T> {
    private SessionFactory sessionFactory;

    @Autowired
    public FlightRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String save(T flight) {
        if (flight instanceof Flight) {
            Flight castedFlight = (Flight) flight;
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.save(castedFlight);
                session.getTransaction().commit();
                session.close();
                return "Success";
            }catch (HibernateException e) {
                return e.getMessage();
            }
        } else {
            return "Incompatible object";
        }
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Flight> criteria = builder.createQuery(Flight.class);
            criteria.from(Flight.class);
            List result = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<T> getAllByOriginAndDestination(String origin, String destination) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Flight> criteria = builder.createQuery(Flight.class);
            Root<Flight> root = criteria.from(Flight.class);
            Predicate originPredicate = builder.equal(root.get("origin"), origin);
            Predicate destinationPredicate = builder.equal(root.get("destination"), destination);
            Predicate finalPredicate = builder.and(originPredicate, destinationPredicate);
            criteria.select(root).where(finalPredicate);
            List result = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public T getByLowestPriceForOriginAndDestination(String origin, String destination) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Flight> criteria = builder.createQuery(Flight.class);
            Root<Flight> root = criteria.from(Flight.class);
            Predicate originPredicate = builder.equal(root.get("origin"), origin);
            Predicate destinationPredicate = builder.equal(root.get("destination"), destination);
            criteria.select(root).where(builder.and(originPredicate, destinationPredicate)).orderBy(builder.asc(root.get("price")));
            List result = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            session.close();
            return (T) result.get(0);

            //there is another way to get the flight with the lowest possible price.
            //one way is to get them all and do the logic with StreamingAPI and get the one with lowest price
            //another way is with different approach here, but using Query with HQL query
            //there are also a few deprecated methods as using a sql query or criteria
            //another way is just using a similar methods as used here(CriteriaBuilder, CriteriaQuery and Root but using builder.function("lowest",...)
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
