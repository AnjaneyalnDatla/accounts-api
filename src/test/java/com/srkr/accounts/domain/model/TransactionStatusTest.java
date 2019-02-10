package com.srkr.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TransactionStatusTest {
	
	@Test
	public void TestTransactionStatus() {
	
		TransactionStatus transactionStatus = new TransactionStatus(new Long(38213546), "Completed");
		
		assertNotNull(transactionStatus);
		
		assertEquals(new Long(38213546), transactionStatus.id());
		assertEquals("Completed", transactionStatus.value());
	}

}
