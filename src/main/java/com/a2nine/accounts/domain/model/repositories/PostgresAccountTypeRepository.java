package com.a2nine.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.a2nine.accounts.domain.model.postgres.AccountTypes;

public interface PostgresAccountTypeRepository extends Repository<AccountTypes, Long> {

	List<AccountTypes> findAll();

}
