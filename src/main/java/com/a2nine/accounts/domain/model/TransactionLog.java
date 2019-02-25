package com.a2nine.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionLog extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2951759322265821020L;
	
	private Long id;
	
	private Transactions transactions;
	
	private TransactionStatus transactionStatus;
		
	private Date statusDate;
	
	private String user_id;
	
	private String comment;
	
	@JsonCreator
	public TransactionLog(@JsonProperty("id") Long id, @JsonProperty("transactions") Transactions transactions, @JsonProperty("transactionStatus") TransactionStatus transactionStatus,
			@JsonProperty("statusDate") Date statusDate, @JsonProperty("user_id") String user_id, @JsonProperty("comment") String comment) {
		super();
		this.id = id;
		this.transactions = transactions;
		this.transactionStatus = transactionStatus;
		this.statusDate = statusDate;
		this.user_id = user_id;
		this.comment = comment;
	}
	
	
	public Long id() {
		return id;
	}
	
	public Transactions transactions() {
		return transactions;
	}
	
	public TransactionStatus transactionStatus() {
		return transactionStatus;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date statusDate() {
		return statusDate;
	}

	public String user_id() {
		return user_id;
	}
	
	public String comment() {
		return comment;
	}
	

}
