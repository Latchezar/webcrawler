package com.fmob.webcrawler.service;

import com.fmob.webcrawler.models.Flight;
import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.repositories.base.FlightRepositoryBase;
import com.fmob.webcrawler.repositories.base.UserRepositoryBase;
import com.fmob.webcrawler.service.base.FlightServiceBase;
import com.fmob.webcrawler.service.base.UserServiceBase;
import com.fmob.webcrawler.util.base.EmailServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements FlightServiceBase {
    private FlightRepositoryBase<Flight> flightRepository;
    private UserServiceBase userService;
    private EmailServiceBase emailService;

    @Autowired
    public FlightService(FlightRepositoryBase<Flight> flightRepository, UserService userService, EmailServiceBase emailService){
        this.flightRepository = flightRepository;
        this.userService = userService;
        this.emailService = emailService;
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
        List<User> users = this.userService.getAll();
        for (User user :
                users) {
            boolean isEligibleUserForEmail = checkUserEligibility(user);
            if (isEligibleUserForEmail) {
                Flight offer = getBestOfferForUser(user);
            }
        }
    }

    private Flight getBestOfferForUser(User user){
        return this.flightRepository.getByLowestPriceForOriginAndDestination(user.getOrigin(), user.getDestination());
    }

    private boolean checkUserEligibility(User user){
        
    }
}
