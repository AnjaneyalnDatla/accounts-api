package com.srkr.accounts.domain.model.postgres;

import java.util.List;

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
@Table(name = "accounts")
public class Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "account_type_id", referencedColumnName = "id")
	private AccountTypes account_type;

	@ManyToOne
	@JoinColumn(name = "contact_id", referencedColumnName = "id")
	private Contacts contact;

	@OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<AccountBalances> accountBalances;

	public List<AccountBalances> getAccountBalances() {
		return accountBalances;
	}

	public void setAccountBalances(List<AccountBalances> accountBalances) {
		this.accountBalances = accountBalances;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountTypes getAccount_type() {
		return account_type;
	}

	public void setAccount_type(AccountTypes account_type) {
		this.account_type = account_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Contacts getContact() {
		return contact;
	}

	public void setContact(Contacts contact) {
		this.contact = contact;
	}

}
