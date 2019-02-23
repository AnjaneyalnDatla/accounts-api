package com.srkr.accounts.accountsapi.usecases;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.naming.NameNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.Contacts;
import com.srkr.accounts.domain.model.Organisation;
import com.srkr.accounts.usecases.FindAndSaveContacts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindAndSaveContactsTest {

	@Autowired
	private FindAndSaveContacts findAndSaveContacts;

	private List<Contacts> contactsList;

	private long id;

	@Before
	public void setUp() {
		Random rand = new Random();
		this.contactsList = new ArrayList<>();
		id = rand.nextLong();
		Contacts contact = new Contacts(id, "supplemId", true, "AFS system", "Lara", "N", "Brain", "608-609-5525",
				"608-322-5525", "608-213-5525", "", "test@test.in", "water side ln", "Macomb", "MI", "USA", "01545",
				"Patel Bros", "No comments", "License", "WI-21129032", "Engineer", 2000.00,
				new Date((new GregorianCalendar(2019, 2, 9)).getTimeInMillis()), null,
				new Organisation("DEFAULT", "DEFAULT"));
		contactsList.add(contact);
	}

	@Test
	public void createContact() {
		contactsList.forEach(contact -> {
			assertNotNull(findAndSaveContacts.createContact(contact));
		});
	}

	@Test
	public void findAllContacts() {
		assertNotNull(findAndSaveContacts.findAllContacts());
	}

	@Test
	public void findContacts() {
		try {
			assertNotNull(findAndSaveContacts.findContacts("Lara", "Brain"));
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findContactById() {
		assertNotNull(findAndSaveContacts.findContactById(id));
	}
}
