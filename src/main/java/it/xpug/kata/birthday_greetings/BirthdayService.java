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

	public void sendGreetings(DateWrapper date) throws IOException, ParseException, AddressException, MessagingException {
		List<Employee> employees = employeeDataSource.getEmployeesBornToday(date);
		for (Employee employee : employees) {
			Greetings greetings = new Greetings(employee);
			messagingService.sendMessage("sender@here.com", greetings);
		}
	}
}
