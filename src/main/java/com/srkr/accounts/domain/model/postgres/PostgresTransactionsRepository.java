package com.srkr.accounts.domain.model.postgres;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface PostgresTransactionsRepository extends Repository<Transactions, Long> {

	List<Transactions> findByUserName(String userName);
	List<Transactions> findAll();

}
