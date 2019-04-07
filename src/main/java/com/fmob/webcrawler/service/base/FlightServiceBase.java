package com.fmob.webcrawler.service.base;

import com.fmob.webcrawler.models.Flight;

import java.util.List;

public interface FlightServiceBase {
    String save(Flight flight);

    List<Flight> getAll();

    List<Flight> getAllByOriginAndDestination(String origin, String destination);

    Flight getByLowestPriceForOriginAndDestination(String origin, String destination);
}
