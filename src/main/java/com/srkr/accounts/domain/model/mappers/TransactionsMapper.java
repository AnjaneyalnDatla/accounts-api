package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	public Transactions toPostgresObject(com.srkr.accounts.domain.model.Transactions transactions) {

		Transactions pgTransactions = new Transactions();
		pgTransactions.setUserId(transactions.user_id());
		pgTransactions.setUserName(transactions.user_name());
		pgTransactions.setTransactionNumber(transactions.transaction_number());
		Set<LineItem> items = new HashSet<>();
		transactions.lineItems().forEach(trans -> {
			items.add(lineItemsMapper.toPostgresObject(trans));
		});
		pgTransactions.setAccounts(accountsMapper.toPostgresObject(transactions.accounts()));
		pgTransactions.setHeaders(headersMapper.toPostgresObject(transactions.header()));
		pgTransactions.setDepartmentId(transactions.departmentId());
		pgTransactions.setDepartmentName(transactions.departmentName());
		return pgTransactions;
	}

	public com.srkr.accounts.domain.model.Transactions toDomainObject(Transactions pgTransactions,Set<LineItem> pgLineItems) {
		com.srkr.accounts.domain.model.Transactions transactions = new com.srkr.accounts.domain.model.Transactions(
				pgTransactions.getId(), headersMapper.toDomainObject(pgTransactions.getHeaders()),
				accountsMapper.toDomainObject(pgTransactions.getAccounts()), pgTransactions.getTransactionNumber(),
				pgTransactions.getUserId(), pgTransactions.getUserName(), pgTransactions.getDepartmentId(),
				pgTransactions.getDepartmentName(), null, this.lineItemsMapper.toListDomainObject(pgLineItems));
		return transactions;
	}

	public List<com.srkr.accounts.domain.model.Transactions> toListOfDomainObjects(List<Transactions> pgTransactions,Set<LineItem> pgLineItems) {
		List<com.srkr.accounts.domain.model.Transactions> transactions = new ArrayList<>();
		pgTransactions.forEach(c -> {
			transactions.add(toDomainObject(c,pgLineItems));
		});
		return transactions;
	}

}
