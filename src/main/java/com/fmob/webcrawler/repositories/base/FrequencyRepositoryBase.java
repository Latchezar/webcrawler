package com.fmob.webcrawler.repositories.base;

import java.util.List;

public interface FrequencyRepositoryBase<T> {

    List<T> getAll();

    T getById(int id);

    T getByName(String name);
}
