package com.fmob.webcrawler.service;

import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.repositories.base.UserRepositoryBase;
import com.fmob.webcrawler.service.base.UserServiceBase;
import com.fmob.webcrawler.util.base.EmailServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceBase {
    private UserRepositoryBase<User> userRepository;
    private EmailServiceBase emailService;

    @Autowired
    public UserService(UserRepositoryBase<User> userRepository, EmailServiceBase emailService){
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public String saveNew(User user) {
        user.setConfirmed(false);
        String response = this.userRepository.saveNew(user);
        if (response.equals("Success")){
            emailService.sendEmail(user, "", "");
        }
        return response;
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
