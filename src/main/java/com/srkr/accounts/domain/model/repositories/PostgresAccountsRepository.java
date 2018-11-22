package com.srkr.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.srkr.accounts.domain.model.postgres.Accounts;

public interface PostgresAccountsRepository extends Repository<Accounts, Long> {

	List<Accounts> findAll();

	Accounts save(Accounts accounts);

	Accounts delete(Accounts accounts);

}
