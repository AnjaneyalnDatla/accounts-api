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
@Table(name = "contacts", schema = "public")
public class Contacts implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5344323179439558057L;
	private Long id;
	private String supplementalid;
	private Boolean iscompany;
	private String companyname;
	private String firstname;
	private String middlename;
	private String lastname;
	private String cellphone;
	private String homephone;
	private String faxnumber;
	private String officephone;
	private String emailaddress;
	private String streetaddress;
	private String city;
	private String state;
	private String country;
	private String postalcode;
	private String landmark;
	private String additionalcomments;
	private String idtype;
	private String idnumber;
	private String designation;
	private Date dateupdated;
	private Double current_balance;
	private Date balance_updated_date;
	private String orgName;
	private String orgcode;

	public Contacts() {
	}

	public Contacts(Long id) {
		this.id = id;
	}

	public Contacts(Long id, String firstname, String lastname, String streetaddress, String city, String state,
			String country, String postalcode, String idtype, String idnumber, String designation,
			Double current_balance, Date balance_updated_date, String orgName, String orgcode) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalcode = postalcode;
		this.idtype = idtype;
		this.idnumber = idnumber;
		this.designation = designation;
		this.current_balance = current_balance;
		this.balance_updated_date = balance_updated_date;
		this.orgName = orgName;
		this.orgcode = orgcode;
	}

	public Contacts(Long id, String supplementalid, Boolean iscompany, String companyname, String firstname,
			String middlename, String lastname, String cellphone, String homephone, String faxnumber,
			String officephone, String emailaddress, String streetaddress, String city, String state, String country,
			String postalcode, String landmark, String additionalcomments, String idtype, String idnumber,
			String designation, Date dateupdated, Double current_balance, Date balance_updated_date, String orgName,
			String orgcode) {
		this.id = id;
		this.supplementalid = supplementalid;
		this.iscompany = iscompany;
		this.companyname = companyname;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.cellphone = cellphone;
		this.homephone = homephone;
		this.faxnumber = faxnumber;
		this.officephone = officephone;
		this.emailaddress = emailaddress;
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalcode = postalcode;
		this.landmark = landmark;
		this.additionalcomments = additionalcomments;
		this.idtype = idtype;
		this.idnumber = idnumber;
		this.designation = designation;
		this.dateupdated = dateupdated;
		this.current_balance = current_balance;
		this.balance_updated_date = balance_updated_date;
		this.orgName = orgName;
		this.orgcode = orgcode;
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

	@Column(name = "supplementalid")
	public String getSupplementalid() {
		return this.supplementalid;
	}

	public void setSupplementalid(String supplementalid) {
		this.supplementalid = supplementalid;
	}

	@Column(name = "iscompany")
	public Boolean getIscompany() {
		return this.iscompany;
	}

	public void setIscompany(Boolean iscompany) {
		this.iscompany = iscompany;
	}

	@Column(name = "companyname")
	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Column(name = "firstname", nullable = false)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "middlename")
	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	@Column(name = "lastname", nullable = false)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "cellphone")
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "homephone")
	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	@Column(name = "faxnumber")
	public String getFaxnumber() {
		return this.faxnumber;
	}

	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}

	@Column(name = "officephone")
	public String getOfficephone() {
		return this.officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	@Column(name = "emailaddress")
	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	@Column(name = "streetaddress", nullable = false)
	public String getStreetaddress() {
		return this.streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	@Column(name = "city", nullable = false)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", nullable = false)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "country", nullable = false)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "postalcode", nullable = false)
	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	@Column(name = "landmark")
	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	@Column(name = "additionalcomments")
	public String getAdditionalcomments() {
		return this.additionalcomments;
	}

	public void setAdditionalcomments(String additionalcomments) {
		this.additionalcomments = additionalcomments;
	}

	@Column(name = "idtype", nullable = false)
	public String getIdtype() {
		return this.idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	@Column(name = "idnumber", nullable = false)
	public String getIdnumber() {
		return this.idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	@Column(name = "designation", nullable = false)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateupdated", length = 35)
	public Date getDateupdated() {
		return this.dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	@Column(name = "current_balance", nullable = false)
	public Double getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(Double current_balance) {
		this.current_balance = current_balance;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "balance_updated_date", length = 35)
	public Date getBalance_updated_date() {
		return balance_updated_date;
	}

	public void setBalance_updated_date(Date balance_updated_date) {
		this.balance_updated_date = balance_updated_date;
	}

	@Column(name = "orgname")
	public String getOrgName() {
		return this.orgName;
	}

	@Column(name = "orgcode")
	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

}
