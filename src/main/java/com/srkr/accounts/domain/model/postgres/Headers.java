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
@Table(name = "headers")
public class Headers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "headernumber")
	private Integer headernumber;

	@Column(name = "headerdate")
	private Date headerdate;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "header_type_id", referencedColumnName = "id")
	private HeaderTypes headerTypes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHeadernumber() {
		return headernumber;
	}

	public void setHeadernumber(Integer headernumber) {
		this.headernumber = headernumber;
	}

	public Date getHeaderdate() {
		return headerdate;
	}

	public void setHeaderdate(Date headerdate) {
		this.headerdate = headerdate;
	}

	public HeaderTypes getHeaderTypes() {
		return headerTypes;
	}

	public void setHeaderTypes(HeaderTypes headerTypes) {
		this.headerTypes = headerTypes;
	}

}
