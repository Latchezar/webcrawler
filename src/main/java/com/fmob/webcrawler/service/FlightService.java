package com.fmob.webcrawler.service;

import com.fmob.webcrawler.models.Flight;
import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.repositories.base.FlightRepositoryBase;
import com.fmob.webcrawler.repositories.base.UserRepositoryBase;
import com.fmob.webcrawler.service.base.FlightServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements FlightServiceBase {
    private FlightRepositoryBase<Flight> flightRepository;
    private UserRepositoryBase<User> userRepository;

    @Autowired
    public FlightService(FlightRepositoryBase<Flight> flightRepository, UserRepositoryBase<User> userRepository){
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String save(Flight flight) {
        return this.flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAll() {
        return this.flightRepository.getAll();
    }

    @Override
    public List<Flight> getAllByOriginAndDestination(String origin, String destination) {
        return this.flightRepository.getAllByOriginAndDestination(origin, destination);
    }

    @Override
    public Flight getByLowestPriceForOriginAndDestination(String origin, String destination) {
        return this.flightRepository.getByLowestPriceForOriginAndDestination(origin, destination);
    }

    private void checkOffers(){

    }
}
