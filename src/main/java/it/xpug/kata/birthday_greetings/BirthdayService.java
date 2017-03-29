package it.xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {

	private final IMessagingService messagingService;
	private final IEmployeeDataSource employeeDataSource;

	public BirthdayService(IEmployeeDataSource employeeDataSource, IMessagingService messagingService) {
		this.employeeDataSource = employeeDataSource;
		this.messagingService = messagingService;
	}

	public void sendGreetings(DateWrapper dateWrapper, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {
		List<Employee> employees = employeeDataSource.getEmployeesBornToday(dateWrapper);
		for (Employee employee : employees) {
			String recipient = employee.getEmail();
			String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
			String subject = "Happy Birthday!";
			sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient);
		}
	}

	private void sendMessage(String smtpHost, int smtpPort, String sender, String subject, String body, String recipient) throws AddressException, MessagingException {
		// Create a mail session
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "" + smtpPort);
		Session session = Session.getInstance(props, null);

		// Construct the message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(sender));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		msg.setSubject(subject);
		msg.setText(body);

		// Send the message
		Transport.send(msg);
	}
}
