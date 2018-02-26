package com.trms.beans;

import java.sql.Date;


public class Application {
	private long appId;
	private long empId;
	private String firstname;
	private String lastname;
	private String email;
	private String address;
	private String city;
	private String state;
	private String tel;
	private Date eventDate; // event_date + event_time
	private String location;
	private String type;
	private String relation;
	private double cost;
	private String gradingFormat;
	private String description;
	private String status;
	private String svDecision;
	private String svComment;
	private String dhDecision;
	private String dhComment;
	private String bcDecision;
	private String bcComment;
	private Date submitDate;

	public Application(long appId, long empId, String firstname,
			String lastname, String email, String address, String city,
			String state, String tel, Date eventDate, String location,
			String type, String relation, double cost, String gradingFormat,
			String description, String status, String svDecision,
			String svComment, String dhDecision, String dhComment,
			String bcDecision, String bcComment, Date submitDate) {
		super();
		this.appId = appId;
		this.empId = empId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.tel = tel;
		this.eventDate = eventDate;
		this.location = location;
		this.type = type;
		this.relation = relation;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.description = description;
		this.status = status;
		this.svDecision = svDecision;
		this.svComment = svComment;
		this.dhDecision = dhDecision;
		this.dhComment = dhComment;
		this.bcDecision = bcDecision;
		this.bcComment = bcComment;
		this.submitDate = submitDate;
	}

	public Application() {
		super();
	}

	public Application(String firstname, String lastname, String email,
			String address, String city, String state, String tel,
			Date eventDate, String location, String type, String relation,
			double cost, String gradingFormat, String description) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.tel = tel;
		this.eventDate = eventDate;
		this.location = location;
		this.type = type;
		this.relation = relation;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.description = description;
	}

	
	
	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSvDecision() {
		return svDecision;
	}

	public void setSvDecision(String svDecision) {
		this.svDecision = svDecision;
	}

	public String getSvComment() {
		return svComment;
	}

	public void setSvComment(String svComment) {
		this.svComment = svComment;
	}

	public String getDhDecision() {
		return dhDecision;
	}

	public void setDhDecision(String dhDecision) {
		this.dhDecision = dhDecision;
	}

	public String getDhComment() {
		return dhComment;
	}

	public void setDhComment(String dhComment) {
		this.dhComment = dhComment;
	}

	public String getBcDecision() {
		return bcDecision;
	}

	public void setBcDecision(String bcDecision) {
		this.bcDecision = bcDecision;
	}

	public String getBcComment() {
		return bcComment;
	}

	public void setBcComment(String bcComment) {
		this.bcComment = bcComment;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	@Override
	public String toString() {
		return "Application [appId=" + appId + ", empId=" + empId
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", address=" + address + ", city="
				+ city + ", state=" + state + ", tel=" + tel + ", eventDate="
				+ eventDate + ", location=" + location + ", type=" + type
				+ ", relation=" + relation + ", cost=" + cost
				+ ", gradingFormat=" + gradingFormat + ", description="
				+ description + ", status=" + status + ", svDecision="
				+ svDecision + ", svComment=" + svComment + ", dhDecision="
				+ dhDecision + ", dhComment=" + dhComment + ", bcDecision="
				+ bcDecision + ", bcComment=" + bcComment + ", submitDate="
				+ submitDate + "]";
	}

}
