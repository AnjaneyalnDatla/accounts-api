package com.srkr.accounts.domain.model.postgres;

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

@Entity
@Table(name = "header_types", schema = "public")
public class HeaderTypes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6862733109790402251L;
	private Long id;
	private String name;
	private String description;
	private Date dateupdated;
	private Set<Headers> headerses = new HashSet<Headers>(0);

	public HeaderTypes() {
	}

	public HeaderTypes(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public HeaderTypes(Long id, String name, String description, Date dateupdated, Set<Headers> headerses) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateupdated = dateupdated;
		this.headerses = headerses;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "headerTypes")
	public Set<Headers> getHeaderses() {
		return this.headerses;
	}

	public void setHeaderses(Set<Headers> headerses) {
		this.headerses = headerses;
	}

}
