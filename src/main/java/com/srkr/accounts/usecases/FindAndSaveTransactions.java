package com.srkr.accounts.usecases;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.LineItem;
import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.mappers.HeadersMapper;
import com.srkr.accounts.domain.model.mappers.LineItemsMapper;
import com.srkr.accounts.domain.model.mappers.TransactionsMapper;
import com.srkr.accounts.domain.model.repositories.PostgresAccountsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresHeadersRepository;
import com.srkr.accounts.domain.model.repositories.PostgresLineItemsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@Service
public class FindAndSaveTransactions {

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	@Autowired
	PostgresHeadersRepository postgresHeadersRepository;
	@Autowired
	PostgresLineItemsRepository postgresLineItemRepository;
	@Autowired
	PostgresAccountsRepository postgresAccountsRepository;
	@Autowired
	TransactionsMapper transactionsMapper;
	@Autowired
	HeadersMapper headersMapper;
	@Autowired
	LineItemsMapper lineItemsMapper;
	
	@Transactional
	public Long transactionNumber() {
		return postgresTransactionsRepository.getNextSequenceValue();
	}

	@Transactional
	public List<Transactions> findTransactionsByUsername(String user_name) {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findByUserName(user_name), null);
	}
	
	@Transactional
	public List<Transactions> findTransactionsByTransactionNumber(Integer transactionNumber) {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findByTransactionNumber(transactionNumber), null);
	}
	
	

	@Transactional
	public List<Transactions> findAllTransactions() {
		List<com.srkr.accounts.domain.model.postgres.Transactions> transactions = this.postgresTransactionsRepository
				.findAll();
		return transactionsMapper.toListOfDomainObjects(transactions, null);
	}

	@Transactional
	public Transactions saveTransaction(Transactions transactions) {
		//generate and set transaction number
		com.srkr.accounts.domain.model.postgres.Transactions retPgTransactions  = this.transactionsMapper.toPostgresObject(transactions);
		Integer transaction_number = postgresTransactionsRepository.getNextSequenceValue().intValue();
		retPgTransactions.setTransactionNumber(transaction_number);
		// Save Transaction		
		com.srkr.accounts.domain.model.postgres.Transactions pgTransactions = postgresTransactionsRepository
				.save(retPgTransactions);
		// Apply transactionNumber to every lineItem
		Set<com.srkr.accounts.domain.model.postgres.LineItem> lineItems = transactions.lineItems().stream().map(lt -> {
			com.srkr.accounts.domain.model.postgres.LineItem pgLit = this.lineItemsMapper.toPostgresObject(lt);
			pgLit.setTransactionNumber(transaction_number);
			return pgLit;
		}).collect(Collectors.toSet());
		// Save Each LineItem
		lineItems.forEach((lt) -> {
			this.lineItemsMapper.toDomainObject(this.postgresLineItemRepository.save(lt));
		});
		// Header
		this.postgresHeadersRepository.save(this.postgresHeadersRepository.save(retPgTransactions.getHeaders()));
		// Account
		this.postgresAccountsRepository.save(this.postgresAccountsRepository.save(retPgTransactions.getAccounts()));
		return this.transactionsMapper.toDomainObject(pgTransactions, lineItems);
	}

	@Transactional
	public Set<LineItem> findAllLineItemsForTransaction(Integer transaction_number) {
		return lineItemsMapper.toListDomainObject(
				this.postgresLineItemRepository.findByTransactionNumber(transaction_number));
	}

	@Transactional
	public void deleteTransaction(Transactions transactions) {
		postgresTransactionsRepository.delete(this.transactionsMapper.toPostgresObject(transactions));
	}

}
