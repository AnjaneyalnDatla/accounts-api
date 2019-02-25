package com.a2nine.accounts.domain.model.postgres;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transaction_log", schema = "public")
public class TransactionLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1308839365576658757L;

	private Long id;
	
	private Transactions transactions;
	
	private TransactionStatus transactionStatus;
		
	private Date statusDate;
	
	private String userId;
	
	private String comment;
	
	public TransactionLog() {
	}
	
	public TransactionLog(Long id, TransactionStatus transactionStatus, Transactions transactions, Date statusDate, String userId, String comment ) {
		this.id = id;
		this.transactionStatus = transactionStatus;
		this.statusDate = statusDate;
		this.userId = userId;
		this.comment = comment;
		this.transactions = transactions;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transaction_id")
	public Transactions getTransactions() {
		return transactions;
	}

	public void setTransactions(Transactions transactions) {
		this.transactions = transactions;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "statusDate", length = 35)
	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	@Column(name = "user_id", nullable = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "comment", nullable = false)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
