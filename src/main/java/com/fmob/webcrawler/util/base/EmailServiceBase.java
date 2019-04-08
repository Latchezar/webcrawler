package com.fmob.webcrawler.util.base;

import com.fmob.webcrawler.models.Flight;
import com.fmob.webcrawler.models.User;

public interface EmailServiceBase {

    void sendEmail(User to, String subject, String text, Flight flight);
}
