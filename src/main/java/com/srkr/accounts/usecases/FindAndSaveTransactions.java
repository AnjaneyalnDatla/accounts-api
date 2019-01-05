package com.srkr.accounts.usecases;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.AdditionalItems;
import com.srkr.accounts.domain.model.LineItem;
import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.mappers.AccountsMapper;
import com.srkr.accounts.domain.model.mappers.DocumentMapper;
import com.srkr.accounts.domain.model.mappers.LineItemsMapper;
import com.srkr.accounts.domain.model.mappers.PaymentsMapper;
import com.srkr.accounts.domain.model.mappers.TransactionTypesAndStatusMapper;
import com.srkr.accounts.domain.model.mappers.TransactionsMapper;
import com.srkr.accounts.domain.model.postgres.Accounts;
import com.srkr.accounts.domain.model.postgres.Document;
import com.srkr.accounts.domain.model.postgres.Payment;
import com.srkr.accounts.domain.model.postgres.TransactionTypes;
import com.srkr.accounts.domain.model.repositories.PostgresAccountsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresContactsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresDocumentRepository;
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
	PostgresDocumentRepository postgresDocumentsRepository;

	@Autowired
	TransactionsMapper transactionsMapper;
	@Autowired
	AccountsMapper accountsMapper;
	@Autowired
	TransactionTypesAndStatusMapper headersMapper;
	@Autowired
	LineItemsMapper lineItemsMapper;
	@Autowired
	PaymentsMapper paymentsMapper;
	@Autowired
	DocumentMapper documentMapper;

	@Transactional
	public Long transactionNumber() {
		return postgresTransactionsRepository.getNextSequenceValue();
	}

	@Transactional
	public List<Transactions> findTransactionsByUsername(String user_name) {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findByUserName(user_name), null);
	}

	@Transactional
	public Transactions findTransactionsByTransactionNumber(Integer transactionNumber) {
		com.srkr.accounts.domain.model.postgres.Transactions transactions = postgresTransactionsRepository
				.findByTransactionNumber(transactionNumber);
		if (transactions == null) {
			throw new ResourceNotFoundException();
		}
		Transactions transactionRs = transactionsMapper.toDomainObject(transactions);
		transactionRs.setDocuments(this.documentMapper.toListDomainObject(
				this.postgresDocumentsRepository.findByDocumentReferencerNumber(transactionNumber.longValue())));
		return transactionRs;
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
		// New transactions need a new number from backend.
		Integer transaction_number = postgresTransactionsRepository.getNextSequenceValue().intValue();

		retPgTransactions.setTransactionNumber(transaction_number);
		// Apply transactionNumber,TransactionId to every lineItem and other lineitems
		retPgTransactions = getLineItems(transactions, transaction_number, retPgTransactions);
		// Apply transactionNumber,TransactionId to every lineItem and other lineitems
		retPgTransactions = getPayments(transactions, transaction_number, retPgTransactions);
		// Save Transaction
		retPgTransactions = postgresTransactionsRepository.save(retPgTransactions);
		// Save Document MetaData
		Set<Document> documents = this.documentMapper.toListPostgresObject(transactions.documents());
		documents.forEach(doc -> {
			doc.setDocumentLink(
					"https://s3.amazonaws.com/a2nine-afs/" + transaction_number + "/" + doc.getDocumentName());
			doc.setDocumentReferencerNumber(transaction_number.longValue());
			this.postgresDocumentsRepository.save(doc);
		});
		Set<com.srkr.accounts.domain.model.Document> documents2 = this.documentMapper.toListDomainObject(documents);
		Transactions transactionRs = this.transactionsMapper.toDomainObject(retPgTransactions);
		transactionRs.setDocuments(documents2);
		return transactionRs;
	}

	@Transactional
	public Transactions updateTransaction(Transactions transactions) {
		// generate and set transaction number
		com.srkr.accounts.domain.model.postgres.Transactions retPgTransactions = this.transactionsMapper
				.toPostgresObject(transactions);
		// For update, the one from incoming request in carried forward
		Integer transaction_number = retPgTransactions.getTransactionNumber();
		// Apply transactionNumber,TransactionId to every lineItem and other lineitems
		retPgTransactions = getLineItems(transactions, transaction_number, retPgTransactions);
		// Apply transactionNumber,TransactionId to every lineItem and other lineitems
		retPgTransactions = getPayments(transactions, transaction_number, retPgTransactions);
		// Save Transaction
		retPgTransactions = postgresTransactionsRepository.save(retPgTransactions);

		// updating the account balances as per transaction type
		Set<Accounts> accountsToUpdate = new HashSet<>();
		for (com.srkr.accounts.domain.model.Bill bill : transactions.bills()) {
			for (com.srkr.accounts.domain.model.Payment payment : bill.payments()) {
				if (payment.getIsNew()) {
					Accounts acc = new Accounts();
					acc = this.postgresAccountsRepository.findById(payment.accounts().id().intValue());
					// for invoice we add the payment amount and for purchase order and bill we
					// deduct the payment amount
					if (transactions.transactionType().id() == 1l)
						acc.setCurrentBalance(acc.getCurrentBalance() + payment.amount());
					else
						acc.setCurrentBalance(acc.getCurrentBalance() - payment.amount());
					accountsToUpdate.add(acc);
				}
			}
		}
		accountsToUpdate.forEach(acc -> {
			this.postgresAccountsRepository.save(acc);
		});

		// Save Document MetaData
		Set<Document> documents = this.documentMapper.toListPostgresObject(transactions.documents());
		documents.forEach(doc -> {
			doc.setDocumentLink(
					"https://s3.amazonaws.com/a2nine-afs/" + transaction_number + "/" + doc.getDocumentName());
			doc.setDocumentReferencerNumber(transaction_number.longValue());
			this.postgresDocumentsRepository.save(doc);
		});
		Set<com.srkr.accounts.domain.model.Document> documents2 = this.documentMapper.toListDomainObject(documents);
		Transactions transactionRs = this.transactionsMapper.toDomainObject(retPgTransactions);
		transactionRs.setDocuments(documents2);
		return transactionRs;
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

	@Transactional
	public void addDocumentMetaDataToTransaction(Transactions transactions) {
		Set<Document> documents = this.documentMapper.toListPostgresObject(transactions.documents());
		documents.forEach(doc -> {
			postgresDocumentsRepository.save(doc);
		});
	}

	@Transactional
	public Set<com.srkr.accounts.domain.model.Document> getDocumentMetaData(Long transaction_number) {
		return documentMapper.toListDomainObject(
				this.postgresDocumentsRepository.findByDocumentReferencerNumber(transaction_number));
	}

	private com.srkr.accounts.domain.model.postgres.Transactions getPayments(Transactions transactions,
			Integer transaction_number, com.srkr.accounts.domain.model.postgres.Transactions pgTransactions) {
		Double totalPaid = 0.0d;
		Accounts accounts = null;
		// Add bill Number, Transaction Number and get the total paid out amount so far.
		// This needs to be refactored
		Set<com.srkr.accounts.domain.model.postgres.Bill> bills = new HashSet<>();
		for (com.srkr.accounts.domain.model.postgres.Bill bill : pgTransactions.getBills()) {
			Set<Payment> payments = new HashSet<>();
			for (Payment pgPayment : bill.getPayments()) {
				if (pgPayment != null) {
					totalPaid = totalPaid + pgPayment.getAmount();
					pgPayment.setBillNumber(bill.getBillNumber());
					pgPayment.setBill(bill);
					payments.add(pgPayment);
				}
			}
			bill.setPayments(payments);
			bill.setTransaction_number(transaction_number);
			bill.setTransactions(pgTransactions);
			bills.add(bill);
		}
		// Setting the pending amount
		Double pendingBalance = pgTransactions.getOriginalAmount() - totalPaid;
		if (pendingBalance == 0) {
			pgTransactions.getTransactionStatus().setId(1l);
			pgTransactions.setTransactionStatusName("COMPLETE");
		}
		pgTransactions.setPendingAmount(pendingBalance);
		if (!bills.isEmpty())
			pgTransactions.setBills(bills);
		return pgTransactions;
	}

	private com.srkr.accounts.domain.model.postgres.Transactions getLineItems(Transactions transactions,
			Integer transaction_number, com.srkr.accounts.domain.model.postgres.Transactions pgTransactions) {

		Set<com.srkr.accounts.domain.model.postgres.LineItem> lineItems = transactions.lineItems().stream().map(lt -> {
			com.srkr.accounts.domain.model.postgres.LineItem pgLit = this.lineItemsMapper.toPostgresObject(lt);
			pgLit.setTransactionNumber(transaction_number);
			pgLit.setTransactions(pgTransactions);
			return pgLit;
		}).collect(Collectors.toSet());

		if (!lineItems.isEmpty()) {
			// mapping tax
			if (transactions.tax() != null) {
				lineItems.add(new com.srkr.accounts.domain.model.postgres.LineItem(null, transaction_number,
						pgTransactions, lineItems.size() + 1, null, AdditionalItems.TAX.name(), 1, transactions.tax(),
						transactions.tax(), null));
			}

			// mapping Shipping
			if (transactions.shipping() != null) {
				lineItems.add(new com.srkr.accounts.domain.model.postgres.LineItem(null, transaction_number,
						pgTransactions, lineItems.size() + 1, null, AdditionalItems.SHIPPING.name(), 1,
						transactions.shipping(), transactions.shipping(), null));
			}

			// mapping Other
			if (transactions.other() != null) {
				lineItems.add(new com.srkr.accounts.domain.model.postgres.LineItem(null, transaction_number,
						pgTransactions, lineItems.size() + 1, null, AdditionalItems.OTHER.name(), 1,
						transactions.other(), transactions.other(), null));
			}

			pgTransactions.setLineItems(lineItems);
		}
		return pgTransactions;
	}

}
