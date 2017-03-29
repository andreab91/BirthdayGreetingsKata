package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;

/**
 * Created by andrea on 29/03/17.
 */
public interface IMessagingService {
    void sendMessage(String subject, Greetings greetings) throws MessagingException;
}
