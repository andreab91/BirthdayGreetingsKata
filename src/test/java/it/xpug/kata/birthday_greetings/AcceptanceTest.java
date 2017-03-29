package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;
import org.junit.*;

import com.dumbster.smtp.*;

public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 9999;
	private BirthdayService birthdayService;
	private SimpleSmtpServer mailServer;
	private String fileName = "employee_data.txt";

	@Before
	public void setUp() throws Exception {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);

		IEmployeeDataSource employeeDataSource = new EmployeeFileDataSource(fileName);
		IMessagingService messagingService = new EmailMessagingService("localhost", NONSTANDARD_PORT);

		birthdayService = new BirthdayService(
				employeeDataSource,
				messagingService);
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		birthdayService.sendGreetings(new DateWrapper("2008/10/08"));

		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		birthdayService.sendGreetings(new DateWrapper("2008/01/01"));

		assertEquals("what? messages?", 0, mailServer.getReceivedEmailSize());
	}
}
