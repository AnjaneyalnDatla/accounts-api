package com.srkr.accounts.domain.model.postgres;

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
@Table(name = "accounts")
public class Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "account_type_id", referencedColumnName = "id")
	private AccountTypes account_type;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_account_id", referencedColumnName = "id")
	private Accounts parent_account;

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

	public Accounts getParent_account() {
		return parent_account;
	}

	public void setParent_account(Accounts parent_account) {
		this.parent_account = parent_account;
	}

}
