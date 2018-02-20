package com.project1.beans;

public class Event {
	String event;
    String depHeadEmail;
    String justification;
    String grade;
    String approvalEmail;
    String startDate;
    String endDate;
    char passFail;
    
	public Event(String event, String depHeadEmail, String justification, String grade, String approvalEmail,
			String startDate, String endDate, char passFail) {
		super();
		this.event = event;
		this.depHeadEmail = depHeadEmail;
		this.justification = justification;
		this.grade = grade;
		this.approvalEmail = approvalEmail;
		this.startDate = startDate;
		this.endDate = endDate;
		this.passFail = passFail;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getDepHeadEmail() {
		return depHeadEmail;
	}

	public void setDepHeadEmail(String depHeadEmail) {
		this.depHeadEmail = depHeadEmail;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getApprovalEmail() {
		return approvalEmail;
	}

	public void setApprovalEmail(String approvalEmail) {
		this.approvalEmail = approvalEmail;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public char getPassFail() {
		return passFail;
	}

	public void setPassFail(char passFail) {
		this.passFail = passFail;
	}
}
