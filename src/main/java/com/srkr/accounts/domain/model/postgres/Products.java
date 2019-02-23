package com.srkr.accounts.domain.model.postgres;

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
@Table(name = "products", schema = "public")
public class Products implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5776385275114624510L;
	private Long id;
	private String name;
	private Date dateupdated;
	private String orgName;
	private String orgCode;

	public Products() {
	}

	public Products(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Products(Long id, String name, Date dateupdated, String orgName, String orgCode) {
		this.id = id;
		this.name = name;
		this.dateupdated = dateupdated;
		this.orgName = orgName;
		this.orgCode = orgCode;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateupdated", length = 35)
	public Date getDateupdated() {
		return this.dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	@Column(name = "orgName")
	public String getOrgName() {
		return this.orgName;
	}

	@Column(name = "orgCode")
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
