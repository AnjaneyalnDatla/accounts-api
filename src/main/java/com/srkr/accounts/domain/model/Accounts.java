package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Accounts extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4360389969050787176L;

	private Long id;

	private String name;

	private String description;

	private AccountTypes account_type;

	private Contacts contacts;

	private List<AccountBalances> accountBalances;

	public Accounts(String name,
			String description,AccountTypes account_type,
			Contacts contacts,
			List<AccountBalances> accountBalances) {
		super();
		this.name = name;
		this.account_type = account_type;
		this.contacts = contacts;
		this.accountBalances = accountBalances;
	}

	@JsonCreator
	public Accounts(@JsonProperty("id") Long id,@JsonProperty("name") String name, @JsonProperty("description") String description,
			@JsonProperty("account_type") AccountTypes account_type, @JsonProperty("contacts") Contacts contacts,
			@JsonProperty("accountBalances") List<AccountBalances> accountBalances) {
		super();
		this.id = id;
		this.name = name;
		this.account_type = account_type;
		this.contacts = contacts;
		this.accountBalances = accountBalances;
	}

	public Long id() {
		return this.id;
	}

	public String name() {
		return this.name;
	}

	public String description() {
		return this.description;
	}

	public AccountTypes account_type() {
		return this.account_type;
	}

	public Contacts contacts() {
		return this.contacts;
	}

	public List<AccountBalances> account_balances() {
		return this.accountBalances;
	}

}
