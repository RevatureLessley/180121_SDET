package com.revature.beans;

public class Reimbursement {

	private int rei_id; // Reimbursement id
	private int emp_id; // Employee Id
	private String fname; // Employee First Name
	private String lname; // Employee Last Name
	private String dateOf; // Date of Event
	private String time; // Time of Event
	private String location; // Location of Event
	private String desc; // Description of Event
	private int cost; 	// Cost of the Event
	private String gradingFormat; // Grading format used by the Event
	private String typeOfEvent; // Type Of Event
	private String work_related_justification; // Work related justification
	private String grade_received; 	// Grade received for Event
    private int grade_attachment_bit; // Whether or not there is an attachment for the grade
    private int attachment_bit; 	  // Whether or not there is an attachment for reimbursement
	
    /**Constructor for Reimbursement class that has all arguments.**/
	public Reimbursement(int rei_id, int emp_id, String fname, String lname, String dateOf, String time, String location,
			 String desc, int cost, String gradingFormat, String typeOfEvent, String work_related_justification,
			 String grade_received, int grade_attachment_bit, int attachment_bit) {
		this.rei_id = rei_id;
		this.emp_id = emp_id;
		this.fname = fname;
		this.lname = lname;
		this.dateOf = dateOf;
		this.time = time;
		this.location = location;
		this.desc = desc;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.typeOfEvent = typeOfEvent;
		this.work_related_justification = work_related_justification;
		this.grade_received = grade_received;
		this.grade_attachment_bit = grade_attachment_bit;
		this.attachment_bit = attachment_bit;
	}
	
	/**Sets the value for rei_Id of this Reimbursement Object**/
	public void setRei_id(int rei_id) {this.rei_id = rei_id;}
	/**Sets the value for emp_Id of this Reimbursement Object**/
	public void setEmp_id(int emp_id) {this.emp_id = emp_id;}
	/**Sets the value for fname of this Reimbursement Object**/
	public void setFname(String fname) {this.fname = fname;}
	/**Sets the value for lname of this Reimbursement Object**/
	public void setLname(String lname) {this.lname = lname;}
	/**Sets the value for dateOf of this Reimbursement Object**/
	public void setDateOf(String dateOf) {this.dateOf = dateOf;}
	/**Sets the value for time of this Reimbursement Object**/
	public void setTime(String time) {this.time = time;}
	/**Sets the value for location of this Reimbursement Object**/
	public void setLocation(String location) {this.location = location;}
	/**Sets the value for desc of this Reimbursement Object**/
	public void setDesc(String desc) {this.desc = desc;}
	/**Sets the value for cost of this Reimbursement Object**/
	public void setCost(int cost) {this.cost = cost;}
	/**Sets the value for gradingFormat of this Reimbursement Object**/
	public void setGradingFormat(String gradingFormat) {this.gradingFormat = gradingFormat;}
	/**Sets the value for typeOfEvent of this Reimbursement Object**/
	public void setTypeOfEvent(String typeOfEvent) {this.typeOfEvent = typeOfEvent;}
	/**Sets the value for work_related_justification of this Reimbursement Object**/
	public void setWork_related_justification(String work_related_justification) {this.work_related_justification = work_related_justification;}
	/**Sets the value for grade_received of this Reimbursement Object**/
	public void setGrade_received(String grade_received) {this.grade_received = grade_received;}
	/**Sets the value for grade_attachment_bit of this Reimbursement Object**/
	public void setGrade_attachment_bit(int grade_attachment_bit) {this.grade_attachment_bit = grade_attachment_bit;}
	/**Sets the value for attachment_bit of this Reimbursement Object**/
	public void setAttachment_bit(int attachment_bit) {
		this.attachment_bit = attachment_bit;
	}

	
	/**Returns the value for rei_id of this Reimbursement Object**/
	public int getRei_id() {return rei_id;}
	/**Returns the value for emp_id of this Reimbursement Object**/
	public int getEmp_id() {return emp_id;}
	/**Returns the value for fname of this Reimbursement Object**/
	public String getFname() {return fname;}
	/**Returns the value for lname of this Reimbursement Object**/
	public String getLname() {return lname;}
	/**Returns the value for dateOf of this Reimbursement Object**/
	public String getDateOf() {return dateOf;}
	/**Returns the value for time of this Reimbursement Object**/
	public String getTime() {return time;}
	/**Returns the value for location of this Reimbursement Object**/
	public String getLocation() {return location;}
	/**Returns the value for desc of this Reimbursement Object**/
	public String getDesc() {return desc;}
	/**Returns the value for cost of this Reimbursement Object**/
	public int getCost() {return cost;}
	/**Returns the value for gradingFormat of this Reimbursement Object**/
	public String getGradingFormat() {return gradingFormat;}
	/**Returns the value for typeOfEvent of this Reimbursement Object**/
	public String getTypeOfEvent() {return typeOfEvent;}
	/**Returns the value for work_related_justification of this Reimbursement Object**/
	public String getWork_related_justification() {return work_related_justification;}
	/**Returns the value for grade_received of this Reimbursement Object**/
	public String getGrade_received() {return grade_received;}
	/**Returns the value for grade-attachment_bit of this Reimbursement Object**/
	public int getGrade_attachment_bit() {return grade_attachment_bit;}
	/**Returns the value for attachment_bit of this Reimbursement Object**/
	public int getAttachment_bit() {return attachment_bit;}
	
	@Override
	public String toString() {
		return "Reimbursement [rei_id=" + rei_id + ",emp_id=" + emp_id + ",First Name=" + fname + ",Last Name=" + lname 
				+ ",Date Of=" + dateOf + ",time=" + time + ",location=" + location + ",descripstion=" + desc + ",cost=" + cost
				+ ",grading format=" + gradingFormat + ",type of event=" + typeOfEvent + ",work related justification=" + 
				work_related_justification + "]";
	}
	
	
	
}
