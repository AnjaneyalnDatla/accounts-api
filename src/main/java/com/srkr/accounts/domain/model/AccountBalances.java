package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountBalances extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5162027143240615333L;

	private Long id;

	private Double beginning_balance;

	private Date beginning_balance_date;

	private Double current_balance;

	private Date current_balance_date;

	private Integer updatedBy;

	public AccountBalances(Double beginning_balance, Date beginning_balance_date, Double current_balance,
			Date current_balance_date, Integer updatedBy) {
		super();
		this.beginning_balance = beginning_balance;
		this.beginning_balance_date = beginning_balance_date;
		this.current_balance = current_balance;
		this.current_balance_date = current_balance_date;
		this.updatedBy = updatedBy;
	}

	@JsonCreator
	public AccountBalances(@JsonProperty("id") Long id, @JsonProperty("beginning_balance") Double beginning_balance,
			@JsonProperty("beginning_balance_date") Date beginning_balance_date,
			@JsonProperty("current_balance") Double current_balance,
			@JsonProperty("current_balance_date") Date current_balance_date,
			@JsonProperty("updatedBy") Integer updatedBy) {
		super();
		this.id = id;
		this.beginning_balance = beginning_balance;
		this.beginning_balance_date = beginning_balance_date;
		this.current_balance = current_balance;
		this.current_balance_date = current_balance_date;
		this.updatedBy = updatedBy;
	}

	public Long id() {
		return this.id;
	}

	public Double beginning_balance() {
		return this.beginning_balance;
	}

	public Date beginning_balance_date() {
		return this.beginning_balance_date;
	}

	public Double current_balance() {
		return this.current_balance;
	}

	public Date current_balance_date() {
		return this.current_balance_date;
	}

	public Integer updatedBy() {
		return this.updatedBy;
	}
}
