package com.a2nine.accounts.accountsapi.repository;

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

import com.a2nine.accounts.domain.model.Organisation;
import com.a2nine.accounts.domain.model.postgres.Contacts;
import com.a2nine.accounts.domain.model.postgres.LineItem;
import com.a2nine.accounts.domain.model.postgres.Products;
import com.a2nine.accounts.domain.model.postgres.TransactionStatus;
import com.a2nine.accounts.domain.model.postgres.TransactionTypes;
import com.a2nine.accounts.domain.model.postgres.Transactions;
import com.a2nine.accounts.domain.model.repositories.PostgresLineItemsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresLineItemsRepositoryTest {
	@Autowired
	private PostgresLineItemsRepository lineItemsRepository;

	private List<LineItem> lineItemsList;
	private List<LineItem> lineItemsDeleteList;
	private int id;

	@Before
	public void setUp() {
		this.lineItemsList = new ArrayList<>();
		Random rand = new Random();
		LineItem pgLineItem = new LineItem();
		pgLineItem.setId(rand.nextLong());
		Products products = new Products(rand.nextLong(), "new Product", new Date(),
				"DEFAULT", "DEFAULT");
		Transactions transactions = new Transactions();
		transactions.setId(rand.nextLong());
		transactions.setTransactionNumber(2);
		
		transactions.setOriginalAmount(rand.nextDouble());
		transactions.setPendingAmount(rand.nextDouble());
		transactions.setContact(new Contacts(rand.nextLong(),"First Name","last name", "streeet","city","WI","USA","53562","Id type","34567","designation",
				6789.00,new Date(),"DEFAULT","DEFAULT"));
		transactions.setContactName("New contact");
		transactions.setTransactionType(new TransactionTypes(1l, "INVOICE","Description",new Date()));
		transactions.setTransactionTypeName("Trans Type Name");
		transactions.setTransactionStatus(new TransactionStatus(1l, "COMPLETE"));
		transactions.setTransactionStatusName("Transaction status name");
		
		transactions.setUserId(12);
		transactions.setUserName("admin@admin.com");
		transactions.setDepartmentId(1);
		transactions.setDepartmentName("civil");
		transactions.setDueDate(new Date());
		transactions.setDateupdated(new Date());
		transactions.setCreationdate(new Date());
		transactions.setDeliveryDate(new Date());
		transactions.setOrgcode("DEFAULT");
		transactions.setOrgName("DEFAULT");
		
		pgLineItem.setAmount(rand.nextDouble());
		pgLineItem.setDateupdated(new Date());
		pgLineItem.setLine_item_number(1);
		pgLineItem.setName("New Line Item");
		pgLineItem.setPrice(rand.nextDouble());
		pgLineItem.setProducts(products);
		pgLineItem.setQuantity(1);
		pgLineItem.setTransactionNumber(2);
		pgLineItem.setTransactions(transactions);

		lineItemsList.add(pgLineItem);
		

	}

	

	@Test
	public void save() {
		lineItemsList.forEach((lineItem) -> {
			lineItemsDeleteList.add(this.lineItemsRepository.save(lineItem));
		});
		assertTrue(lineItemsDeleteList.size() > 0);
		id = lineItemsDeleteList.get(0).getTransactions().getTransactionNumber();
	}

	@Test
	public void findByTransactionNumber() {
		Set<LineItem> lineItem = lineItemsRepository.findByTransactionNumber(id);
		assertNotNull(lineItem);
	}
	/*@After
	public void after() {
		lineItemsDeleteList.forEach(lineItem -> {
			this.lineItemsRepository.delete(lineItem);
		});
	}*/
}
