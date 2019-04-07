package com.fmob.webcrawler.service;

import com.fmob.webcrawler.models.Frequency;
import com.fmob.webcrawler.repositories.base.FrequencyRepositoryBase;
import com.fmob.webcrawler.service.base.FrequencyServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrequencyService implements FrequencyServiceBase {
    private FrequencyRepositoryBase<Frequency> repository;

    @Autowired
    public FrequencyService(FrequencyRepositoryBase<Frequency> repository){
        this.repository = repository;
    }

    @Override
    public List<Frequency> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Frequency getById(int id) {
        return this.repository.getById(id);
    }

    @Override
    public Frequency getByName(String name) {
        return this.repository.getByName(name);
    }
}
