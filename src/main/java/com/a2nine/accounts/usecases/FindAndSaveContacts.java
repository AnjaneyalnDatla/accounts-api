package com.a2nine.accounts.usecases;

import java.util.List;
import java.util.Set;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a2nine.accounts.domain.model.Contacts;
import com.a2nine.accounts.domain.model.mappers.ContactsMapper;
import com.a2nine.accounts.domain.model.mappers.DocumentMapper;
import com.a2nine.accounts.domain.model.postgres.Document;
import com.a2nine.accounts.domain.model.repositories.PostgresContactsRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresDocumentRepository;

@Service
public class FindAndSaveContacts {

	@Autowired
	private PostgresContactsRepository postgresContactsRepository;
	@Autowired
	private PostgresDocumentRepository postgresDocumentRespository;

	@Autowired
	private ContactsMapper contactsMapper;
	@Autowired
	private DocumentMapper documentMapper;

	@Transactional
	public Contacts createContact(Contacts contact) {
		Contacts contacts = contactsMapper
				.toDomainObject(postgresContactsRepository.save(contactsMapper.toPostgresObject(contact)));
		Set<Document> documents = this.documentMapper.toListPostgresObject(contacts.documents());
		documents.forEach(doc -> {
			postgresDocumentRespository.save(doc);
		});
		contacts.setDocuments(this.documentMapper.toListDomainObject(documents));
		return contacts;
	}

	@Transactional
	public Contacts findContactById(Long contactId, String orgCode) {
		Contacts contacts = contactsMapper
				.toDomainObject(postgresContactsRepository.findByIdAndOrgcode(contactId, orgCode));
		contacts.setDocuments(this.documentMapper
				.toListDomainObject(this.postgresDocumentRespository.findByDocumentReferencerNumber(contactId)));
		return contacts;

	}

	@Transactional
	public List<Contacts> findContacts(String firstName, String lastName, String orgCode) throws NameNotFoundException {
		if (null != lastName) {
			return contactsMapper
					.toListOfDomainObjects(postgresContactsRepository.findByLastnameAndOrgcode(lastName, orgCode));
		} else if (null != firstName) {
			return contactsMapper
					.toListOfDomainObjects(postgresContactsRepository.findByFirstnameAndOrgcode(firstName, orgCode));
		}
		throw new NameNotFoundException();
	}

	@Transactional
	public List<Contacts> findAllContacts(String orgCode) {
		return contactsMapper.toListOfDomainObjects(postgresContactsRepository.findByOrgcode(orgCode));
	}

}
