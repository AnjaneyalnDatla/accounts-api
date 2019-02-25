package com.a2nine.accounts.usecases;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a2nine.accounts.domain.model.AdditionalItems;
import com.a2nine.accounts.domain.model.LineItem;
import com.a2nine.accounts.domain.model.TransactionLog;
import com.a2nine.accounts.domain.model.Transactions;
import com.a2nine.accounts.domain.model.mappers.AccountsMapper;
import com.a2nine.accounts.domain.model.mappers.DocumentMapper;
import com.a2nine.accounts.domain.model.mappers.LineItemsMapper;
import com.a2nine.accounts.domain.model.mappers.PaymentsMapper;
import com.a2nine.accounts.domain.model.mappers.TransactionLogMapper;
import com.a2nine.accounts.domain.model.mappers.TransactionTypesAndStatusMapper;
import com.a2nine.accounts.domain.model.mappers.TransactionsMapper;
import com.a2nine.accounts.domain.model.postgres.Accounts;
import com.a2nine.accounts.domain.model.postgres.Document;
import com.a2nine.accounts.domain.model.postgres.Payment;
import com.a2nine.accounts.domain.model.postgres.TransactionTypes;
import com.a2nine.accounts.domain.model.repositories.PostgresAccountsRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresContactsRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresDocumentRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresLineItemsRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresTransactionLogRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresTransactionsRepository;

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
	PostgresTransactionLogRepository postgresTransactionLogRepository;

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
	@Autowired
	TransactionLogMapper transactionLogMapper;
	@Autowired
	JavaMailSender sender;

	@Transactional
	public Long transactionNumber() {
		return postgresTransactionsRepository.getNextSequenceValue();
	}

	@Transactional
	public List<Transactions> findTransactionsByUsername(String user_name, String orgCode) {
		return transactionsMapper.toListOfDomainObjects(
				postgresTransactionsRepository.findByUserNameAndOrgcode(user_name, orgCode), null);
	}

	@Transactional
	public Transactions findTransactionsByTransactionNumber(Integer transactionNumber, String orgCode) {
		com.a2nine.accounts.domain.model.postgres.Transactions transactions = postgresTransactionsRepository
				.findByTransactionNumberAndOrgcode(transactionNumber, orgCode);
		if (transactions == null) {
			throw new ResourceNotFoundException();
		}
		Transactions transactionRs = transactionsMapper.toDomainObject(transactions);
		transactionRs.setDocuments(this.documentMapper.toListDomainObject(
				this.postgresDocumentsRepository.findByDocumentReferencerNumber(transactionNumber.longValue())));
		return transactionRs;
	}

	@Transactional
	public List<Transactions> findAllTransactions(String orgCode) {
		List<com.a2nine.accounts.domain.model.postgres.Transactions> transactions = this.postgresTransactionsRepository
				.findByOrgcode(orgCode);
		return transactionsMapper.toListOfDomainObjects(transactions, null);
	}

	@Transactional
	public List<Transactions> findAllTransactionsByTransactionType(Long transactionType, String orgCode) {
		List<com.a2nine.accounts.domain.model.postgres.Transactions> transactions = this.postgresTransactionsRepository
				.findByTransactionTypeAndOrgcode(new TransactionTypes(transactionType, null), orgCode);
		return transactionsMapper.toListOfDomainObjects(transactions, null);
	}

	@Transactional
	public Transactions saveTransaction(Transactions transactions) {
		// generate and set transaction number
		com.a2nine.accounts.domain.model.postgres.Transactions retPgTransactions = this.transactionsMapper
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
		Set<com.a2nine.accounts.domain.model.Document> documents2 = this.documentMapper.toListDomainObject(documents);
		Transactions transactionRs = this.transactionsMapper.toDomainObject(retPgTransactions);
		transactionRs.setDocuments(documents2);

		// Save Transaction log
		TransactionLog transactionLog = new TransactionLog(null, transactionRs, transactionRs.transactionStatus(),
				new Date(), transactionRs.user_name(), null);
		saveTransactionLog(transactionLog);

		return transactionRs;
	}

	@Transactional
	public Transactions updateTransaction(Transactions transactions) {
		// generate and set transaction number
		com.a2nine.accounts.domain.model.postgres.Transactions retPgTransactions = this.transactionsMapper
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
		for (com.a2nine.accounts.domain.model.Bill bill : transactions.bills()) {
			for (com.a2nine.accounts.domain.model.Payment payment : bill.payments()) {
				if (payment.getIsNew()) {
					Accounts acc = new Accounts();
					acc = this.postgresAccountsRepository.findByIdAndOrgcode(payment.accounts().id().intValue(),
							payment.organisation().code());
					// for invoice we add the payment amount and for purchase order and bill we
					// deduct the payment amount
					if (transactions.transactionType().id() == 1l || transactions.transactionType().id() == 10l)
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
		Set<com.a2nine.accounts.domain.model.Document> documents2 = this.documentMapper.toListDomainObject(documents);
		Transactions transactionRs = this.transactionsMapper.toDomainObject(retPgTransactions);
		transactionRs.setDocuments(documents2);
		return transactionRs;
	}

	@Transactional
	public Set<LineItem> findAllLineItemsForTransaction(Integer transaction_number, String orgCode) {
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
	public Set<com.a2nine.accounts.domain.model.Document> getDocumentMetaData(Long transaction_number) {
		return documentMapper.toListDomainObject(
				this.postgresDocumentsRepository.findByDocumentReferencerNumber(transaction_number));
	}

	private com.a2nine.accounts.domain.model.postgres.Transactions getPayments(Transactions transactions,
			Integer transaction_number, com.a2nine.accounts.domain.model.postgres.Transactions pgTransactions) {
		Double totalPaid = 0.0d;
		// Add bill Number, Transaction Number and get the total paid out amount so far.
		// This needs to be refactored
		Set<com.a2nine.accounts.domain.model.postgres.Bill> bills = new HashSet<>();
		for (com.a2nine.accounts.domain.model.postgres.Bill bill : pgTransactions.getBills()) {
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

	private com.a2nine.accounts.domain.model.postgres.Transactions getLineItems(Transactions transactions,
			Integer transaction_number, com.a2nine.accounts.domain.model.postgres.Transactions pgTransactions) {

		Set<com.a2nine.accounts.domain.model.postgres.LineItem> lineItems = transactions.lineItems().stream()
				.map(lt -> {
					com.a2nine.accounts.domain.model.postgres.LineItem pgLit = this.lineItemsMapper
							.toPostgresObject(lt);
					pgLit.setTransactionNumber(transaction_number);
					pgLit.setTransactions(pgTransactions);
					return pgLit;
				}).collect(Collectors.toSet());

		if (!lineItems.isEmpty()) {
			// mapping tax
			if (transactions.tax() != null) {
				lineItems.add(new com.a2nine.accounts.domain.model.postgres.LineItem(null, transaction_number,
						pgTransactions, lineItems.size() + 1, null, AdditionalItems.TAX.name(), 1, transactions.tax(),
						transactions.tax(), null));
			}

			// mapping Shipping
			if (transactions.shipping() != null) {
				lineItems.add(new com.a2nine.accounts.domain.model.postgres.LineItem(null, transaction_number,
						pgTransactions, lineItems.size() + 1, null, AdditionalItems.SHIPPING.name(), 1,
						transactions.shipping(), transactions.shipping(), null));
			}

			// mapping Other
			if (transactions.other() != null) {
				lineItems.add(new com.a2nine.accounts.domain.model.postgres.LineItem(null, transaction_number,
						pgTransactions, lineItems.size() + 1, null, AdditionalItems.OTHER.name(), 1,
						transactions.other(), transactions.other(), null));
			}

			pgTransactions.setLineItems(lineItems);
		}
		return pgTransactions;
	}

	@Transactional
	public TransactionLog approveTransaction(TransactionLog transactionLog) {
		com.a2nine.accounts.domain.model.postgres.TransactionLog retPgTransactionLog = this.transactionLogMapper
				.toPostgresObject(transactionLog);
		// update the status in transaction table
		// Save Transaction
		com.a2nine.accounts.domain.model.postgres.Transactions retPgTransactions = retPgTransactionLog
				.getTransactions();
		retPgTransactions.setTransactionStatus(retPgTransactionLog.getTransactionStatus());
		retPgTransactions.setTransactionStatusName(retPgTransactionLog.getTransactionStatus().getValue());
		postgresTransactionsRepository.save(retPgTransactionLog.getTransactions());

		// save status in transaction log
		retPgTransactionLog = postgresTransactionLogRepository.save(retPgTransactionLog);

		TransactionLog transactionLogRs = this.transactionLogMapper.toDomainObject(retPgTransactionLog);

		return transactionLogRs;
	}

	private TransactionLog saveTransactionLog(TransactionLog transactionLog) {
		// save status in transaction log
		com.a2nine.accounts.domain.model.postgres.TransactionLog retPgTransactionLog = this.transactionLogMapper
				.toPostgresObject(transactionLog);
		retPgTransactionLog = postgresTransactionLogRepository.save(retPgTransactionLog);

		TransactionLog transactionLogRs = this.transactionLogMapper.toDomainObject(retPgTransactionLog);

		return transactionLogRs;
	}

	public void sendEmail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("kaushik@gmail.com");
		message.setTo("approver@gmail.com");
		message.setSubject("Testing");
		message.setText("This is the test email template for your email:\n%s\n");
		sender.send(message);
	}

}
