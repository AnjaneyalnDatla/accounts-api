package com.srkr.accounts.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srkr.accounts.domain.model.Contacts;
import com.srkr.accounts.domain.model.postgres.ContactsMapper;
import com.srkr.accounts.domain.model.postgres.PostgresContactsRepository;

@Service
public class CreateContact {

	@Autowired
	private PostgresContactsRepository postgresContactsRepository;

	@Autowired
	private ContactsMapper contactsMapper;

	public Contacts createContact(Contacts contact) {
		return contactsMapper
				.toDomainObject(postgresContactsRepository.save(contactsMapper.toPostgresObject(contact)));

	}

}
