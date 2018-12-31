package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 36101921973946273L;

	private Long id;

	private String billNumber;

	private Double amount;

	private Date paymentDate;

	private Boolean isActive;

	private Date dateUpdated;

	private Accounts accounts;
	
	private Boolean isNew;

	@JsonCreator
	public Payment(@JsonProperty("id") Long id, @JsonProperty("billNumber") String billNumber,
			@JsonProperty("amount") Double amount, @JsonProperty("paymentDate") Date paymentDate,
			@JsonProperty("isActive") Boolean isActive, @JsonProperty("dateUpdated") Date dateUpdated,
			@JsonProperty("accounts") Accounts accounts,@JsonProperty("isNew") Boolean isNew) {
		super();
		this.id = id;
		this.billNumber = billNumber;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.isActive = isActive;
		this.dateUpdated = dateUpdated;
		this.accounts = accounts;
		this.isNew = isNew;
	}

	public Long id() {
		return this.id;
	}

	public String billNUmber() {
		return this.billNumber;
	}

	public Double amount() {
		return this.amount;
	}

	public Date paymentDate() {
		return this.paymentDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public Date dateUpdated() {
		return this.dateUpdated;
	}

	public Accounts accounts() {
		return this.accounts;
	}
	
	public Boolean getIsNew(){
		return this.isNew;
	}

}
