package com.srkr.accounts.domain.model.postgres;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accounts_balance")
public class AccountBalances {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "beginning_balance")
	private double beginning_balance;
	
	@Column(name = "beginning_balance_date")
	private Date beginning_balance_date;
	
	@Column(name = "current_balance")
	private double current_balance;
	
	@Column(name = "current_balance_date")
	private Date current_balance_date;
	
	@Column(name = "updatedby")
	private Integer updatedBy;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Accounts accounts;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBeginning_balance() {
		return beginning_balance;
	}

	public void setBeginning_balance(double beginning_balance) {
		this.beginning_balance = beginning_balance;
	}

	public Date getBeginning_balance_date() {
		return beginning_balance_date;
	}

	public void setBeginning_balance_date(Date beginning_balance_date) {
		this.beginning_balance_date = beginning_balance_date;
	}

	public double getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(double current_balance) {
		this.current_balance = current_balance;
	}

	public Date getCurrent_balance_date() {
		return current_balance_date;
	}

	public void setCurrent_balance_date(Date current_balance_date) {
		this.current_balance_date = current_balance_date;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	
	

}
