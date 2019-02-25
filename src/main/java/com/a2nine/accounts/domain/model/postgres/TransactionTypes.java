package com.a2nine.accounts.domain.model.postgres;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transaction_type", schema = "public")
public class TransactionTypes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6862733109790402251L;
	private Long id;
	private String name;
	private String description;
	private Date dateupdated;

	public TransactionTypes() {
	}

	public TransactionTypes(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public TransactionTypes(Long id, String name, String description, Date dateupdated) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateupdated = dateupdated;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
