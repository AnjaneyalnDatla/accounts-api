package com.srkr.accounts.usecases;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srkr.accounts.domain.model.Contacts;
import com.srkr.accounts.domain.model.postgres.ContactsMapper;
import com.srkr.accounts.domain.model.postgres.PostgresContactsRepository;

@Service
public class FindContact {

	
	@Autowired
	private PostgresContactsRepository postgresContactsRepository;

	@Autowired
	private ContactsMapper contactsMapper;

	public List<Contacts> findContacts(String firstName,String lastName) throws NameNotFoundException {
		if (null != lastName) {
			return contactsMapper.toListOfDomainObjects(postgresContactsRepository.findByLastName(lastName));
		} else if (null != firstName) {
			return contactsMapper.toListOfDomainObjects(postgresContactsRepository.findByFirstName(firstName));
		}
		throw new NameNotFoundException();
	}
	
	public List<Contacts> findAllContacts() {
			return contactsMapper.toListOfDomainObjects(postgresContactsRepository.findAll());
	}

}
