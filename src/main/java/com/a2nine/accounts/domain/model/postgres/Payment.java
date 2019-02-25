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

@Entity
@Table(name = "payments", schema = "public")
public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 36101921973946273L;

	private Long id;
	private String billNumber;
	private Bill bill;
	private Double amount;
	private Date paymentDate;
	private Boolean isActive;
	private Date dateUpdated;
	private Accounts accounts;
	private String orgName;
	private String orgCode;

	public Payment() {

	}

	public Payment(Long id, String billNumber, Bill bill, Double amount, Date paymentDate, Boolean isActive,
			Date dateUpdated, Accounts accounts, String orgName, String orgCode) {
		super();
		this.id = id;
		this.billNumber = billNumber;
		this.bill = bill;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.isActive = isActive;
		this.dateUpdated = dateUpdated;
		this.accounts = accounts;
		this.orgName = orgName;
		this.orgCode = orgCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(name = "billNumber")
	public String getBillNumber() {
		return billNumber;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_id")
	public Bill getBill() {
		return bill;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	@Column(name = "payment_date")
	public Date getPaymentDate() {
		return paymentDate;
	}

	@Column(name = "isactive", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	@Column(name = "dateupdated")
	public Date getDateUpdated() {
		return dateUpdated;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	public Accounts getAccounts() {
		return accounts;
	}

	@Column(name = "orgname")
	public String getOrgName() {
		return this.orgName;
	}

	@Column(name = "orgcode")
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
