package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.AdditionalItems;
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
		Set<com.srkr.accounts.domain.model.LineItem> lineItems = this.lineItemMapper
				.toListDomainObject(pgTransactions.getLineItems());

		/**
		 * In Postgres TAX,SHIPPING and OTHER are saved as lineitems, we need to
		 * separate that out for UI and pass as elements by themselves on the transaction object
		 */
		double tax = 0.0d;
		double shipping = 0.0d;
		double other = 0.0d;
		Set<com.srkr.accounts.domain.model.LineItem> reOrganisedList = new HashSet<>();
		for (com.srkr.accounts.domain.model.LineItem lt : lineItems) {
			if (lt.name().equalsIgnoreCase(AdditionalItems.TAX.name())) {
				tax = lt.price();
			} else if (lt.name().equalsIgnoreCase(AdditionalItems.SHIPPING.name())) {
				shipping = lt.price();
			} else if (lt.name().equalsIgnoreCase(AdditionalItems.OTHER.name())) {
				other = lt.price();
			} else {
				reOrganisedList.add(lt);
			}
		}

		return new com.srkr.accounts.domain.model.Transactions(pgTransactions.getId(),
				pgTransactions.getTransactionNumber(), pgTransactions.getOriginalAmount(),
				pgTransactions.getPendingAmount(), this.accountsmapper.toDomainObject(pgTransactions.getAccount()),
				this.contactsMapper.toDomainObject(pgTransactions.getContact()),
				this.transactionTypesAndStatusMapper.toDomainObject(pgTransactions.getTransactionType()),
				this.transactionTypesAndStatusMapper.toDomainObject(pgTransactions.getTransactionStatus()), tax,
				shipping, other, pgTransactions.getUserId(), pgTransactions.getUserName(),
				pgTransactions.getDepartmentId(), pgTransactions.getDepartmentName(), pgTransactions.getDateupdated(),
				reOrganisedList, pgTransactions.getPaymentDate(), pgTransactions.getDueDate(),
				pgTransactions.getDeliveryDate());
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
