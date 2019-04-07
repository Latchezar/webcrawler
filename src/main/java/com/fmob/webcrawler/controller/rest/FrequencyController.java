package com.fmob.webcrawler.controller.rest;

import com.fmob.webcrawler.service.base.FrequencyServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/frequency")
public class FrequencyController {
    private FrequencyServiceBase service;

    @Autowired
    public FrequencyController(FrequencyServiceBase service){
        this.service = service;
    }
}
