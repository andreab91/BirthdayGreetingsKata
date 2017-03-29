package it.xpug.kata.birthday_greetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {

		IEmployeeDataSource employeeDataSource = new EmployeeFileDataSource("employee_data.txt");
		IMessagingService messagingService = new MessagingService();

		BirthdayService service = new BirthdayService(
				employeeDataSource,
				messagingService);
		service.sendGreetings(new DateWrapper(), "localhost", 25);
	}

}
