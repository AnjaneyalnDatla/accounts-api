package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
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
	private ContactsMapper contactsMapper;

	@Autowired
	private TransactionTypesAndStatusMapper transactionTypesAndStatusMapper;

	@Autowired
	private AccountsMapper accountsmapper;

	@Autowired
	private LineItemsMapper lineItemMapper;

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;

	public Transactions toPostgresObject(com.srkr.accounts.domain.model.Transactions transactions) {

		Transactions pgTransactions = new Transactions();
		pgTransactions.setTransactionNumber(transactions.transaction_number());
		pgTransactions.setOriginalAmount(transactions.paymentAmount());
		pgTransactions.setPendingAmount(transactions.pendingAmount());
		pgTransactions.setAccount(this.accountsmapper.toPostgresObject(transactions.accounts()));
		pgTransactions.setContact(this.contactsMapper.toPostgresObject(transactions.contact()));
		pgTransactions
				.setTransactionType(transactionTypesAndStatusMapper.toPostgresObject(transactions.transactionType()));
		pgTransactions.setTransactionStatus(
				transactionTypesAndStatusMapper.toPostgresObject(transactions.transactionStatus()));
		pgTransactions.setUserId(transactions.user_id());
		pgTransactions.setUserName(transactions.user_name());
		pgTransactions.setDepartmentId(transactions.departmentId());
		pgTransactions.setDepartmentName(transactions.departmentName());
		pgTransactions.setPaymentDate(transactions.paymentDate());
		pgTransactions.setDueDate(transactions.dueDate());
		pgTransactions.setDeliveryDate(transactions.deliveryDate());
		pgTransactions.setLineItems(this.lineItemMapper.toListPostgresObject(transactions.lineItems()));
		return pgTransactions;
	}

	public com.srkr.accounts.domain.model.Transactions toDomainObject(Transactions pgTransactions) {

		return new com.srkr.accounts.domain.model.Transactions(pgTransactions.getId(),
				pgTransactions.getTransactionNumber(), pgTransactions.getOriginalAmount(),
				pgTransactions.getPendingAmount(), this.accountsmapper.toDomainObject(pgTransactions.getAccount()),
				this.contactsMapper.toDomainObject(pgTransactions.getContact()),
				this.transactionTypesAndStatusMapper.toDomainObject(pgTransactions.getTransactionType()),
				this.transactionTypesAndStatusMapper.toDomainObject(pgTransactions.getTransactionStatus()), null, null,
				null, pgTransactions.getUserId(), pgTransactions.getUserName(), pgTransactions.getDepartmentId(),
				pgTransactions.getDepartmentName(), pgTransactions.getDateupdated(),
				this.lineItemMapper.toListDomainObject(pgTransactions.getLineItems()), pgTransactions.getPaymentDate(),
				pgTransactions.getDueDate(), pgTransactions.getDeliveryDate());
	}

	public List<com.srkr.accounts.domain.model.Transactions> toListOfDomainObjects(List<Transactions> pgTransactions,
			Set<LineItem> pgLineItems) {
		List<com.srkr.accounts.domain.model.Transactions> transactions = new ArrayList<>();
		pgTransactions.forEach(c -> {
			transactions.add(toDomainObject(c));
		});
		return transactions;
	}

}
