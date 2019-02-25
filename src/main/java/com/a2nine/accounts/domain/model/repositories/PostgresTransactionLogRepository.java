package com.a2nine.accounts.domain.model.repositories;

import org.springframework.data.repository.Repository;

import com.a2nine.accounts.domain.model.postgres.TransactionLog;

public interface PostgresTransactionLogRepository extends Repository<TransactionLog, Long> {
	TransactionLog save(TransactionLog transactionLog);

}
