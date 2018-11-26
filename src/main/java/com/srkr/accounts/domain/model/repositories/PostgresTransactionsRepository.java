package com.srkr.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.postgres.Transactions;

public interface PostgresTransactionsRepository extends Repository<Transactions, Long> {

	List<Transactions> findByUserName(String userName);

	List<Transactions> findAll();
	
	//@EntityGraph(value = "transactions.lineItems")
	//Page<Transactions> findWithLineItems(Pageable page);

	@Transactional
	Transactions save(Transactions transactions);
	
	Transactions delete(Transactions transactions);

	@Query(value = "SELECT nextval('sequencer')", nativeQuery = true)
	Long getNextSequenceValue();

}
