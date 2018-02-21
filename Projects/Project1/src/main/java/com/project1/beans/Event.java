package com.project1.beans;

public class Event {
	String email;
    String event;
    String depHeadEmail;
    String benCoEmail;
    String dirSupEmail;
    String approvalEmail;
    String justification;
    String grade;
    String passFail;
    String startDate;
    String endDate;
    
	public Event(String email, String event, String depHeadEmail, String benCoEmail, String dirSupEmail,
			String approvalEmail, String justification, String grade, String passFail, String startDate,
			String endDate) {
		super();
		this.email = email;
		this.event = event;
		this.depHeadEmail = depHeadEmail;
		this.benCoEmail = benCoEmail;
		this.dirSupEmail = dirSupEmail;
		this.approvalEmail = approvalEmail;
		this.justification = justification;
		this.grade = grade;
		this.passFail = passFail;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getBenCoEmail() {
		return benCoEmail;
	}
	public void setBenCoEmail(String benCoEmail) {
		this.benCoEmail = benCoEmail;
	}
	public String getDirSupEmail() {
		return dirSupEmail;
	}
	public void setDirSupEmail(String dirSupEmail) {
		this.dirSupEmail = dirSupEmail;
	}
	public String getApprovalEmail() {
		return approvalEmail;
	}
	public void setApprovalEmail(String approvalEmail) {
		this.approvalEmail = approvalEmail;
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
	public String getPassFail() {
		return passFail;
	}
	public void setPassFail(String passFail) {
		this.passFail = passFail;
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
}
