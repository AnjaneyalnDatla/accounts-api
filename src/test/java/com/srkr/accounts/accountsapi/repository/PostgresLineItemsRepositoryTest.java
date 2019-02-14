package com.srkr.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.postgres.Contacts;
import com.srkr.accounts.domain.model.postgres.LineItem;
import com.srkr.accounts.domain.model.postgres.Products;
import com.srkr.accounts.domain.model.postgres.TransactionStatus;
import com.srkr.accounts.domain.model.postgres.TransactionTypes;
import com.srkr.accounts.domain.model.postgres.Transactions;
import com.srkr.accounts.domain.model.repositories.PostgresLineItemsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresLineItemsRepositoryTest {
	@Autowired
	private PostgresLineItemsRepository lineItemsRepository;

	private List<LineItem> lineItemsList;
	private List<LineItem> lineItemsDeleteList = new ArrayList<>();

	@Before
	public void setUp() {
		this.lineItemsList = new ArrayList<>();
		Random rand = new Random();
		LineItem pgLineItem = new LineItem();
		Products products = new Products(rand.nextLong(), "new Product", new Date()); 
		Transactions transactions = new Transactions();
		transactions.setTransactionType(new TransactionTypes(1l, "INVOICE"));
		transactions.setTransactionStatus(new TransactionStatus(1l, "COMPLETE"));

		transactions.setContact(new Contacts(rand.nextLong()));
		transactions.setUserId(rand.nextInt());
		transactions.setUserName("admin@admin.com");
		transactions.setDepartmentId(rand.nextInt());
		transactions.setDepartmentName("civil");
		transactions.setDueDate(new Date());
		transactions.setCreationdate(new Date());
		transactions.setDeliveryDate(new Date());
				
		pgLineItem.setAmount(rand.nextDouble());
		pgLineItem.setDateupdated(new Date());
		pgLineItem.setLine_item_number(rand.nextInt());
		pgLineItem.setName("New Line Item");
		pgLineItem.setPrice(rand.nextDouble());
		pgLineItem.setProducts(products);
		pgLineItem.setQuantity(rand.nextInt());
		pgLineItem.setTransactionNumber(rand.nextInt());
		pgLineItem.setTransactions(transactions);
		
		lineItemsList.add(pgLineItem);
	}
	
	@Test
	public void findByTransactionNumber() {
		Set<LineItem> lineItem = lineItemsRepository.findByTransactionNumber(1);
		assertNotNull(lineItem);
	}
	
	@Test
	public void save() {
		lineItemsList.forEach((lineItem) -> {
			lineItemsDeleteList.add(this.lineItemsRepository.save(lineItem));
		});
		assertTrue(lineItemsDeleteList.size() > 0);
	}
	
	@After
	public void after() {
		lineItemsDeleteList.forEach(lineItem -> {
			this.lineItemsRepository.delete(lineItem);
		});
	}
}
