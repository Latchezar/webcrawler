package com.fmob.webcrawler.controller.rest;

import com.fmob.webcrawler.service.base.FlightServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private FlightServiceBase flightService;

    @Autowired
    public FlightController(FlightServiceBase flightService){
        this.flightService = flightService;
    }
    
}
