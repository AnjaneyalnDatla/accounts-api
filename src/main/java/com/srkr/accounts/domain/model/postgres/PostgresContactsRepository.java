package com.srkr.accounts.domain.model.postgres;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface PostgresContactsRepository extends Repository<Contacts, Long> {

	public Contacts save(Contacts contact);

	List<Contacts> findByFirstName(String firstName);

	List<Contacts> findByLastName(String lastName);
	
	List<Contacts> findAll();

}
