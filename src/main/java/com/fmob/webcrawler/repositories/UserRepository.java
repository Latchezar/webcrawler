package com.fmob.webcrawler.repositories;

import com.fmob.webcrawler.repositories.base.UserRepositoryBase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository<T> implements UserRepositoryBase<T> {
    @Override
    public String saveNew(T user) {
        return null;
    }

    @Override
    public List<T> getAllByOriginAndDestination(String origin, String destination) {
        return null;
    }

    @Override
    public void confirmUser(T user) {

    }
}
