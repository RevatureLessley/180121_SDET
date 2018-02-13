package com.revature.beans;

public class Reimbursement {

	private int rei_id; 
	private int emp_id;
	private String fname;
	private String lname;
	private String dateOf;
	private String time;
	private String location;
	private String desc;
	private int cost;
	private String gradingFormat;
	private String typeOfEvent;
	private String work_related_justification;
	
	public void setRei_id(int rei_id) {this.rei_id = rei_id;}
	public void setEmp_id(int emp_id) {this.emp_id = emp_id;}
	public void setFname(String fname) {this.fname = fname;}
	public void setLname(String lname) {this.lname = lname;}
	public void setDateOf(String dateOf) {this.dateOf = dateOf;}
	public void setTime(String time) {this.time = time;}
	public void setLocation(String location) {this.location = location;}
	public void setDesc(String desc) {this.desc = desc;}
	public void setCost(int cost) {this.cost = cost;}
	public void setGradingFormat(String gradingFormat) {this.gradingFormat = gradingFormat;}
	public void setTypeOfEvent(String typeOfEvent) {this.typeOfEvent = typeOfEvent;}
	public void setWork_related_justification(String work_related_justification) {this.work_related_justification = work_related_justification;}
	
	public int getRei_id() {return rei_id;}
	public int getEmp_id() {return emp_id;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getDateOf() {return dateOf;}
	public String getTime() {return time;}
	public String getLocation() {return location;}
	public String getDesc() {return desc;}
	public int getCost() {return cost;}
	public String getGradingFormat() {return gradingFormat;}
	public String getTypeOfEvent() {return typeOfEvent;}
	public String getWork_related_justification() {return work_related_justification;}
	
	@Override
	public String toString() {
		return "Reimbursement [rei_id=" + rei_id + ",emp_id=" + emp_id + ",First Name=" + fname + ",Last Name=" + lname 
				+ ",Date Of=" + dateOf + ",time=" + time + ",location=" + location + ",descripstion=" + desc + ",cost=" + cost
				+ ",grading format=" + gradingFormat + ",type of event=" + typeOfEvent + ",work related justification=" + 
				work_related_justification + "]";
	}
	
	
	
}
