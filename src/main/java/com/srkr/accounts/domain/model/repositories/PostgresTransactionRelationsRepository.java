package com.srkr.accounts.domain.model.repositories;

import org.springframework.data.repository.Repository;

import com.srkr.accounts.domain.model.postgres.TransactionRelations;

public interface PostgresTransactionRelationsRepository extends Repository<TransactionRelations, Long>{

	
}
