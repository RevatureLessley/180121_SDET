--Intial DDL

DROP TABLE employees;

DROP TABLE departments;
DROP TABLE coverage;
DROP TABLE reimbursements;
DROP TABLE rejected;
/

CREATE TABLE employees(
    emp_id NUMBER(6) PRIMARY KEY,
    fname VARCHAR2(60),
    lname VARCHAR2(60),
    username VARCHAR(32) UNIQUE,
    password VARCHAR(32),
    email VARCHAR2(100) UNIQUE,
    role VARCHAR2(32),
    department VARCHAR2(32),
    supervisor VARCHAR(100),
    amount NUMBER(6)
    );

    
    
    
CREATE TABLE departments (
    dep_id NUMBER(6),
    name VARCHAR2(32) PRIMARY KEY,
    location VARCHAR2(4),
    head VARCHAR2(32)
);

CREATE TABLE coverage(
    cov_id NUMBER(6) PRIMARY KEY,
    name VARCHAR2 (100),
    percent NUMBER(3)
);

CREATE TABLE reimbursements(
    rei_id NUMBER(6) PRIMARY KEY,
    emp_id NUMBER(6),
    fname VARCHAR2(100),
    lname VARCHAR2 (100),
    dateof VARCHAR2 (11),
    time VARCHAR2 (7),
    location VARCHAR2 (200),
    description VARCHAR2 (200),
    cost NUMBER(6),
    grading_format VARCHAR2(200),
    type_of_event VARCHAR2(200),
    work_related_justification VARCHAR2(200),
    aprroval_state NUMBER(1),
    attachment BLOB
);

CREATE TABLE rejected(
    rej_id NUMBER(6),
    rei_id NUMBER(6),
    reason VARCHAR2(300),
    attachment BLOB
);

/
--Initial DML


INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department)
VALUES (1,'phoenix','nakamura','chh','Blazing123', 'chh@outlook.com','DEC-21-1980','91-43 124th street','sparta', 'NY',14518,'head','production');

INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department)
VALUES (2,'martha','clairevoynce','123martha','Martha123','marthalovesgod@dogmail.com','MAR-03-1985','23-45 22nd street','baking', 'NY',56418,'head','marketing');

INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department)
VALUES (3,'armin','bradshow','CoolSalute334','BlondeH4','ihatetitans@dogmail.com','JAN-14-1994','76-21 42nd street','Wall', 'NY',14567,'head','techincal');

INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department)
VALUES (4,'jennifer','poras','okami','ilovealex','porasokami@dogmail.com','AUG-16-1992','23-48 22nd street','baking', 'NY',56418,'head','art');

INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department)
VALUES (5,'lucas','jaeger','liger333','BrastleTiger423','hero@dogmail.com','JUN-30-1988','91-45 115th street','queens', 'NY',11419,'head','game design');

INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department)
VALUES (6,'camilo','arrauth','kcam','YankeesRock','deathcam320@dogmail.com','MAR-21-1991','91-30 93rd street','queens', 'NY',11416,'head','business');

INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department)
VALUES (7,'arin','hanson','lovemegaman','JumpAndShoot','egoraptor@catmail.com','JAN-06-1987','45 1st street','somewhere', 'NY',10023,'head','quality assurance');


INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department,supervisor)
VALUES (8,'christian','diaz','cdiaz8369','Araceli8','cdiaz@dogmail.com','DEC-19-1990','91-52 112th street','queens', 'NY',11418,'employee','game design','jasmin');

INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department,supervisor)
VALUES (9,'jasmin','tepale','funsizejas','live4free','jtepale@dogmail.com','APR-28-1991','91-52 112th street','queens', 'NY',11418,'supervisor','game design','lucas');

INSERT INTO employees (emp_id, fname, lname,username,password, email, dob, address,city,state, zipcode, role,department,supervisor)
VALUES (10,'clara','martinez','clara1953','Aquamarina1','clara1953@dogmail','OCT-20-1953','91-54 112th street','queens', 'NY',11418,'benco','benefits','null');
/


INSERT INTO departments (dep_id, name,location,head)
VALUES(1,'production','C101','phoenix');

INSERT INTO departments (dep_id, name,location,head)
VALUES(2,'marketing','C201','martha');

INSERT INTO departments (dep_id, name,location,head)
VALUES(3,'technical','C104','armin');

INSERT INTO departments (dep_id, name,location,head)
VALUES(4,'art','C103','jennifer');

INSERT INTO departments (dep_id, name,location,head)
VALUES(5,'game design','C102','lucas');

INSERT INTO departments (dep_id, name,location,head)
VALUES(6,'business','C202','camilo');

INSERT INTO departments (dep_id, name,location,head)
VALUES(7,'quality assurance','C203','arin');

INSERT INTO departments (dep_id, name,location,head)
VALUES(8,'beneits coordinator','C204','clara');
/


INSERT INTO coverage (cov_id, name,percent)
VALUES(1,'University Courses', 80);

INSERT INTO coverage (cov_id, name,percent)
VALUES(2,'Seminars', 60);

INSERT INTO coverage (cov_id, name,percent)
VALUES(3,'Certification Preparation Classes', 75);

INSERT INTO coverage (cov_id, name,percent)
VALUES(4,'Certification', 100);

INSERT INTO coverage (cov_id, name,percent)
VALUES(5,'Technical Training', 90);

INSERT INTO coverage (cov_id, name,percent)
VALUES(6,'Other', 30);
/

SELECT * FROM employees;
SELECT * FROM departments;
SELECT * FROM coverage;
SELECT * FROM reimbursements;
SELECT * FROM REJECTED;

SELECT count(*) FROM employees;
commit;

--SELECT * FROM employees WHERE role = 'supervisor';

--DELETE FROM EMPLOYEES
--WHERE fname = 'test';