package com.fmob.webcrawler.util.base;

public interface EmailServiceBase {

    void sendEmail(String to, String subject, String text);
}
