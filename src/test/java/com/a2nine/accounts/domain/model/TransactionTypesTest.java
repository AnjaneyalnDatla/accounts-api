package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.a2nine.accounts.domain.model.TransactionTypes;

public class TransactionTypesTest {
	
	@Test
	public void TestTransactionTypes() {
		TransactionTypes transactionTypes = new TransactionTypes("Trans Name", "Trans Description");
		
		assertNotNull(transactionTypes);

		assertEquals("Trans Name", transactionTypes.name());
		assertEquals("Trans Description", transactionTypes.description());
		
		transactionTypes = new TransactionTypes(new Long(412378), "Trans Name", "Trans Description");
		
		assertNotNull(transactionTypes);

		assertEquals(new Long(412378), transactionTypes.id());
		assertEquals("Trans Name", transactionTypes.name());
		assertEquals("Trans Description", transactionTypes.description());
	}
}
