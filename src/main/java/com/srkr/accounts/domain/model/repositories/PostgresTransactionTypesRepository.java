package com.srkr.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.srkr.accounts.domain.model.postgres.TransactionTypes;

public interface PostgresTransactionTypesRepository extends Repository<TransactionTypes, Long>{
	
	TransactionTypes save(TransactionTypes transactionTypes);
	
	List<TransactionTypes> findAll();
	
	TransactionTypes findById(Long id);

}
