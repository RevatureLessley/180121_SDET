/*
DROP TABLE reimbursements;

CREATE TABLE reimbursements( --child table
    email VARCHAR2(100) PRIMARY KEY,
    available NUMBER(4),
    pending NUMBER(4),
    awarded NUMBER(5),
    total NUMBER(4),
    last_reimb DATE
);

INSERT INTO reimbursements
VALUES ('RRose@zmail.com', 0, 0, 0, 0, '25.Feb.1996');

CREATE TABLE event_status( --child table
    email VARCHAR2(100),
    event VARCHAR2(100),
    dep_head_email VARCHAR2(100),
    justification VARCHAR2(300),
    grade VARCHAR2(2),
    pass_fail VARCHAR2(2),
    approval_email VARCHAR2(4),
    start_date DATE,
    end_date DATE
);

CREATE TABLE personal_info( --parent table
    email VARCHAR2(100) PRIMARY KEY,
    first_name VARCHAR2(100),
    last_name VARCHAR2(100),
    address VARCHAR2(100),
    job_title VARCHAR2(100),
    join_date DATE
);

--create account table: account status 1. benco 2. supervisor, etc...
SELECT * FROM reimbursements;
commit;
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
    
	@Override
	public String toString() {
		return "Employee [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", jobTitle=" + jobTitle + ", date=" + date + "]";
	}

	public Employee(String email, String firstName, String lastName, String address, String jobTitle, String date) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.jobTitle = jobTitle;
		this.date = date;
	}
	
	/*public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yy");
        System.out.println(sdf.format(cal.getTime()));
	}*/
	
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
}
