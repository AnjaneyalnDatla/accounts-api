package com.srkr.accounts.domain.model.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionsMapper {

	@Autowired
	private AccountsMapper accountsMapper;

	public Transactions toPostgresObject(com.srkr.accounts.domain.model.Transactions transactions) {
		Transactions pgTransactions = new Transactions();
		pgTransactions.setUserId(transactions.user_id());
		pgTransactions.setUserName(transactions.user_name());
		pgTransactions.setLine_item_number(transactions.line_item_number());
		pgTransactions.setName(transactions.name());
		pgTransactions.setAccounts(accountsMapper.toPostgresObject(transactions.accounts()));
		return pgTransactions;
	}

}
