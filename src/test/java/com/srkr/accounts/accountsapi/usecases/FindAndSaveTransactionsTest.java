package com.srkr.accounts.accountsapi.usecases;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.usecases.FindAndSaveTransactions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindAndSaveTransactionsTest {

	@Autowired
	private FindAndSaveTransactions findAndSaveTransactions;

	@Test
	public void findAllTransactions() {
		assertNotNull(findAndSaveTransactions.findAllTransactions());
	}
	
	@Test
	public void findAllTransactionsByUsername() {
		assertNotNull(findAndSaveTransactions.findTransactionsByUsername("Bunny"));
	}

}
