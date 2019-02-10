package com.srkr.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class ContactsTest {

	@Test
	public void TestContacts() {
		Contacts contacts = new Contacts(new Long(123455), "supplemId", true, "AFS system", "Lara", "N", "Brain", "608-609-5525", "608-322-5525", "608-213-5525", "", 
				"test@test.in", "water side ln", "Macomb", "MI", "USA", "01545", "Patel Bros", "No comments", "License", "WI-21129032", "Engineer", 2000.00, 
				new Date((new GregorianCalendar(2019,2,9)).getTimeInMillis()), null);
		assertNotNull(contacts);
		assertNotNull(contacts.documents());
		assertEquals(new Long(123455), contacts.id());
		assertEquals("supplemId",contacts.supplementalId());
		assertEquals(true,contacts.getIsCompany());
		assertEquals("AFS system",contacts.companyName());
		assertEquals("Lara",contacts.firstName());
		assertEquals("N",contacts.middleName());
		assertEquals("Brain",contacts.lastName());
		assertEquals("608-609-5525",contacts.cellPhone());
		assertEquals("608-322-5525",contacts.homePhone());
		assertEquals("608-213-5525",contacts.officePhone());
		assertEquals("test@test.in",contacts.emailAddress());
		assertEquals("water side ln",contacts.streetAddress());
		assertEquals("Macomb",contacts.city());
		assertEquals(State.UNKNOWN,contacts.state());
		assertEquals(Country.UNITED_STATES_OF_AMERICA,contacts.country());
		assertEquals("01545",contacts.postalCode());
		assertEquals("Patel Bros",contacts.landMark());
		assertEquals("No comments",contacts.additionalComments());
		assertEquals("License",contacts.idType());
		assertEquals("WI-21129032",contacts.idNumber());
		assertEquals("Engineer",contacts.designation());
		assertEquals(new Double(2000.00),contacts.current_balance());
		assertEquals(new Date((new GregorianCalendar(2019,2,9)).getTimeInMillis()), contacts.balance_updated_date());
	}
}
