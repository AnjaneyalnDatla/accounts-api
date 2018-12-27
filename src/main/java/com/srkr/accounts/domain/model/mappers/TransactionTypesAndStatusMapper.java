package com.srkr.accounts.domain.model.mappers;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.TransactionStatus;
import com.srkr.accounts.domain.model.postgres.TransactionTypes;

@Component
public class TransactionTypesAndStatusMapper {

	/**
	 * 
	 * @param transactionTypes
	 * @return
	 */
	public TransactionTypes toPostgresObject(com.srkr.accounts.domain.model.TransactionTypes transactionTypes) {

		TransactionTypes pgtransactionTypes = new TransactionTypes();
		pgtransactionTypes.setId(transactionTypes.id());
		pgtransactionTypes.setName(transactionTypes.name());
		pgtransactionTypes.setDescription(transactionTypes.description());
		return pgtransactionTypes;

	}

	/**
	 * 
	 * @param pgTransactionTypes
	 * @return
	 */
	public com.srkr.accounts.domain.model.TransactionTypes toDomainObject(TransactionTypes pgTransactionTypes) {
		if (pgTransactionTypes == null) {
			return null;
		}
		return new com.srkr.accounts.domain.model.TransactionTypes(pgTransactionTypes.getId(),
				pgTransactionTypes.getName(), pgTransactionTypes.getDescription());
	}

	/**
	 * 
	 * @param transactionStatus
	 * @return
	 */
	public TransactionStatus toPostgresObject(com.srkr.accounts.domain.model.TransactionStatus transactionStatus) {
		TransactionStatus pgTransactionStatus = new TransactionStatus();
		pgTransactionStatus.setId(transactionStatus.id());
		pgTransactionStatus.setValue(transactionStatus.value());
		return pgTransactionStatus;
	}

	/**
	 * 
	 * @param pgTransactionStatus
	 * @return
	 */
	public com.srkr.accounts.domain.model.TransactionStatus toDomainObject(TransactionStatus pgTransactionStatus) {
		if (pgTransactionStatus == null) {
			return null;
		}
		return new com.srkr.accounts.domain.model.TransactionStatus(pgTransactionStatus.getId(),
				pgTransactionStatus.getValue());
	}

}
