package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import org.junit.*;



public class DateWrapperTest {
	@Test
	public void getters() throws Exception {
		DateWrapper date = new DateWrapper("1789/01/24");
		assertEquals(1, date.getMonth());
		assertEquals(24, date.getDay());
	}

	@Test
	public void isSameDate() throws Exception {
		DateWrapper date = new DateWrapper("1789/01/24");
		DateWrapper sameDay = new DateWrapper("2001/01/24");
		DateWrapper notSameDay = new DateWrapper("1789/01/25");
		DateWrapper notSameMonth = new DateWrapper("1789/02/25");

		assertTrue("same", date.isSameDay(sameDay));
		assertFalse("not same day", date.isSameDay(notSameDay));
		assertFalse("not same month", date.isSameDay(notSameMonth));
	}

	@Test
	public void equality() throws Exception {
		DateWrapper base = new DateWrapper("2000/01/02");
		DateWrapper same = new DateWrapper("2000/01/02");
		DateWrapper different = new DateWrapper("2000/01/04");

		assertFalse(base.equals(null));
		assertFalse(base.equals(""));
		assertTrue(base.equals(base));
		assertTrue(base.equals(same));
		assertFalse(base.equals(different));
	}

}
