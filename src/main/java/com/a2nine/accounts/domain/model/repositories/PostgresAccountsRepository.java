package com.a2nine.accounts.domain.model.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.a2nine.accounts.domain.model.postgres.Accounts;

public interface PostgresAccountsRepository extends Repository<Accounts, Long> {

	@Transactional
	List<Accounts> findByOrgcode(String orgCode);

	@Transactional
	Accounts findByNameAndOrgcode(String name, String orgCode);

	@Transactional
	Accounts findByIdAndOrgcode(Integer id, String orgCode);

	@Transactional
	Accounts save(Accounts accounts);

	@Transactional
	Accounts save(Set<Accounts> accounts);

	@Transactional
	Accounts delete(Accounts accounts);

}
