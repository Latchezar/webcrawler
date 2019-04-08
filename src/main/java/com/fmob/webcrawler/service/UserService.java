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
        return this.userRepository.saveNew(user);
    }

    @Override
    public List<User> getAllByOriginAndDestination(String origin, String destination) {
        return this.userRepository.getAllByOriginAndDestination(origin, destination);
    }

    @Override
    public void confirmUser(String email) {
        User user = this.userRepository.getUserByEmail(email);
        if (!user.isConfirmed()) {
            user.setConfirmed(true);
        }
        this.userRepository.confirmUser(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.getAll();
    }
}
