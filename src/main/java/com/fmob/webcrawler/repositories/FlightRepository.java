package com.fmob.webcrawler.repositories;

import com.fmob.webcrawler.repositories.base.FlightRepositoryBase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public List<T> getAllByOriginAndDestination(String origin, String destination) {
        return null;
    }

    @Override
    public List<T> getAllByPrice(double price) {
        return null;
    }

    @Override
    public T getByLowestPriceForOriginAndDestination(String origin, String destination) {
        return null;
    }
}
