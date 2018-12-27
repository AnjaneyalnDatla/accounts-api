package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.Contacts;

@Component
public class ContactsMapper {

	public Contacts toPostgresObject(com.srkr.accounts.domain.model.Contacts contacts) {

		Contacts pgContacts = new Contacts();
		pgContacts.setId(contacts.id());
		pgContacts.setSupplementalid(contacts.supplementalId());
		pgContacts.setIscompany(contacts.getIsCompany());
		pgContacts.setCompanyname(contacts.companyName());
		pgContacts.setFirstname(contacts.firstName());
		pgContacts.setMiddlename(contacts.middleName());
		pgContacts.setLastname(contacts.lastName());
		pgContacts.setCellphone(contacts.cellPhone());
		pgContacts.setHomephone(contacts.homePhone());
		pgContacts.setOfficephone(contacts.officePhone());
		pgContacts.setFaxnumber(contacts.faxNumber());
		pgContacts.setEmailaddress(contacts.emailAddress());
		pgContacts.setStreetaddress(contacts.streetAddress());
		pgContacts.setCity(contacts.city());
		pgContacts.setState(null != contacts.state() ? contacts.state().abbreviation() : null);
		pgContacts.setCountry(null != contacts.country() ? contacts.country().abbreviation() : null);
		pgContacts.setPostalcode(contacts.postalCode());
		pgContacts.setLandmark(contacts.landMark());
		pgContacts.setAdditionalcomments(contacts.additionalComments());
		pgContacts.setIdtype(contacts.idType());
		pgContacts.setIdnumber(contacts.idNumber());
		pgContacts.setDesignation(contacts.designation());
		pgContacts.setCurrent_balance(contacts.current_balance());
		pgContacts.setBalance_updated_date(contacts.balance_updated_date());

		return pgContacts;

	}

	public com.srkr.accounts.domain.model.Contacts toDomainObject(Contacts pgContacts) {
		com.srkr.accounts.domain.model.Contacts contacts = new com.srkr.accounts.domain.model.Contacts(
				pgContacts.getId(), pgContacts.getSupplementalid(), pgContacts.getIscompany(),
				pgContacts.getCompanyname(), pgContacts.getFirstname(), pgContacts.getMiddlename(),
				pgContacts.getLastname(), pgContacts.getCellphone(), pgContacts.getHomephone(),
				pgContacts.getOfficephone(), pgContacts.getFaxnumber(), pgContacts.getEmailaddress(),
				pgContacts.getStreetaddress(), pgContacts.getCity(), pgContacts.getState(), pgContacts.getCountry(),
				pgContacts.getPostalcode(), pgContacts.getLandmark(), pgContacts.getAdditionalcomments(),
				pgContacts.getIdtype(), pgContacts.getIdnumber(), pgContacts.getDesignation(),
				pgContacts.getCurrent_balance(), pgContacts.getBalance_updated_date());
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
