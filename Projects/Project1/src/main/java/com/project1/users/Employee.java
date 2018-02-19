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

package com.project1.users;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Employee {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yy");
        System.out.println(sdf.format(cal.getTime()));
	}
}
