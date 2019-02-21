package com.srkr.accounts.domain.model.repositories;

import org.springframework.data.repository.Repository;

import com.srkr.accounts.domain.model.postgres.TransactionLog;

public interface PostgresTransactionLogRepository extends Repository<TransactionLog, Long> {
	TransactionLog save(TransactionLog transactionLog);

}
