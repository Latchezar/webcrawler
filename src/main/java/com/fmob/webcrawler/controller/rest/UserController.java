package com.fmob.webcrawler.controller.rest;

import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.service.base.UserServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserServiceBase userService;

    @Autowired
    public UserController(UserServiceBase userService){
        this.userService = userService;
    }

    @PostMapping("/create-new")
    public String createNewUser(@RequestBody User user){
        return this.userService.saveNew(user);
    }
}
