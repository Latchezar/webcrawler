package com.fmob.webcrawler.repositories;

import com.fmob.webcrawler.repositories.base.SentEmailRepositoryBase;
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
        
    }
}
