package com.srkr.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TransactionsTest {

	@Test
	public void TestTransactions() {
		Set<LineItem> lineItems = new HashSet<LineItem>();
		Set<Bill> bills = new HashSet<Bill>();
		Set<Document> documents = new HashSet<Document>();
		Contacts contacts = new Contacts(new Long(123));
		TransactionTypes transactionTypes = new TransactionTypes("Test", "Description");
		TransactionStatus transactionStatus  = new TransactionStatus(new Long(1234), "Yes");
		
		Transactions transactions = new Transactions(new Long(6789), 345, 980.98, 678.00, contacts, 
										transactionTypes, transactionStatus, 19780.00 , 456.00, 956.00, 6521, "tester", 
										4567, "Electronics", new Date((new GregorianCalendar(2019,2,1)).getTimeInMillis()), lineItems, new Date((new GregorianCalendar(2019,2,2)).getTimeInMillis()), 
										new Date((new GregorianCalendar(2019,2,10)).getTimeInMillis()), new Date((new GregorianCalendar(2019,2,3)).getTimeInMillis()), bills, documents);
		
		assertNotNull(transactions);
		assertNotNull(transactions.lineItems());
		assertNotNull(transactions.bills());
		assertNotNull(transactions.documents());
		assertNotNull(transactions.contact());
		assertNotNull(transactions.transactionType());
		assertNotNull(transactions.transactionStatus());

		assertEquals(new Long(6789), transactions.id());
		assertEquals(new Integer(345), transactions.transaction_number());
		assertEquals(new Double(980.98), transactions.paymentAmount());
		assertEquals(new Double(678.00), transactions.pendingAmount());
		assertEquals(contacts, transactions.contact());
		assertEquals(transactionTypes, transactions.transactionType());
		assertEquals(transactionStatus, transactions.transactionStatus());
		assertEquals(new Double(19780.00), transactions.tax());
		assertEquals(new Double(456.00), transactions.shipping());
		assertEquals(new Double(956.00), transactions.other());
		assertEquals(new Integer(6521), transactions.user_id());
		assertEquals("tester", transactions.user_name());
		assertEquals(new Integer(4567), transactions.departmentId());
		assertEquals("Electronics", transactions.departmentName());
		assertEquals(lineItems, transactions.lineItems());
		assertEquals(new Date((new GregorianCalendar(2019,2,2)).getTimeInMillis()), transactions.creationdate());
		assertEquals(new Date((new GregorianCalendar(2019,2,10)).getTimeInMillis()), transactions.dueDate());
		assertEquals(new Date((new GregorianCalendar(2019,2,3)).getTimeInMillis()), transactions.deliveryDate());
		assertEquals(bills, transactions.bills());
		assertEquals(documents, transactions.documents());
	}
	
	@Test
	public void TestTransaction() {
		Set<LineItem> lineItems = new HashSet<LineItem>();
		TransactionTypes transactionTypes = new TransactionTypes("Test", "Description");
		
		Transactions transactions = new Transactions(6789, 4567, "Sridhar", transactionTypes, lineItems, 456, "Chemistry");
	
		assertNotNull(transactions);
		assertNotNull(transactions.lineItems());
		assertNotNull(transactions.transactionType());
		assertEquals(new Integer(6789), transactions.user_id());
		assertEquals(new Integer(4567), transactions.transaction_number());
		assertEquals("Sridhar", transactions.user_name());
		assertEquals(transactionTypes, transactions.transactionType());
		assertEquals(lineItems, transactions.lineItems());
		assertEquals(new Integer(456), transactions.departmentId());
		assertEquals("Chemistry", transactions.departmentName());
		
	}
}
