package com.srkr.accounts.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.mappers.TransactionsMapper;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@Service
public class FindAndSaveTransactions {
	
	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	
	@Autowired
	TransactionsMapper transactionsMapper;

	@Transactional
	public List<Transactions> findTransactionsByUsername(String user_name) {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findByUserName(user_name));
	}
	
	@Transactional
	public List<Transactions> findAllTransactions() {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findAll());
	}

}

