package com.srkr.accounts.domain.model.mappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.AccountsBalance;

@Component
public class AccountBalancesMapper {

	public Set<AccountsBalance> toPostgresObjects(
			Set<com.srkr.accounts.domain.model.AccountBalances> AccountsBalanceList) {
		Set<AccountsBalance> AccountsBalance = new HashSet();
		AccountsBalanceList.forEach((accountBalance) -> {
			AccountsBalance.add(toPostgresObject(accountBalance));
		});
		return AccountsBalance;
	}

	public Set<com.srkr.accounts.domain.model.AccountBalances> toDomainObjects(
			Set<AccountsBalance> AccountsBalanceList) {
		Set<com.srkr.accounts.domain.model.AccountBalances> AccountsBalance = new HashSet();
		AccountsBalanceList.forEach((accountBalance) -> {
			AccountsBalance.add(toDomainObject(accountBalance));
		});
		return AccountsBalance;
	}

	private AccountsBalance toPostgresObject(com.srkr.accounts.domain.model.AccountBalances accountsBalance) {
		AccountsBalance pgAccountsBalance = new AccountsBalance();
		pgAccountsBalance.setId(accountsBalance.id());
		pgAccountsBalance.setBeginningBalance(accountsBalance.beginning_balance());
		pgAccountsBalance.setBeginningBalanceDate(accountsBalance.beginning_balance_date());
		pgAccountsBalance.setCurrentBalance(accountsBalance.current_balance());
		pgAccountsBalance.setCurrentBalanceDate(accountsBalance.current_balance_date());
		pgAccountsBalance.setUpdatedby(accountsBalance.updatedBy());
		pgAccountsBalance.setIsActive(accountsBalance.getIsActive());
		return pgAccountsBalance;

	}

	private com.srkr.accounts.domain.model.AccountBalances toDomainObject(
			com.srkr.accounts.domain.model.postgres.AccountsBalance pgAccountsBalance) {
		return new com.srkr.accounts.domain.model.AccountBalances(pgAccountsBalance.getId(),
				pgAccountsBalance.getBeginningBalance(), pgAccountsBalance.getBeginningBalanceDate(),
				pgAccountsBalance.getCurrentBalance(), pgAccountsBalance.getCurrentBalanceDate(),
				pgAccountsBalance.getIsActive(), pgAccountsBalance.getUpdatedby());

	}

}
