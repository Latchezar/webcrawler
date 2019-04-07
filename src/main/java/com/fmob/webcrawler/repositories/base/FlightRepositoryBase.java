package com.fmob.webcrawler.repositories.base;

import java.util.List;

public interface FlightRepositoryBase<T> {

    String save(T flight);

    List<T> getAll();

    List<T> getAllByOriginAndDestination(String origin, String destination);

    T getByLowestPriceForOriginAndDestination(String origin, String destination);


}
