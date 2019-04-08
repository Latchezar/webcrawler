package com.fmob.webcrawler.service.base;

import com.fmob.webcrawler.models.User;

import java.util.List;

public interface UserServiceBase {

    String saveNew(User user);

    List<User> getAllByOriginAndDestination(String origin, String destination);

    void confirmUser(String email);

    List<User> getAll();
}
