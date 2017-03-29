package it.xpug.kata.birthday_greetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by andrea on 29/03/17.
 */
public class EmailMessagingService implements IMessagingService {

    private String smtpHost;
    private int smtpPort;

    public EmailMessagingService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public void sendMessage(String sender, Greetings greetings) throws MessagingException {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session mailSession = Session.getInstance(props, null);

        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(greetings.recipient));
        msg.setSubject(greetings.subject);
        msg.setText(greetings.body);

        Transport.send(msg);
    }
}
