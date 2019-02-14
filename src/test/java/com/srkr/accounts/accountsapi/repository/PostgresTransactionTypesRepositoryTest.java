package com.srkr.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.postgres.TransactionTypes;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionTypesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresTransactionTypesRepositoryTest {

	@Autowired
	private PostgresTransactionTypesRepository transactionTypesRepository;

	private List<TransactionTypes> transactionTypesList;
	private List<TransactionTypes> transactionTypesDeleteList = new ArrayList<>();

	@Before
	public void setUp() {
		this.transactionTypesList = new ArrayList<>();

		TransactionTypes transactionTypes = new  TransactionTypes();
		transactionTypes.setDateupdated(new Date());
		transactionTypes.setDescription("Master Card");
		transactionTypes.setName("new Transaction");
		
		transactionTypesList.add(transactionTypes);
	}
	
	@Test
	public void findAll() {
		List<TransactionTypes> transactionTypes = this.transactionTypesRepository.findAll();
		assertNotNull(transactionTypes);
	}
	
	@Test
	public void save() {
		transactionTypesList.forEach((transactionTypes) -> {
			transactionTypesDeleteList.add(this.transactionTypesRepository.save(transactionTypes));
		});
		assertTrue(transactionTypesDeleteList.size() > 0);
	}
	
	@Test
	public void findById() {
		transactionTypesDeleteList.forEach((transactionTypes) -> {
			assertNotNull(this.transactionTypesRepository.findById(transactionTypes.getId()));
		});
	}
	
	
	/*@After
	public void after() {
		transactionTypesDeleteList.forEach(transactionTypes -> {
			this.transactionTypesRepository.delete(transactionTypes);
		});
	}*/

}
