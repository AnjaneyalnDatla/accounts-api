package com.srkr.accounts.domain.model.postgres;

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
@Table(name = "accounts_balance", schema = "public")
public class AccountsBalance implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6701027080784820708L;
	private Long id;
	private Accounts accounts;
	private Double beginningBalance;
	private Date beginningBalanceDate;
	private Double currentBalance;
	private Date currentBalanceDate;
	private Integer updatedby;
	private Date dateupdated;

	public AccountsBalance() {
	}

	public AccountsBalance(Long id, Accounts accounts, Double beginningBalance, Double currentBalance) {
		this.id = id;
		this.accounts = accounts;
		this.beginningBalance = beginningBalance;
		this.currentBalance = currentBalance;
	}

	public AccountsBalance(Long id, Accounts accounts, Double beginningBalance, Date beginningBalanceDate,
			Double currentBalance, Date currentBalanceDate, Integer updatedby, Date dateupdated) {
		this.id = id;
		this.accounts = accounts;
		this.beginningBalance = beginningBalance;
		this.beginningBalanceDate = beginningBalanceDate;
		this.currentBalance = currentBalance;
		this.currentBalanceDate = currentBalanceDate;
		this.updatedby = updatedby;
		this.dateupdated = dateupdated;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	public Accounts getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	@Column(name = "beginning_balance", nullable = false, precision = 17, scale = 17)
	public Double getBeginningBalance() {
		return this.beginningBalance;
	}

	public void setBeginningBalance(Double beginningBalance) {
		this.beginningBalance = beginningBalance;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "beginning_balance_date", length = 13)
	public Date getBeginningBalanceDate() {
		return this.beginningBalanceDate;
	}

	public void setBeginningBalanceDate(Date beginningBalanceDate) {
		this.beginningBalanceDate = beginningBalanceDate;
	}

	@Column(name = "current_balance", nullable = false, precision = 17, scale = 17)
	public Double getCurrentBalance() {
		return this.currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "current_balance_date", length = 13)
	public Date getCurrentBalanceDate() {
		return this.currentBalanceDate;
	}

	public void setCurrentBalanceDate(Date currentBalanceDate) {
		this.currentBalanceDate = currentBalanceDate;
	}

	@Column(name = "updatedby")
	public Integer getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateupdated", length = 35)
	public Date getDateupdated() {
		return this.dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

}
