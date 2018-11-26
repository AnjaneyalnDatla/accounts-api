package com.srkr.accounts.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.mappers.HeadersMapper;
import com.srkr.accounts.domain.model.mappers.TransactionsMapper;
import com.srkr.accounts.domain.model.repositories.PostgresHeadersRepository;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@Service
public class FindAndSaveTransactions {

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	@Autowired
	PostgresHeadersRepository postgresHeadersRepository;

	@Autowired
	TransactionsMapper transactionsMapper;
	@Autowired
	HeadersMapper headersMapper;

	@Transactional
	public List<Transactions> findTransactionsByUsername(String user_name) {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findByUserName(user_name));
	}

	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public List<Transactions> findAllTransactions() {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findAll());
	}

	@Transactional
	public Transactions saveTransaction(Transactions transactions) {
		return transactionsMapper.toDomainObject(
				postgresTransactionsRepository.save(this.transactionsMapper.toPostgresObject(transactions)));
	}

	@Transactional
	public void deleteTransaction(Transactions transactions) {
		postgresTransactionsRepository.delete(this.transactionsMapper.toPostgresObject(transactions));
	}

}
