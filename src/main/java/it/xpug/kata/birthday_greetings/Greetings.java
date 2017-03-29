package it.xpug.kata.birthday_greetings;

/**
 * Created by andrea on 29/03/17.
 */
public class Greetings {
    String recipient;
    String subject;
    String body;

    public Greetings(Employee employee) {
        recipient = employee.getEmail();
        body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
        subject = "Happy Birthday!";
    }
}
