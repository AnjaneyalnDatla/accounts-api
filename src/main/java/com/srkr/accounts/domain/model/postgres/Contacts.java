package com.srkr.accounts.domain.model.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contacts {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9076213918969754442L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "supplementalId")
	private String supplementalId;

	@Column(name = "isCompany")
	private Boolean isCompany;

	@Column(name = "companyName")
	private String companyName;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "middleName")
	private String middleName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "cellPhone")
	private String cellPhone;

	@Column(name = "homePhone")
	private String homePhone;

	@Column(name = "officePhone")
	private String officePhone;
	
	@Column(name = "faxNumber")
	private String faxNumber;

	@Column(name = "emailAddress")
	private String emailAddress;

	@Column(name = "streetAddress")
	private String streetAddress;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "postalCode")
	private String postalCode;

	@Column(name = "landMark")
	private String landMark;

	@Column(name = "additionalComments")
	private String additionalComments;

	@Column(name = "idType")
	private String idType;

	@Column(name = "idNumber")
	private String idNumber;

	@Column(name = "designation")
	private String designation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplementalId() {
		return supplementalId;
	}

	public void setSupplementalId(String supplementalId) {
		this.supplementalId = supplementalId;
	}

	public Boolean getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(Boolean isCompany) {
		this.isCompany = isCompany;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

}
