package com.srkr.accounts.domain.model.postgres;

import java.util.Date;
import java.util.HashSet;
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
	private Contacts contact;
	private Date dateupdated;
	private Set<AccountsBalance> accountsBalances = new HashSet<AccountsBalance>(0);

	public Accounts() {
	}

	public Accounts(Integer id, AccountTypes accountTypes, String name, String description) {
		this.id = id;
		this.accountTypes = accountTypes;
		this.name = name;
		this.description = description;
	}

	public Accounts(Integer id, AccountTypes accountTypes, String name, String description, Contacts contact,
			Date dateupdated, Set<AccountsBalance> accountsBalances) {
		this.id = id;
		this.accountTypes = accountTypes;
		this.name = name;
		this.description = description;
		this.contact = contact;
		this.dateupdated = dateupdated;
		this.accountsBalances = accountsBalances;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_type_id")
	public AccountTypes getAccountTypes() {
		return this.accountTypes;
	}

	public void setAccountTypes(AccountTypes accountTypes) {
		this.accountTypes = accountTypes;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	public Contacts getContact() {
		return this.contact;
	}

	public void setContact(Contacts contact) {
		this.contact = contact;
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


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateupdated", length = 35)
	public Date getDateupdated() {
		return this.dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accounts")
	public Set<AccountsBalance> getAccountsBalances() {
		return this.accountsBalances;
	}

	public void setAccountsBalances(Set<AccountsBalance> accountsBalances) {
		this.accountsBalances = accountsBalances;
	}

}
