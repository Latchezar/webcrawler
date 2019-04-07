package com.fmob.webcrawler.controller.web;

import com.fmob.webcrawler.service.base.UserServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("")
public class WebController {
    private UserServiceBase userService;

    @Autowired
    public WebController(UserServiceBase userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/confirm")
    public String confirmEmail(@PathParam("email")String email){
        try {
            this.userService.confirmUser(email);
            return "/confirmed";
        } catch (Exception e) {
            return "/unconfirmed";
        }
    }
}