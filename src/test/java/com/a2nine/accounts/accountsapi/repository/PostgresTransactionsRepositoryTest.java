package com.a2nine.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.a2nine.accounts.domain.model.postgres.Accounts;
import com.a2nine.accounts.domain.model.postgres.Contacts;
import com.a2nine.accounts.domain.model.postgres.LineItem;
import com.a2nine.accounts.domain.model.postgres.Products;
import com.a2nine.accounts.domain.model.postgres.TransactionStatus;
import com.a2nine.accounts.domain.model.postgres.TransactionTypes;
import com.a2nine.accounts.domain.model.postgres.Transactions;
import com.a2nine.accounts.domain.model.repositories.PostgresLineItemsRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresTransactionTypesRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresTransactionsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostgresTransactionsRepositoryTest {

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	@Autowired
	PostgresTransactionTypesRepository postgresTransactionTypesRepository;
	@Autowired
	PostgresLineItemsRepository postgresLineItemsRepository;

	private Transactions transactions;
	Set<LineItem> lineItems;
	Set<LineItem> deleteLineItems = new HashSet<>();

	@Before
	public void setUp() {
		this.transactions = new Transactions();
		this.transactions.setOriginalAmount(20000d);
		this.transactions.setPendingAmount(0d);
		this.lineItems = new HashSet<>();

		LineItem item = new LineItem();
		item.setLine_item_number(1);
		item.setProducts(new Products(1l, "TEST"));
		item.setName("Test 1");
		item.setQuantity(1);
		item.setPrice(20d);
		item.setAmount(20d);
		lineItems.add(item);

		LineItem item2 = new LineItem();
		item2.setLine_item_number(2);
		item2.setProducts(new Products(1l, "TEST2"));
		item2.setName("Test 2");
		item2.setQuantity(1);
		item2.setPrice(20d);
		item2.setAmount(20d);
		lineItems.add(item2);
		this.transactions.setLineItems(lineItems);

		Accounts accounts = new Accounts();
		accounts.setId(1);
		Contacts contact = new Contacts(1l);

		this.transactions.setTransactionType(new TransactionTypes(1l, "INVOICE"));
		this.transactions.setTransactionStatus(new TransactionStatus(1l, "COMPLETE"));

		this.transactions.setContact(contact);
		this.transactions.setUserId(4);
		this.transactions.setUserName("admin@admin.com");
		this.transactions.setDepartmentId(1);
		this.transactions.setDepartmentName("civil");
		this.transactions.setDueDate(new Date());
		this.transactions.setCreationdate(new Date());
		this.transactions.setDeliveryDate(new Date());
		this.transactions.setOrgcode("DEFAULT");
		this.transactions.setOrgName("DEFAULT");
	}

	@Test
	public void findAllTransactions() {
		List<Transactions> transactions = postgresTransactionsRepository.findByOrgcode("DEFAULT");
		assertNotNull(transactions);
	}

	@Test
	public void findAllTransactionsByUsername() {
		List<Transactions> transactions = postgresTransactionsRepository.findByUserNameAndOrgcode("admin@admin.com",
				"DEFAULT");
		assertNotNull(transactions);
	}

	@Test
	public void findByTransactionType() {
		List<Transactions> transactions = postgresTransactionsRepository
				.findByTransactionTypeAndOrgcode(new TransactionTypes(1l, null), "DEFAULT");
		assertNotNull(transactions);
	}

	@Test
	public void saveTransactions() {
		Integer transaction_number = this.postgresTransactionsRepository.getNextSequenceValue().intValue();
		this.transactions.setTransactionNumber(transaction_number);
		this.transactions.getLineItems().forEach(lt -> {
			lt.setTransactionNumber(transaction_number);
		});
		this.transactions = postgresTransactionsRepository.save(this.transactions);
		assertNotNull(transactions);

	}

	@Test
	public void updateTransaction() {
		this.transactions.setTransactionNumber(128);
		this.transactions.setLineItems(null);
		this.transactions = postgresTransactionsRepository.save(this.transactions);
		assertNotNull(transactions);
	}

	@Test
	public void getNextSeqValue() {
		Integer sequence = postgresTransactionsRepository.getNextSequenceValue().intValue();
		assertNotNull(sequence);
	}

	// @After
	public void destroy() {
		if (this.transactions.getId() != null)
			this.postgresTransactionsRepository.delete(this.transactions);
		deleteLineItems.forEach(lt -> {
			if (lt.getId() != null)
				postgresLineItemsRepository.delete(lt);
		});
	}

}
