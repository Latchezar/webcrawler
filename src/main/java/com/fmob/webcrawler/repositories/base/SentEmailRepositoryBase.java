package com.fmob.webcrawler.repositories.base;

public interface SentEmailRepositoryBase<T> {

    void saveEmail(T email);
}
