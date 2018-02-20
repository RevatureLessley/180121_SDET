/*
DROP TABLE personal_info;
DROP TABLE account_info;
DROP TABLE reimbursements;
DROP TABLE event_status;

CREATE TABLE personal_info( --parent table
    email VARCHAR2(100) PRIMARY KEY,
    first_name VARCHAR2(100),
    last_name VARCHAR2(100),
    address VARCHAR2(100),
    job_title VARCHAR2(100),
    join_date VARCHAR2(20)
);

CREATE TABLE account_info(
    email VARCHAR2(100) PRIMARY KEY,
    username VARCHAR2(100),
    pw VARCHAR2(100),
    regular VARCHAR2(1),
    ben_co VARCHAR2(1),
    dir_sup VARCHAR2(1),
    dep_head VARCHAR2(1),
    CONSTRAINT a_email_fk FOREIGN KEY (email) REFERENCES personal_info(email)
);

CREATE TABLE event_status(
    email VARCHAR2(100) PRIMARY KEY,
    event VARCHAR2(100),
    dep_head_email VARCHAR2(100),
    ben_co_email VARCHAR2(100),
    dir_sup_email VARCHAR2(100),
    justification VARCHAR2(300),
    grade VARCHAR2(2),
    pass_fail VARCHAR2(2),
    approval_email VARCHAR2(100),
    start_date VARCHAR2(20),
    end_date VARCHAR2(20),
    CONSTRAINT e_email_fk FOREIGN KEY (email) REFERENCES personal_info(email)
);

CREATE TABLE reimbursements(
    email VARCHAR2(100) PRIMARY KEY,
    available NUMBER(4),
    pending NUMBER(4),
    awarded NUMBER(5),
    total NUMBER(4),
    last_reimb VARCHAR2(20),
    CONSTRAINT r_email_fk FOREIGN KEY (email) REFERENCES personal_info(email)
);
*/

package com.project1.beans;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;

public class Employee {
	String email;
    String firstName;
    String lastName;
    String address;
    String jobTitle;
    String date;
    String lastReimbursementDate;
    char isBenCo;
    char isDirSup;
    char isDepHead;
    
    //reimbursements
    double available;
    double pending;
    double awarded;
    double total;
	public Employee(String email, String firstName, String lastName, String address, String jobTitle, String date,
			String lastReimbursementDate, char isBenCo, char isDirSup, char isDepHead) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.jobTitle = jobTitle;
		this.date = date;	//use calendar for this
		this.lastReimbursementDate = lastReimbursementDate;
		this.isBenCo = isBenCo;
		this.isDirSup = isDirSup;
		this.isDepHead = isDepHead;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getLastReimbursementDate() {
		return lastReimbursementDate;
	}
	
	public void setLastReimbursementDate(String lastReimbursementDate) {
		this.lastReimbursementDate = lastReimbursementDate;
	}
	
	public char getIsBenCo() {
		return isBenCo;
	}
	
	public void setIsBenCo(char isBenCo) {
		this.isBenCo = isBenCo;
	}
	
	public char getIsDirSup() {
		return isDirSup;
	}
	
	public void setIsDirSup(char isDirSup) {
		this.isDirSup = isDirSup;
	}
	
	public char getIsDepHead() {
		return isDepHead;
	}
	
	public void setIsDepHead(char isDepHead) {
		this.isDepHead = isDepHead;
	}
	
	public double getAvailable() {
		return available;
	}
	
	public void setAvailable(double available) {
		this.available = available;
	}
	
	public double getPending() {
		return pending;
	}
	
	public void setPending(double pending) {
		this.pending = pending;
	}
	
	public double getAwarded() {
		return awarded;
	}
	
	public void setAwarded(double awarded) {
		this.awarded = awarded;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
}
