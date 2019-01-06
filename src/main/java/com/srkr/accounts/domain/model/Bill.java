package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bill extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6141823745088673963L;

	private Long id;

	private Integer transaction_number;

	private String billNumber;

	private Double amount;

	private Date bill_issued_date;

	private Date bill_payment_date;

	private Set<Payment> payments;

	@JsonCreator
	public Bill(@JsonProperty("id") Long id, @JsonProperty("transaction_number") Integer transaction_number,
			@JsonProperty("billNumber") String billNumber, @JsonProperty("amount") Double amount,
			@JsonProperty("bill_issued_date") Date bill_issued_date,
			@JsonProperty("bill_payment_date") Date bill_payment_date,
			@JsonProperty("payments") Set<Payment> payments) {
		super();
		this.id = id;
		this.transaction_number = transaction_number;
		this.billNumber = billNumber;
		this.amount = amount;
		this.bill_issued_date = bill_issued_date;
		this.bill_payment_date = bill_payment_date;
		this.payments = payments;
	}

	public Long id() {
		return this.id;
	}

	public Integer transaction_number() {
		return transaction_number;
	}

	public String billNumber() {
		return this.billNumber;
	}

	public Double amount() {
		return this.amount;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date bill_issued_date() {
		return this.bill_issued_date;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date bill_payment_date() {
		return this.bill_payment_date;
	}

	public Set<Payment> payments() {
		return this.payments;
	}

}
