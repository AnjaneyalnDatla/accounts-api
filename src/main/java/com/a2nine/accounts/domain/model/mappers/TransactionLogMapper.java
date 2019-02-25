package com.a2nine.accounts.domain.model.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.a2nine.accounts.domain.model.postgres.TransactionLog;
import com.a2nine.accounts.domain.model.repositories.PostgresTransactionLogRepository;

@Component
public class TransactionLogMapper {

	@Autowired
	PostgresTransactionLogRepository postgresTransactionLogRepository;
	
	@Autowired
	private TransactionTypesAndStatusMapper transactionTypesAndStatusMapper;
	
	@Autowired
	private TransactionsMapper transactionsMapper;
	
	public TransactionLog toPostgresObject(com.a2nine.accounts.domain.model.TransactionLog transactionLog) {
		TransactionLog pgTransactionLog = new TransactionLog();
		pgTransactionLog.setId(transactionLog.id());
		pgTransactionLog.setTransactions(transactionsMapper.toPostgresObject(transactionLog.transactions()));
		pgTransactionLog.setTransactionStatus(
				transactionTypesAndStatusMapper.toPostgresObject(transactionLog.transactionStatus()));
		pgTransactionLog.setStatusDate(transactionLog.statusDate());
		pgTransactionLog.setUserId(transactionLog.user_id());
		pgTransactionLog.setComment(transactionLog.comment());
		return pgTransactionLog;
	}

	public com.a2nine.accounts.domain.model.TransactionLog toDomainObject(TransactionLog retPgTransactionLog) {
		// TODO Auto-generated method stub
		return new com.a2nine.accounts.domain.model.TransactionLog(retPgTransactionLog.getId(),
				this.transactionsMapper.toDomainObject(retPgTransactionLog.getTransactions()),
				this.transactionTypesAndStatusMapper.toDomainObject(retPgTransactionLog.getTransactionStatus()),
				retPgTransactionLog.getStatusDate(),retPgTransactionLog.getUserId(), retPgTransactionLog.getComment());
	}
}
