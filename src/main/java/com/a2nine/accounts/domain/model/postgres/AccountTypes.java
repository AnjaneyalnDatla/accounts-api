package com.a2nine.accounts.domain.model.postgres;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "account_types", schema = "public")
public class AccountTypes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3666206085934369527L;
	private Long id;
	private AccountCategory accountCategory;
	private String name;
	private String description;
	private Date dateupdated;
	private Set<Accounts> accountses = new HashSet<Accounts>(0);

	public AccountTypes() {
	}

	public AccountTypes(Long id, AccountCategory accountCategory, String name) {
		this.id = id;
		this.accountCategory = accountCategory;
		this.name = name;
	}

	public AccountTypes(Long id, AccountCategory accountCategory, String name, String description, Date dateupdated,
			Set<Accounts> accountses) {
		this.id = id;
		this.accountCategory = accountCategory;
		this.name = name;
		this.description = description;
		this.dateupdated = dateupdated;
		this.accountses = accountses;
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
	@JoinColumn(name = "category_id", nullable = false)
	public AccountCategory getAccountCategory() {
		return this.accountCategory;
	}

	public void setAccountCategory(AccountCategory accountCategory) {
		this.accountCategory = accountCategory;
	}

	@Column(name = "name", nullable = false)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTypes")
	public Set<Accounts> getAccountses() {
		return this.accountses;
	}

	public void setAccountses(Set<Accounts> accountses) {
		this.accountses = accountses;
	}

}
