package com.srkr.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.postgres.Accounts;

public interface PostgresAccountsRepository extends Repository<Accounts, Long> {

	List<Accounts> findAll();
	
	@Transactional
	Accounts findByName(String name);
	
	@Transactional
	Accounts findById(Integer id);

	Accounts save(Accounts accounts);

	Accounts delete(Accounts accounts);

}
