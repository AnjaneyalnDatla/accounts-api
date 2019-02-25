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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "account_category", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AccountCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4143265300431496898L;
	private Long id;
	private String name;
	private String description;
	private Date dateupdated;
	private Set<AccountTypes> accountTypeses = new HashSet<AccountTypes>(0);

	public AccountCategory() {
	}

	public AccountCategory(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public AccountCategory(Long id, String name, String description, Date dateupdated,
			Set<AccountTypes> accountTypeses) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateupdated = dateupdated;
		this.accountTypeses = accountTypeses;
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

	@Column(name = "name", unique = true, nullable = false)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountCategory")
	public Set<AccountTypes> getAccountTypeses() {
		return this.accountTypeses;
	}

	public void setAccountTypeses(Set<AccountTypes> accountTypeses) {
		this.accountTypeses = accountTypeses;
	}

}
