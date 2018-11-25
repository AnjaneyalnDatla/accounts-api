package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.LineItem;
import com.srkr.accounts.domain.model.postgres.Transactions;

@Component
public class TransactionsMapper {

	@Autowired
	private AccountsMapper accountsMapper;

	@Autowired
	private HeadersMapper headersMapper;

	@Autowired
	private LineItemsMapper lineItemsMapper;

	public Transactions toPostgresObject(com.srkr.accounts.domain.model.Transactions transactions) {

		Transactions pgTransactions = new Transactions();
		pgTransactions.setUserId(transactions.user_id());
		pgTransactions.setUserName(transactions.user_name());
		pgTransactions.setTransactionNumber(transactions.transaction_number());
		List<LineItem> items = new ArrayList<>();
		transactions.lineItems().forEach(trans -> {
			items.add(lineItemsMapper.toPostgresObject(trans));
		});
		pgTransactions.setAccounts(accountsMapper.toPostgresObject(transactions.accounts()));
		pgTransactions.setHeaders(headersMapper.toPostgresObject(transactions.header()));

		return pgTransactions;
	}

	public com.srkr.accounts.domain.model.Transactions toDomainObject(Transactions pgTransactions) {
		List<com.srkr.accounts.domain.model.LineItem> lineItems = new ArrayList<>();
		pgTransactions.getLineItems().forEach((trans) -> {
			lineItems.add(lineItemsMapper.toDomainObject(trans));
		});
		com.srkr.accounts.domain.model.Transactions transactions = new com.srkr.accounts.domain.model.Transactions(
				pgTransactions.getId(), pgTransactions.getUserId(), pgTransactions.getTransactionNumber(),
				pgTransactions.getUserName(), accountsMapper.toDomainObject(pgTransactions.getAccounts()),
				headersMapper.toDomainObject(pgTransactions.getHeaders()), lineItems);
		return transactions;
	}

	public List<com.srkr.accounts.domain.model.Transactions> toListOfDomainObjects(List<Transactions> pgTransactions) {
		List<com.srkr.accounts.domain.model.Transactions> transactions = new ArrayList<>();
		pgTransactions.forEach(c -> {
			transactions.add(toDomainObject(c));
		});
		return transactions;
	}

}
