package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class BirthdayService {

	private final IMessagingService messagingService;
	private final IEmployeeDataSource employeeDataSource;

	public BirthdayService(IEmployeeDataSource employeeDataSource, IMessagingService messagingService) {
		this.employeeDataSource = employeeDataSource;
		this.messagingService = messagingService;
	}

	public void sendGreetings(DateWrapper date) throws IOException, ParseException, MessagingException {
		List<Employee> employees = employeeDataSource.getEmployeesBornToday(date);
		for (Employee employee : employees) {
			Greetings greetings = new Greetings(employee);
			messagingService.sendMessage("sender@here.com", greetings);
		}
	}
}
