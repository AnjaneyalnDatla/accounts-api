package com.srkr.accounts.usecases;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.AdditionalItems;
import com.srkr.accounts.domain.model.LineItem;
import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.mappers.AccountsMapper;
import com.srkr.accounts.domain.model.mappers.TransactionTypesAndStatusMapper;
import com.srkr.accounts.domain.model.mappers.LineItemsMapper;
import com.srkr.accounts.domain.model.mappers.TransactionsMapper;
import com.srkr.accounts.domain.model.postgres.Accounts;
import com.srkr.accounts.domain.model.postgres.Contacts;
import com.srkr.accounts.domain.model.postgres.TransactionTypes;
import com.srkr.accounts.domain.model.repositories.PostgresAccountsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresContactsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresLineItemsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@Service
public class FindAndSaveTransactions {

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	@Autowired
	PostgresLineItemsRepository postgresLineItemRepository;
	@Autowired
	PostgresAccountsRepository postgresAccountsRepository;
	@Autowired
	PostgresContactsRepository postgresContactsRepository;

	@Autowired
	TransactionsMapper transactionsMapper;
	@Autowired
	AccountsMapper accountsMapper;
	@Autowired
	TransactionTypesAndStatusMapper headersMapper;
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
		return transactionsMapper
				.toListOfDomainObjects(postgresTransactionsRepository.findByTransactionNumber(transactionNumber), null);
	}

	@Transactional
	public List<Transactions> findAllTransactions() {
		List<com.srkr.accounts.domain.model.postgres.Transactions> transactions = this.postgresTransactionsRepository
				.findAll();
		return transactionsMapper.toListOfDomainObjects(transactions, null);
	}
	
	@Transactional
	public List<Transactions> findAllTransactionsByTransactionType(Long transactionType) {
		List<com.srkr.accounts.domain.model.postgres.Transactions> transactions = this.postgresTransactionsRepository
				.findByTransactionType(new TransactionTypes(transactionType, null));
		return transactionsMapper.toListOfDomainObjects(transactions, null);
	}

	@Transactional
	public Transactions saveTransaction(Transactions transactions) {
		// generate and set transaction number
		com.srkr.accounts.domain.model.postgres.Transactions retPgTransactions = this.transactionsMapper
				.toPostgresObject(transactions);
		Integer transaction_number = postgresTransactionsRepository.getNextSequenceValue().intValue();
		retPgTransactions.setTransactionNumber(transaction_number);
		// Apply transactionNumber,TransactionId to every lineItem and other lineitems
		retPgTransactions = getLineItems(transactions, transaction_number, retPgTransactions);
		// Save Transaction
		retPgTransactions = postgresTransactionsRepository.save(retPgTransactions);

		Accounts accounts = this.postgresAccountsRepository.findById(transactions.accounts().id().intValue());
		if (transactions.transactionType().id() == 1l || transactions.transactionType().id() == 2l) {
			accounts.setCurrentBalance(
					accounts.getCurrentBalance() + (transactions.paymentAmount() - transactions.pendingAmount()));
		} else {
			accounts.setCurrentBalance(accounts.getCurrentBalance() - transactions.paymentAmount());
		}
		// Save/Update Account Balance
		this.postgresAccountsRepository.save(this.postgresAccountsRepository.save(accounts));

		return this.transactionsMapper.toDomainObject(retPgTransactions);
	}

	@Transactional
	public Set<LineItem> findAllLineItemsForTransaction(Integer transaction_number) {
		return lineItemsMapper
				.toListDomainObject(this.postgresLineItemRepository.findByTransactionNumber(transaction_number));
	}

	@Transactional
	public void deleteTransaction(Transactions transactions) {
		postgresTransactionsRepository.delete(this.transactionsMapper.toPostgresObject(transactions));
	}

	private com.srkr.accounts.domain.model.postgres.Transactions getLineItems(Transactions transactions,
			Integer transaction_number, com.srkr.accounts.domain.model.postgres.Transactions pgTransactions) {

		Set<com.srkr.accounts.domain.model.postgres.LineItem> lineItems = transactions.lineItems().stream().map(lt -> {
			com.srkr.accounts.domain.model.postgres.LineItem pgLit = this.lineItemsMapper.toPostgresObject(lt);
			pgLit.setTransactionNumber(transaction_number);
			pgLit.setTransactions(pgTransactions);
			return pgLit;
		}).collect(Collectors.toSet());

		// mapping tax
		if (transactions.tax() != null) {
			lineItems.add(new com.srkr.accounts.domain.model.postgres.LineItem(null, transaction_number, pgTransactions,
					lineItems.size() + 1, null, AdditionalItems.TAX.name(), 1, transactions.tax(), transactions.tax(), null));
		}

		// mapping Shipping
		if (transactions.shipping() != null) {
			lineItems.add(new com.srkr.accounts.domain.model.postgres.LineItem(null, transaction_number, pgTransactions,
					lineItems.size() + 1, null, AdditionalItems.SHIPPING.name(), 1, transactions.shipping(), transactions.shipping(), null));
		}

		// mapping Other
		if (transactions.other() != null) {
			lineItems.add(new com.srkr.accounts.domain.model.postgres.LineItem(null, transaction_number, pgTransactions,
					lineItems.size() + 1, null, AdditionalItems.OTHER.name(), 1, transactions.other(), transactions.other(), null));
		}

		pgTransactions.setLineItems(lineItems);
		return pgTransactions;
	}

}
