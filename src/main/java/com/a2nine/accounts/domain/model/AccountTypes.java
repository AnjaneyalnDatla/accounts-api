package com.a2nine.accounts.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountTypes extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6753973735623212300L;

	private Long id;

	private String name;

	private AccountCategory accountCategory;

	private String description;

	public AccountTypes(Long id) {
		super();
		this.id = id;
	}

	@JsonCreator
	public AccountTypes(@JsonProperty("id") Long id, @JsonProperty("name") String name,
			@JsonProperty("accountCategory") AccountCategory accountCategory,
			@JsonProperty("description") String description) {
		super();
		this.id = id;
		this.name = name;
		this.accountCategory = accountCategory;
		this.description = description;
	}

	public AccountTypes(String name, AccountCategory accountCategory, String description) {
		super();
		this.name = name;
		this.accountCategory = accountCategory;
		this.description = description;
	}

	public Long id() {
		return id;
	}

	public String name() {
		return name;
	}

	public AccountCategory accountCategory() {
		return accountCategory;
	}

	public String description() {
		return description;
	}

}
