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
    ben_co NUMBER(1),
    dir_sup NUMBER(1),
    dep_head NUMBER(1),
    CONSTRAINT a_email_fk FOREIGN KEY (email) REFERENCES personal_info(email)
);

CREATE TABLE events(
    email VARCHAR2(100) PRIMARY KEY,
    event VARCHAR2(100),
    dep_head_email VARCHAR2(100),
    ben_co_email VARCHAR2(100),
    dir_sup_email VARCHAR2(100),
    approval_email VARCHAR2(100),
    justification VARCHAR2(300),
    grade VARCHAR2(2),
    pass_fail VARCHAR2(2),
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

import com.project1.dao.TRMSDaoImpl;

public class TestMain {
	public static void main(String args[]) {
		TRMSDaoImpl x = new TRMSDaoImpl();
		System.out.println(x.getAllReimbursements());
		System.out.println(x.getAllEvents());
		System.out.println(x.getAllAccounts());
		System.out.println(x.getAllPersonal());
		x.updateDoubleColumn("tto@zmail.com", "reimbursements", "total", 345.32);
	}
}
