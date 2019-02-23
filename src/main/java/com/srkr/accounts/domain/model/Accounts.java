package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
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

	private Organisation organisation;

	private AccountTypes account_type;

	private Double currentBalance;

	private Date dateUpdated;

	private Boolean isActive;

	public Accounts(String name, String description, AccountTypes account_type) {
		super();
		this.name = name;
		this.description = description;
		this.account_type = account_type;
	}

	@JsonCreator
	public Accounts(@JsonProperty("id") Long id, @JsonProperty("name") String name,
			@JsonProperty("description") String description, @JsonProperty("account_type") AccountTypes account_type,
			@JsonProperty("currentBalance") Double currentBalance, @JsonProperty("dateUpdated") Date dateUpdated,
			@JsonProperty("isActive") Boolean isActive, @JsonProperty("organisation") Organisation organisation) {
		super();
		this.id = id;
		this.name = name;
		this.account_type = account_type;
		this.currentBalance = currentBalance;
		this.dateUpdated = dateUpdated;
		this.isActive = isActive;
		this.organisation = organisation;
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

	public Double currentBalance() {
		return this.currentBalance;
	}

	public Organisation organisation() {
		return this.organisation;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date dateUpdated() {
		return this.dateUpdated;
	}

}
