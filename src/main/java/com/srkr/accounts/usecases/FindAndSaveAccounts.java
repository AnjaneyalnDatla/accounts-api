package com.srkr.accounts.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.Accounts;
import com.srkr.accounts.domain.model.mappers.AccountsMapper;
import com.srkr.accounts.domain.model.repositories.PostgresAccountsRepository;

@Service
public class FindAndSaveAccounts {

	@Autowired
	PostgresAccountsRepository postgresAccountsRepository;

	@Autowired
	AccountsMapper accountsMapper;

	@Transactional
	public List<Accounts> findAllAccounts() {
		return accountsMapper.toListDomainObjects(postgresAccountsRepository.findAll());
	}

	@Transactional
	public Accounts saveAccount(Accounts accounts) {
		return accountsMapper
				.toDomainObject(postgresAccountsRepository.save(accountsMapper.toPostgresObject(accounts)));
	}

	@Transactional
	public Accounts deleteAccount(Accounts accounts) {
		return accountsMapper
				.toDomainObject(postgresAccountsRepository.save(accountsMapper.toPostgresObject(accounts)));
	}

}
