package com.srkr.accounts.domain.model.postgres;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill", schema = "public")
public class Bill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6141823745088673963L;

	private Long id;

	private Integer transaction_number;

	private Transactions transactions;

	private String billNumber;
	
	private Double amount;

	private Date bill_issued_date;

	private Date bill_payment_date;

	private Set<Payment> payments;

	public Bill() {

	}

	public Bill(Long id, Integer transaction_number, Transactions transactions, String billNumber,
			Double amount,Date bill_issued_date, Date bill_payment_date, Set<Payment> payments) {
		super();
		this.id = id;
		this.transaction_number = transaction_number;
		this.transactions = transactions;
		this.billNumber = billNumber;
		this.amount = amount;
		this.bill_issued_date = bill_issued_date;
		this.bill_payment_date = bill_payment_date;
		this.payments = payments;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(name = "transaction_number")
	public Integer getTransaction_number() {
		return transaction_number;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_id")
	public Transactions getTransactions() {
		return transactions;
	}

	@Column(name = "billNumber")
	public String getBillNumber() {
		return billNumber;
	}

	@Column(name = "bill_issued_date")
	public Date getBill_issued_date() {
		return bill_issued_date;
	}

	@Column(name = "bill_payment_date")
	public Date getBill_payment_date() {
		return bill_payment_date;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", orphanRemoval = true)
	public Set<Payment> getPayments() {
		return payments;
	}

	@Column(name = "bill_amount")
	public Double getAmount() {
		return amount;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public void setBill_issued_date(Date bill_issued_date) {
		this.bill_issued_date = bill_issued_date;
	}

	public void setBill_payment_date(Date bill_payment_date) {
		this.bill_payment_date = bill_payment_date;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public void setTransaction_number(Integer transaction_number) {
		this.transaction_number = transaction_number;
	}

	public void setTransactions(Transactions transactions) {
		this.transactions = transactions;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
