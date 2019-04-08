package com.fmob.webcrawler.service;

import com.fmob.webcrawler.models.Flight;
import com.fmob.webcrawler.models.Frequency;
import com.fmob.webcrawler.models.SentEmail;
import com.fmob.webcrawler.models.User;
import com.fmob.webcrawler.repositories.base.FlightRepositoryBase;
import com.fmob.webcrawler.service.base.FlightServiceBase;
import com.fmob.webcrawler.service.base.UserServiceBase;
import com.fmob.webcrawler.util.base.EmailServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
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
        String respones = this.flightRepository.save(flight);
        if (respones.equals("Success")) {
            checkOffers();
        }
        return respones;
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
            Flight offer = getBestOfferForUser(user);
            boolean isEligibleUserForEmail = checkUserEligibility(user, offer);
            if (isEligibleUserForEmail) {
                String dateString = new Date(offer.getTimestamp()).toString();
                String emailText = "We have a new offer for you: \n Origin: " + offer.getOrigin() + "\n Destination: " + offer.getDestination() + "\n Price: " + offer.getPrice() + "\n Date: " + dateString + "\n Flight number: " + offer.getFlightNumber();
                String subject = "Webcrawler: New offer!";
                this.emailService.sendEmail(user, subject, emailText);
            } else {
                System.out.println("User " + user.getEmail() + " is not yet eligible for a new offer");
            }
        }
    }

    private Flight getBestOfferForUser(User user){
        return this.flightRepository.getByLowestPriceForOriginAndDestination(user.getOrigin(), user.getDestination());
    }

    private boolean checkUserEligibility(User user, Flight bestOffer){
        if (user.getSentEmails().size() > 0) {
            Comparator cmp = Comparator.comparing(SentEmail::getTimestam);
            SentEmail lastEmail = (SentEmail) user.getSentEmails().stream().max(cmp).get();
            long frequency = determineFrequency(user.getFrequency());
            long currentMilliseconds = new Date().getTime();
            long difference = currentMilliseconds - lastEmail.getTimestam();
            return difference > frequency;
        }
        return true;
    }

    private boolean isSentOffer(List<SentEmail> emails, Flight offer){
        for (SentEmail email :
                emails) {
            return false;
        }
        return true;
    }

    private long determineFrequency(Frequency frequency){
        int id = frequency.getFrequencyId();
        switch (id){
            case 1:
                return 86400000L;
            case 2:
                return 604800000L;
            case 3:
                return 2592000000L;
            default:
                return 0;
        }
    }
}
