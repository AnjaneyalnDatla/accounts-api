package com.a2nine.accounts.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a2nine.accounts.domain.model.AccountTypes;
import com.a2nine.accounts.domain.model.Accounts;
import com.a2nine.accounts.domain.model.mappers.AccountTypesMapper;
import com.a2nine.accounts.domain.model.mappers.AccountsMapper;
import com.a2nine.accounts.domain.model.repositories.PostgresAccountTypeRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresAccountsRepository;

@Service
public class FindAndSaveAccounts {

	@Autowired
	PostgresAccountsRepository postgresAccountsRepository;
	@Autowired
	PostgresAccountTypeRepository postgresAccountTypeRepository;

	@Autowired
	AccountsMapper accountsMapper;

	@Autowired
	AccountTypesMapper accountTypesMapper;

	@Transactional
	public List<Accounts> findAllAccountsByOrgcode(String orgCode) {
		return accountsMapper.toListDomainObjects(postgresAccountsRepository.findByOrgcode(orgCode));
	}

	@Transactional
	public Accounts findAccountByIdAndOrgcode(Long id, String orgCode) {
		return accountsMapper
				.toDomainObject(this.postgresAccountsRepository.findByIdAndOrgcode(id.intValue(), orgCode));
	}

	@Transactional
	public List<AccountTypes> findAllAccountTypes() {
		return accountTypesMapper.toListDomainObjects(postgresAccountTypeRepository.findAll());
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
