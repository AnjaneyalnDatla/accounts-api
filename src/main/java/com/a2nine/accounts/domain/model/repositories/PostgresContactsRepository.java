package com.a2nine.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.a2nine.accounts.domain.model.postgres.Contacts;

public interface PostgresContactsRepository extends Repository<Contacts, Long> {

	public Contacts save(Contacts contact);

	List<Contacts> findByFirstnameAndOrgcode(String firstName, String orgCode);

	List<Contacts> findByLastnameAndOrgcode(String lastName, String orgCode);

	Contacts findByIdAndOrgcode(Long id, String orgCode);

	List<Contacts> findByOrgcode(String orgCode);

}
