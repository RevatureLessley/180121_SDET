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
    sup_id NUMBER(6),
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


INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (1,'phoenix','nakamura','chh','Blazing123', 'chh@outlook.com','head','production',0,1000);

INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (2,'martha','clairevoynce','123martha','Martha123','marthalovesgod@dogmail.com','head','marketing',0,1000);

INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (3,'armin','bradshow','CoolSalute334','BlondeH4','ihatetitans@dogmail.com','head','techincal',0,1000);

INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (4,'jennifer','poras','okami','ilovealex','porasokami@dogmail.com','head','art',0,1000);

INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (5,'lucas','jaeger','liger333','BrastleTiger423','hero@dogmail.com','head','game design',0,1000);

INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (6,'camilo','arrauth','kcam','YankeesRock','deathcam320@dogmail.com','head','business',0,1000);

INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (7,'arin','hanson','lovemegaman','JumpAndShoot','egoraptor@catmail.com','head','quality assurance',0,1000);


INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (8,'christian','diaz','cdiaz8369','Araceli8','cdiaz@dogmail.com','employee','game design',9,1000);

INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (9,'jasmin','tepale','funsizejas','live4free','jtepale@dogmail.com','supervisor','game design',5,1000);

INSERT INTO employees (emp_id, fname, lname,username,password, email, role, department, sup_id, amount)
VALUES (10,'clara','martinez','clara1953','Aquamarina1','clara1953@dogmail','benco','benefits',0,1000);
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
VALUES(8,'beneits','C204','clara');
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