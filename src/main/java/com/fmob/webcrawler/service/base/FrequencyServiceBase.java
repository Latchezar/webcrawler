package com.fmob.webcrawler.service.base;

import com.fmob.webcrawler.models.Frequency;

import java.util.List;

public interface FrequencyServiceBase {

    List<Frequency> getAll();

    Frequency getById(int id);

    Frequency getByName(String name);
}
