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
@Table(name = "headers", schema = "public")
public class Headers implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 396496395528762590L;
	private Long id;
	private HeaderTypes headerTypes;
	private Accounts accounts;
	private Integer headernumber;
	private Date headerdate;
	private Date dateupdated;

	public Headers() {
	}

	public Headers(Long id, HeaderTypes headerTypes) {
		this.id = id;
		this.headerTypes = headerTypes;
	}

	public Headers(Long id, HeaderTypes headerTypes, Accounts accounts, Integer headernumber, Date headerdate,
			Date dateupdated) {
		this.id = id;
		this.headerTypes = headerTypes;
		this.accounts = accounts;
		this.headernumber = headernumber;
		this.headerdate = headerdate;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "header_type_id")
	public HeaderTypes getHeaderTypes() {
		return this.headerTypes;
	}

	public void setHeaderTypes(HeaderTypes headerTypes) {
		this.headerTypes = headerTypes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	//@Column(name = "account_id")
	public Accounts getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	@Column(name = "headernumber")
	public Integer getHeadernumber() {
		return this.headernumber;
	}

	public void setHeadernumber(Integer headernumber) {
		this.headernumber = headernumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "headerdate", length = 35)
	public Date getHeaderdate() {
		return this.headerdate;
	}

	public void setHeaderdate(Date headerdate) {
		this.headerdate = headerdate;
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
