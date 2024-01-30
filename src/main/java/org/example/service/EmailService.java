package org.example.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {

    void sendEmail(String email,Integer msg) throws MessagingException;

}
