package com.srkr.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.postgres.Contacts;
import com.srkr.accounts.domain.model.repositories.PostgresContactsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresContactsRepositoryTest {


	@Autowired
	private PostgresContactsRepository contactsRepository;

	private List<Contacts> contactsList;
	private List<Contacts> contactsDeleteList = new ArrayList<>();

	@Before
	public void setUp() {
		this.contactsList = new ArrayList<>();
		
		Contacts pgContacts = new Contacts();
//		pgContacts.setId(5l);
		pgContacts.setSupplementalid("supplemId");
		pgContacts.setIscompany(true);
		pgContacts.setCompanyname("AFS system");
		pgContacts.setFirstname("Lara");
		pgContacts.setMiddlename("N");
		pgContacts.setLastname("Brain");
		pgContacts.setCellphone("608-609-5525");
		pgContacts.setHomephone("608-213-5525");
		pgContacts.setOfficephone("608-132-5525");
		pgContacts.setFaxnumber("");
		pgContacts.setEmailaddress("test@test.in");
		pgContacts.setStreetaddress("water side ln");
		pgContacts.setCity("Macomb");
		pgContacts.setState("MI");
		pgContacts.setCountry("USA");
		pgContacts.setPostalcode("61455");
		pgContacts.setLandmark("Patel Bros");
		pgContacts.setAdditionalcomments("No comments");
		pgContacts.setIdtype("License");
		pgContacts.setIdnumber("WI-21129032");
		pgContacts.setDesignation("Engineer");
		pgContacts.setCurrent_balance(2000.00);
		pgContacts.setBalance_updated_date(new Date());
		
		contactsList.add(pgContacts);
	}

	@Test
	public void findAll() {
		List<Contacts> contacts = contactsRepository.findAll();
		assertNotNull(contacts);
	}
	
	@Test
	public void findAccountsByName() {
		List<Contacts> contacts = contactsRepository.findByFirstname("Lara");
		assertNotNull(contacts);
	}

	@Test
	public void save() {
		contactsList.forEach((contact) -> {
			contactsDeleteList.add(this.contactsRepository.save(contact));
		});
		assertTrue(contactsDeleteList.size() > 0);
	}
	
	@Test
	public void findById() {
		Contacts contacts = contactsRepository.findById(1l);
		assertNotNull(contacts);
	}
	/*@After
	public void after() {
		contactsDeleteList.forEach(contact -> {
			this.contactsRepository.delete(contact);
		});
	}*/


}
