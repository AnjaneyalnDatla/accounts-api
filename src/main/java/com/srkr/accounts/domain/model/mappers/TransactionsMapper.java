package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.postgres.LineItem;
import com.srkr.accounts.domain.model.postgres.Transactions;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@Component
public class TransactionsMapper {

	@Autowired
	private AccountsMapper accountsMapper;

	@Autowired
	private HeadersMapper headersMapper;

	@Autowired
	private LineItemsMapper lineItemsMapper;

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Transactions toPostgresObject(com.srkr.accounts.domain.model.Transactions transactions) {

		Transactions pgTransactions = new Transactions();
		pgTransactions.setUserId(transactions.user_id());
		pgTransactions.setUserName(transactions.user_name());
		pgTransactions.setTransactionNumber(transactions.transaction_number());
		Set<LineItem> items = new HashSet<>();
		transactions.lineItems().forEach(trans -> {
			items.add(lineItemsMapper.toPostgresObject(trans));
		});
		pgTransactions.setLineItems(items);
		pgTransactions.setAccounts(accountsMapper.toPostgresObject(transactions.accounts()));
		pgTransactions.setHeaders(headersMapper.toPostgresObject(transactions.header()));
		pgTransactions.setDepartmentId(transactions.departmentId());
		pgTransactions.setDepartmentName(transactions.departmentName());
		return pgTransactions;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public com.srkr.accounts.domain.model.Transactions toDomainObject(Transactions pgTransactions) {
		Set<com.srkr.accounts.domain.model.LineItem> lineItems = new HashSet<>();
		if (null != pgTransactions.getLineItems() && pgTransactions.getLineItems().isEmpty())
			pgTransactions.getLineItems().forEach((trans) -> {
				lineItems.add(lineItemsMapper.toDomainObject(trans));
			});
		com.srkr.accounts.domain.model.Transactions transactions = new com.srkr.accounts.domain.model.Transactions(
				pgTransactions.getId(), pgTransactions.getUserId(), pgTransactions.getTransactionNumber(),
				pgTransactions.getUserName(), accountsMapper.toDomainObject(pgTransactions.getAccounts()),
				headersMapper.toDomainObject(pgTransactions.getHeaders()), lineItems, pgTransactions.getDepartmentId(),
				pgTransactions.getDepartmentName());
		return transactions;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<com.srkr.accounts.domain.model.Transactions> toListOfDomainObjects(List<Transactions> pgTransactions) {
		List<com.srkr.accounts.domain.model.Transactions> transactions = new ArrayList<>();
		pgTransactions.forEach(c -> {
			transactions.add(toDomainObject(c));
		});
		return transactions;
	}

}
