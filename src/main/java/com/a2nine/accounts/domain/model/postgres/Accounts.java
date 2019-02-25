package com.a2nine.accounts.domain.model.postgres;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "accounts", schema = "public")
public class Accounts implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4241926837177999029L;
	private Integer id;
	private AccountTypes accountTypes;
	private String name;
	private String description;
	private String orgName;
	private String orgcode;
	private Date dateupdated;
	private Double currentBalance;
	private Boolean isActive;

	public Accounts() {
	}

	public Accounts(Integer id, AccountTypes accountTypes, String name, String description, String orgName,
			String orgcode, Double currentBalance, Boolean isActive) {
		this.id = id;
		this.accountTypes = accountTypes;
		this.name = name;
		this.description = description;
		this.orgName = orgName;
		this.orgcode = orgcode;
		this.currentBalance = currentBalance;
		this.isActive = isActive;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account_type_id")
	public AccountTypes getAccountTypes() {
		return this.accountTypes;
	}

	public void setAccountTypes(AccountTypes accountTypes) {
		this.accountTypes = accountTypes;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "orgname")
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "orgcode")
	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateupdated", length = 35)
	public Date getDateupdated() {
		return this.dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	@Column(name = "currentbalance", nullable = false)
	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Column(name = "isactive", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
