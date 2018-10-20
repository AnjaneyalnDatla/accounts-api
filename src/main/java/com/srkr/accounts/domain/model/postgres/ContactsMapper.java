package com.srkr.accounts.domain.model.postgres;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ContactsMapper {

	public Contacts toPostgresObject(com.srkr.accounts.domain.model.Contacts contacts) {

		Contacts pgContacts = new Contacts();
		pgContacts.setId(contacts.id());
		pgContacts.setSupplementalId(contacts.supplementalId());
		pgContacts.setIsCompany(contacts.isCompany());
		pgContacts.setCompanyName(contacts.companyName());
		pgContacts.setFirstName(contacts.firstName());
		pgContacts.setMiddleName(contacts.middleName());
		pgContacts.setLastName(contacts.lastName());
		pgContacts.setCellPhone(contacts.cellPhone());
		pgContacts.setHomePhone(contacts.homePhone());
		pgContacts.setOfficePhone(contacts.officePhone());
		pgContacts.setFaxNumber(contacts.faxNumber());
		pgContacts.setEmailAddress(contacts.emailAddress());
		pgContacts.setStreetAddress(contacts.streetAddress());
		pgContacts.setCity(contacts.city());
		pgContacts.setState(contacts.state().abbreviation());
		pgContacts.setCountry(contacts.country().abbreviation());
		pgContacts.setPostalCode(contacts.postalCode());
		pgContacts.setLandMark(contacts.landMark());
		pgContacts.setAdditionalComments(contacts.additionalComments());
		pgContacts.setIdType(contacts.idType());
		pgContacts.setIdNumber(contacts.idNumber());
		pgContacts.setDesignation(contacts.designation());

		return pgContacts;

	}

	public com.srkr.accounts.domain.model.Contacts toDomainObject(Contacts pgContacts) {
		com.srkr.accounts.domain.model.Contacts contacts = new com.srkr.accounts.domain.model.Contacts(
				pgContacts.getId(), pgContacts.getSupplementalId(), pgContacts.getIsCompany(),
				pgContacts.getCompanyName(), pgContacts.getFirstName(), pgContacts.getMiddleName(),
				pgContacts.getLastName(), pgContacts.getCellPhone(), pgContacts.getHomePhone(),
				pgContacts.getOfficePhone(), pgContacts.getFaxNumber(), pgContacts.getEmailAddress(),
				pgContacts.getStreetAddress(), pgContacts.getCity(), pgContacts.getState(),
				pgContacts.getCountry(), pgContacts.getPostalCode(),
				pgContacts.getLandMark(), pgContacts.getAdditionalComments(), pgContacts.getIdType(),
				pgContacts.getIdNumber(), pgContacts.getDesignation());
		return contacts;

	}

	public List<com.srkr.accounts.domain.model.Contacts> toListOfDomainObjects(List<Contacts> pgContacts) {
		List<com.srkr.accounts.domain.model.Contacts> contacts = new ArrayList<>();
		pgContacts.forEach(c -> {
			contacts.add(toDomainObject(c));
		});
		return contacts;
	}

}
