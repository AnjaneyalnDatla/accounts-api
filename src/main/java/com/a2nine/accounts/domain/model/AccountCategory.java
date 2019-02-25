package com.a2nine.accounts.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountCategory extends AssertionConcern implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2684990329086993917L;

	private Long id;

	private String name;

	private String description;

	@JsonCreator
	public AccountCategory(@JsonProperty("id") Long id, @JsonProperty("name") String name,
			@JsonProperty("description") String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public AccountCategory(String name, String description) {
		this.name = name;
		this.description = description;
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

}
