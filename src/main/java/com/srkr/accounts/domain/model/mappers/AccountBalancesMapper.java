package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.AccountsBalance;

@Component
public class AccountBalancesMapper {

	public List<AccountsBalance> toPostgresObjects(
			List<com.srkr.accounts.domain.model.AccountBalances> AccountsBalanceList) {
		List<AccountsBalance> AccountsBalance = new ArrayList<>();
		AccountsBalanceList.forEach((accountBalance) -> {
			AccountsBalance.add(toPostgresObject(accountBalance));
		});
		return AccountsBalance;
	}

	public List<com.srkr.accounts.domain.model.AccountBalances> toDomainObjects(
			List<AccountsBalance> AccountsBalanceList) {
		List<com.srkr.accounts.domain.model.AccountBalances> AccountsBalance = new ArrayList<>();
		AccountsBalanceList.forEach((accountBalance) -> {
			AccountsBalance.add(toDomainObject(accountBalance));
		});
		return AccountsBalance;
	}

	private AccountsBalance toPostgresObject(com.srkr.accounts.domain.model.AccountBalances AccountsBalance) {
		AccountsBalance pgAccountsBalance = new AccountsBalance();
		pgAccountsBalance.setId(AccountsBalance.id());
		pgAccountsBalance.setBeginningBalance(AccountsBalance.beginning_balance());
		pgAccountsBalance.setBeginningBalanceDate(AccountsBalance.beginning_balance_date());
		pgAccountsBalance.setCurrentBalance(AccountsBalance.current_balance());
		pgAccountsBalance.setCurrentBalanceDate(AccountsBalance.current_balance_date());
		pgAccountsBalance.setUpdatedby(AccountsBalance.updatedBy());
		return pgAccountsBalance;

	}

	private com.srkr.accounts.domain.model.AccountBalances toDomainObject(
			com.srkr.accounts.domain.model.postgres.AccountsBalance pgAccountsBalance) {
		return new com.srkr.accounts.domain.model.AccountBalances(pgAccountsBalance.getId(),
				pgAccountsBalance.getBeginningBalance(), pgAccountsBalance.getBeginningBalanceDate(),
				pgAccountsBalance.getCurrentBalance(), pgAccountsBalance.getCurrentBalanceDate(),
				pgAccountsBalance.getUpdatedby());

	}

}
