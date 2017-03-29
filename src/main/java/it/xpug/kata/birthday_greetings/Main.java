package it.xpug.kata.birthday_greetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {

		IEmployeeDataSource employeeDataSource = new EmployeeDataSource();
		IMessagingService messagingService = new MessagingService();

		BirthdayService service = new BirthdayService(
				employeeDataSource,
				messagingService);
		service.sendGreetings("employee_data.txt", new XDate(), "localhost", 25);
	}

}
