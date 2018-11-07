package com.srkr.accounts.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.postgres.PostgresTransactionsRepository;
import com.srkr.accounts.domain.model.postgres.TransactionsMapper;

@Service
public class FindTransactions {
	
	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	
	@Autowired
	TransactionsMapper transactionsMapper;

	public List<Transactions> findTransactionsByUsername(String user_name) {
		
		return null;
	}
	
	public List<Transactions> findAllTransactions() {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findAll());
	}

}

