package com.a2nine.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.a2nine.accounts.domain.model.postgres.TransactionTypes;
import com.a2nine.accounts.domain.model.postgres.Transactions;

public interface PostgresTransactionsRepository extends Repository<Transactions, Long> {

	List<Transactions> findByUserNameAndOrgcode(String userName, String orgCode);

	List<Transactions> findByTransactionTypeAndOrgcode(TransactionTypes transactionType, String orgCode);

	Transactions findByTransactionNumberAndOrgcode(Integer transactionNumber, String orgCode);

	List<Transactions> findByOrgcode(String orgCode);

	Transactions save(Transactions transactions);

	Transactions delete(Transactions transactions);

	@Query(value = "SELECT nextval('sequencer')", nativeQuery = true)
	Long getNextSequenceValue();

}
