package com.a2nine.accounts.domain.model.mappers;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.a2nine.accounts.domain.model.Organisation;
import com.a2nine.accounts.domain.model.postgres.Contacts;

@Component
public class ContactsMapper {

	public Contacts toPostgresObject(com.a2nine.accounts.domain.model.Contacts contacts) {

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
		pgContacts.setCurrent_balance(null != contacts.current_balance() ? contacts.current_balance() : 0.0d);
		pgContacts.setBalance_updated_date(
				null != contacts.balance_updated_date() ? contacts.balance_updated_date() : null);
		pgContacts.setOrgName(null == contacts.organisation() ? null : contacts.organisation().name());
		pgContacts.setOrgcode(null == contacts.organisation() ? null : contacts.organisation().code());
		return pgContacts;

	}

	public com.a2nine.accounts.domain.model.Contacts toDomainObject(Contacts pgContacts) {
		com.a2nine.accounts.domain.model.Contacts contacts = new com.a2nine.accounts.domain.model.Contacts(
				pgContacts.getId(), pgContacts.getSupplementalid(), pgContacts.getIscompany(),
				pgContacts.getCompanyname(), pgContacts.getFirstname(), pgContacts.getMiddlename(),
				pgContacts.getLastname(), pgContacts.getCellphone(), pgContacts.getHomephone(),
				pgContacts.getOfficephone(), pgContacts.getFaxnumber(), pgContacts.getEmailaddress(),
				pgContacts.getStreetaddress(), pgContacts.getCity(), pgContacts.getState(), pgContacts.getCountry(),
				pgContacts.getPostalcode(), pgContacts.getLandmark(), pgContacts.getAdditionalcomments(),
				pgContacts.getIdtype(), pgContacts.getIdnumber(), pgContacts.getDesignation(),
				pgContacts.getCurrent_balance(), pgContacts.getBalance_updated_date(), null,
				new Organisation(pgContacts.getOrgName(), pgContacts.getOrgcode()));
		return contacts;

	}

	public List<com.a2nine.accounts.domain.model.Contacts> toListOfDomainObjects(List<Contacts> pgContacts) {
		List<com.a2nine.accounts.domain.model.Contacts> contacts = new ArrayList<>();
		pgContacts.forEach(c -> {
			contacts.add(toDomainObject(c));
		});
		return contacts;
	}

}
