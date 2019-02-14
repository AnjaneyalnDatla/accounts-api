package com.srkr.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.postgres.AccountTypes;
import com.srkr.accounts.domain.model.repositories.PostgresAccountTypeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresAccountTypeRepositoryTest {

	@Autowired
	PostgresAccountTypeRepository accountTypeRepository; 
	
	@Test
	public void findAll() {
		List<AccountTypes> accountTypes = this.accountTypeRepository.findAll();
		assertNotNull(accountTypes);
	}
}
