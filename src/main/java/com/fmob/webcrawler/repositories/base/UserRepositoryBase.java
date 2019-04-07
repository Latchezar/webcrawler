package com.fmob.webcrawler.repositories.base;

import java.util.List;

public interface UserRepositoryBase<T> {

    String saveNew(T user);

    List<T> getAllByOriginAndDestination(String origin, String destination);

    void confirmUser(T user);
}
