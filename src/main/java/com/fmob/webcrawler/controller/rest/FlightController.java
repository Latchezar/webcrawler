package com.fmob.webcrawler.controller.rest;

import com.fmob.webcrawler.models.Flight;
import com.fmob.webcrawler.service.base.FlightServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private FlightServiceBase flightService;

    @Autowired
    public FlightController(FlightServiceBase flightService){
        this.flightService = flightService;
    }

    @PostMapping("/new")
    public String save(@RequestBody Flight flight){
        return this.flightService.save(flight);
    }

    @GetMapping("/all")
    public List<Flight> getAll(){
        return this.flightService.getAll();
    }

    @GetMapping("/specific")
    public List<Flight> getAllForOriginAndDestination(@PathParam("origin")String origin, @PathParam("destination")String destination){
        return this.flightService.getAllByOriginAndDestination(origin, destination);
    }

    @GetMapping("/lowest")
    public Flight getLowestForOriginAndDestination(@PathParam("origin") String origin, @PathParam("destination") String destination){
        return this.flightService.getByLowestPriceForOriginAndDestination(origin, destination);
    }
}
