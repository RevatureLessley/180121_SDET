/*
    SQL worksheet to create the database need to run the TRMS project
*/

-- DROP TABLES
DROP TABLE reimburseaddedinfo;
DROP TABLE reimburseattachments;
DROP TABLE reimbursements;
DROP TABLE learningcenters;
DROP TABLE gradingformats;
DROP TABLE eventtypes;
DROP TABLE usersemp;
DROP TABLE employees;
DROP TABLE empdepartments;
DROP TABLE employeetitles;

-- CREATE TABLES
CREATE TABLE employeetitles(
    title_id NUMBER(8),
    title VARCHAR2(40),
    CONSTRAINT title_id_pk PRIMARY KEY (title_id)
);

CREATE TABLE empdepartments (
    depart_id NUMBER(8),
    depart_name VARCHAR(30),
    CONSTRAINT depart_id_pk PRIMARY KEY (depart_id)
);

CREATE TABLE employees(
    emp_id NUMBER(9),
    emp_fname VARCHAR2(30) NOT NULL,
    emp_lname VARCHAR2(30) NOT NULL,
    emp_reportsto NUMBER(9) NOT NULL,
    emp_department NUMBER(8) NOT NULL,
    emp_title_id NUMBER(8) NOT NULL,
    emp_availreim NUMBER(9,2) DEFAULT 1000.00,
    emp_addr VARCHAR2(30) NOT NULL,
    emp_city VARCHAR2(15) NOT NULL,
    emp_state VARCHAR2(2) NOT NULL,
    emp_zip NUMBER(5) NOT NULL,
    CONSTRAINT emp_id_pk PRIMARY KEY (emp_id),
    CONSTRAINT emp_title_fk FOREIGN KEY (emp_title_id)
    REFERENCES employeetitles (title_id) ON DELETE CASCADE,
    CONSTRAINT emp_depart_fk FOREIGN KEY (emp_department)
    REFERENCES empdepartments (depart_id) ON DELETE CASCADE
);

CREATE TABLE usersemp(
    user_id NUMBER(9),
    useremp_id NUMBER(9),
    user_name VARCHAR2(40) NOT NULL,
    user_password VARCHAR2(20) NOT NULL,
    user_email VARCHAR2(60) NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY (user_id),
    CONSTRAINT useremp_fk FOREIGN KEY (useremp_id)
    REFERENCES employees (emp_id) ON DELETE CASCADE,
    CONSTRAINT username_uniq UNIQUE (user_name)
);

CREATE TABLE eventtypes(
    event_id NUMBER(9),
    event_name VARCHAR2(30) NOT NULL,
    event_coverage NUMBER(3,2) NOT NULL,
    CONSTRAINT event_id_pk PRIMARY KEY (event_id)
);

CREATE TABLE gradingformats(
    format_id NUMBER(9),
    format_type VARCHAR2(20) NOT NULL,
    format_req VARCHAR2(250) NOT NULL,
    CONSTRAINT grade_format_pk PRIMARY KEY(format_id)
);

CREATE TABLE learningcenters (
    center_id NUMBER(9),
    center_name VARCHAR(20) NOT NULL,
    center_addr VARCHAR2(30) NOT NULL,
    center_city VARCHAR2(15) NOT NULL,
    center_state VARCHAR2(2) NOT NULL,
    center_zip NUMBER(5) NOT NULL,
    center_country VARCHAR2(20) NOT NULL,
    CONSTRAINT center_pk PRIMARY KEY(center_id)
);

CREATE TABLE reimbursements(
    reimburse_id NUMBER(9),
    reimburse_emp_id NUMBER(9),
    reimburse_event_id NUMBER(9),
    reimburse_format_id NUMBER(9),
    reimburse_center_id NUMBER(9),
    reimburse_cost NUMBER(9,2),
    reimburse_projreimb NUMBER(9,2),
    reimburse_desc VARCHAR2(300),
    reimburse_grade NUMBER(3),
    reimburse_passgrade NUMBER(3) DEFAULT 75,
    reimburse_workmissed INTERVAL DAY(3) TO SECOND,
    reimburse_datetime DATE,
    reimburse_timestamp TIMESTAMP(5) DEFAULT SYSTIMESTAMP,
    reimburse_workjustify VARCHAR2(300), --not sure if this should be free form typed or something from a list
    reimburse_approvelvl NUMBER(1),
    reimburse_inforeq NUMBER(1) DEFAULT -1, --number similar to applvl level the number dictates who needs to provide additional info
    reimburse_urgent NUMBER(1),
    reimburse_approved NUMBER(1) DEFAULT 0,
    CONSTRAINT reimburse_pk PRIMARY KEY (reimburse_id),
    CONSTRAINT reimb_emp_fk FOREIGN KEY (reimburse_emp_id)
    REFERENCES employees (emp_id) ON DELETE CASCADE,
    CONSTRAINT reimb_event_fk FOREIGN KEY (reimburse_event_id)
    REFERENCES eventtypes(event_id) ON DELETE CASCADE,
    CONSTRAINT reimb_format_fk FOREIGN KEY (reimburse_format_id)
    REFERENCES gradingformats(format_id) ON DELETE CASCADE,
    CONSTRAINT reimb_center_fk FOREIGN KEY (reimburse_center_id)
    REFERENCES learningcenters(center_id) ON DELETE CASCADE
);

CREATE TABLE reimburseattachments(
    attach_id NUMBER(9),
    at_reimburse_id NUMBER(9),
    reimburse_attach BLOB,
    CONSTRAINT attachments_pk PRIMARY KEY(attach_id),
    CONSTRAINT attach_reimburse_fk FOREIGN KEY(at_reimburse_id)
    REFERENCES reimbursements(reimburse_id) ON DELETE CASCADE
);

CREATE TABLE reimburseaddedinfo ( 
    info_id NUMBER(9),
    in_reimburse_id NUMBER(9),
    info_added_by_emp NUMBER(9),
    addinfo_reimburse VARCHAR(500),
    CONSTRAINT addinfo_pk PRIMARY KEY(info_id),
    CONSTRAINT info_reimburse_fk FOREIGN KEY(in_reimburse_id)
    REFERENCES reimbursements(reimburse_id),
    CONSTRAINT info_by_emp_fk FOREIGN KEY(info_added_by_emp)
    REFERENCES employees(emp_id) ON DELETE CASCADE
);

-- SEQUENCES
DROP SEQUENCE useremp_seq;
CREATE SEQUENCE useremp_seq
    START WITH 100
    INCREMENT BY 1;
    
DROP SEQUENCE reimb_seq;
CREATE SEQUENCE reimb_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE reimbinfo_seq;
CREATE SEQUENCE reimbinfo_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE reimbattach_seq;
CREATE SEQUENCE reimbattach_seq
    START WITH 1
    INCREMENT BY 1;

-- TRIGGERS
CREATE OR REPLACE TRIGGER useremp_intrig
BEFORE INSERT ON usersemp
FOR EACH ROW
BEGIN
    IF :new.user_id IS NULL THEN
    SELECT useremp_seq.NEXTVAL INTO :new.user_id FROM dual;
    END IF;
END;
/

-- STORED PROCEDURES
CREATE OR REPLACE PROCEDURE usersemp_insert(empid IN NUMBER, usernamein IN VARCHAR2, passin IN VARCHAR2, email IN VARCHAR2)
IS
BEGIN
    INSERT INTO USERSEMP VALUES(NULL, empid, usernamein, passin, email);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE get_emp(empid IN NUMBER, emp_cursor OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN emp_curosr FOR
    SELECT a.emp_fname, a.emp_lname, a.emp_reportsto, b.depart_name, c.title, a.emp_availreim, 
    a.emp_addr, a.emp_city, a.emp_state, a.emp_zip
    FROM employees a, empdepartments b, employeetitles c
    WHERE emp_id = empid AND a.emp_department = b.depart_id AND a.emp_title_id = c.title_id;
END;
/

SELECT a.emp_fname, a.emp_lname, a.emp_reportsto, b.depart_name, c.title
FROM employees a, empdepartments b, employeetitles c
WHERE emp_id = 2 AND a.emp_department = b.depart_id AND a.emp_title_id = c.title_id;

-- POPULATE TABLES
INSERT INTO empdepartments VALUES(0, 'EXECUTIVES');
INSERT INTO empdepartments VALUES(1, 'CUSTOMER SERVICE');
INSERT INTO empdepartments VALUES(2, 'HUMAN RESOURCES');
INSERT INTO empdepartments VALUES(3, 'PRODUCTION');
INSERT INTO empdepartments VALUES(4, 'SALES');
INSERT INTO empdepartments VALUES(5, 'MARKETING');
INSERT INTO empdepartments VALUES(6, 'ACCOUNTING &' || ' FINANCE');
INSERT INTO empdepartments VALUES(7, 'RESEARCH &' || ' DEVELOPMENT');
INSERT INTO empdepartments VALUES(8, 'INFORMATION TECHNOLOGY');

INSERT INTO employeetitles VALUES(0, 'CEO');
INSERT INTO employeetitles VALUES(1, 'CTO');
INSERT INTO employeetitles VALUES(2, 'COO');
INSERT INTO employeetitles VALUES(50, 'DEPARTMENT HEAD');
INSERT INTO employeetitles VALUES(51, 'MANAGER');
INSERT INTO employeetitles VALUES(100, 'CASHIER');
INSERT INTO employeetitles VALUES(101, 'CALL CENTER REPRESENTATIVE');
INSERT INTO employeetitles VALUES(102, 'CUSTOMER CONSULTANT');
INSERT INTO employeetitles VALUES(103, 'RECEPTIONIST');
INSERT INTO employeetitles VALUES(200, 'BENEFITS COORDINATOR');
INSERT INTO employeetitles VALUES(201, 'COMPENSATION ANALYST');
INSERT INTO employeetitles VALUES(202, 'EMPLOYEE RELATIONS SPECIALIST');
INSERT INTO employeetitles VALUES(203, 'STAFFING CONSULTANT');
INSERT INTO employeetitles VALUES(204, 'UNION ORGANIZER');
INSERT INTO employeetitles VALUES(300, 'ASSEMBLER');
INSERT INTO employeetitles VALUES(301, 'DESIGNER');
INSERT INTO employeetitles VALUES(302, 'INSPECTOR');
INSERT INTO employeetitles VALUES(400, 'STORE MANAGER');
INSERT INTO employeetitles VALUES(401, 'SALES REPRESENTITIVE');
INSERT INTO employeetitles VALUES(402, 'BRAND AMBASSADOR');
INSERT INTO employeetitles VALUES(500, 'ADVERTISING DIRECTOR');
INSERT INTO employeetitles VALUES(501, 'BRAND STRATEGIST');
INSERT INTO employeetitles VALUES(502, 'CONTENT WRITER');
INSERT INTO employeetitles VALUES(600, 'AUDITOR');
INSERT INTO employeetitles VALUES(601, 'BUDGET ANALYST');
INSERT INTO employeetitles VALUES(602, 'TAX SPECIALIST');
INSERT INTO employeetitles VALUES(603, 'CREDIT MANAGER');
INSERT INTO employeetitles VALUES(604, 'LOAN OFFICER');
INSERT INTO employeetitles VALUES(605, 'FINACIAL ANALYST');
INSERT INTO employeetitles VALUES(700, 'PRINCIPLE RESERACH SCIENTIST');
INSERT INTO employeetitles VALUES(701, 'RESEARCH ASSOCIATE');
INSERT INTO employeetitles VALUES(702, 'TECHNICAL WRITER');
INSERT INTO employeetitles VALUES(800, 'PROJECT MANAGER');
INSERT INTO employeetitles VALUES(801, 'DATABASE ADMINISTRATOR');
INSERT INTO employeetitles VALUES(802, 'WEBMASTER');
INSERT INTO employeetitles VALUES(803, 'FULL STACK DEVELOPER');
INSERT INTO employeetitles VALUES(804, 'HELP DESK');

INSERT INTO employees VALUES(1, 'Jazmine', 'Sullivan', 0, 0, 0, 1000, '23 Johnson Ave', 'Philiadelphia', 'PA', 45677);
INSERT INTO employees VALUES(2, 'Lynda', 'Ade', 1, 0, 1, 1000, '13 Bizza Court', 'Queens', 'NY', 13492);
INSERT INTO employees VALUES(3, 'Myra', 'Santos', 0, 2, 200, 1000, '48 Jamestown Street', 'Denver', 'CO', 93368);
INSERT INTO employees VALUES(4, 'Namjoon', 'Kim', 1, 0, 2, 1000, '3-93 Sacho Park', 'Goyang', 'PA', 53538);
INSERT INTO employees VALUES(5, 'Dae-sung', 'Kang', 4, 7, 50, 1000, '23-75 Eastchester Ave', 'Incheon', 'NY', 10742);
INSERT INTO employees VALUES(6, 'Yoon-gi', 'Min', 5, 7, 700, 1000, '27 Charlotte Road', 'Daegu', 'NY', 30582);
INSERT INTO employees VALUES(7, 'Tae-hyung', 'Kim', 6, 7, 702, 1000, '78 Charlotte Road', 'Daegu', 'NY', 30582);