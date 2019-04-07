package com.fmob.webcrawler.service;

import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.repositories.base.UserRepositoryBase;
import com.fmob.webcrawler.service.base.UserServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceBase {
    private UserRepositoryBase<User> userRepository;

    @Autowired
    public UserService(UserRepositoryBase<User> userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public String saveNew(User user) {
        return null;
    }

    @Override
    public List<User> getAllByOriginAndDestination(String origin, String destination) {
        return null;
    }

    @Override
    public void confirmUser(User user) {

    }
}
