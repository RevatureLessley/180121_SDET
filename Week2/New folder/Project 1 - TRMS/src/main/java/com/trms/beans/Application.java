package com.trms.beans;

import java.util.Date;

public class Application {
	private long appId;
	private long empId;
	private Date eventDate; //event_date + event_time
	private String location;
	private String type;
	private String relation;
	private double cost;
	private String description;
	private String gradingFormat;
	private String status;
	
	public Application() {
		super();
	}
	
	public Application(long appId, long empId, Date eventDate, String location,
			String type, String relation, double cost, String description,
			String gradingFormat, String status) {
		super();
		this.appId = appId;
		this.empId = empId;
		this.eventDate = eventDate;
		this.location = location;
		this.type = type;
		this.relation = relation;
		this.cost = cost;
		this.description = description;
		this.gradingFormat = gradingFormat;
		this.status = status;
	}

	/**
	 * @return the appId
	 */
	public long getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(long appId) {
		this.appId = appId;
	}

	/**
	 * @return the empId
	 */
	public long getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(long empId) {
		this.empId = empId;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the gradingFormat
	 */
	public String getGradingFormat() {
		return gradingFormat;
	}

	/**
	 * @param gradingFormat the gradingFormat to set
	 */
	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Application [appId=" + appId + ", empId=" + empId
				+ ", eventDate=" + eventDate + ", location=" + location
				+ ", type=" + type + ", relation=" + relation + ", cost="
				+ cost + ", description=" + description + ", gradingFormat="
				+ gradingFormat + ", status=" + status + "]";
	}
	
	
	
}	
