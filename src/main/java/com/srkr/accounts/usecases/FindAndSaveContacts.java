package com.srkr.accounts.usecases;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.Contacts;
import com.srkr.accounts.domain.model.mappers.ContactsMapper;
import com.srkr.accounts.domain.model.repositories.PostgresContactsRepository;

@Service
public class FindAndSaveContacts {

	@Autowired
	private PostgresContactsRepository postgresContactsRepository;

	@Autowired
	private ContactsMapper contactsMapper;

	@Transactional
	public Contacts createContact(Contacts contact) {
		return contactsMapper.toDomainObject(postgresContactsRepository.save(contactsMapper.toPostgresObject(contact)));

	}

	@Transactional
	public Contacts findContactById(Long contactId) {
		return contactsMapper.toDomainObject(postgresContactsRepository.findById(contactId));

	}

	@Transactional
	public List<Contacts> findContacts(String firstName, String lastName) throws NameNotFoundException {
		if (null != lastName) {
			return contactsMapper.toListOfDomainObjects(postgresContactsRepository.findByLastname(lastName));
		} else if (null != firstName) {
			return contactsMapper.toListOfDomainObjects(postgresContactsRepository.findByFirstname(firstName));
		}
		throw new NameNotFoundException();
	}

	@Transactional
	public List<Contacts> findAllContacts() {
		return contactsMapper.toListOfDomainObjects(postgresContactsRepository.findAll());
	}

}
