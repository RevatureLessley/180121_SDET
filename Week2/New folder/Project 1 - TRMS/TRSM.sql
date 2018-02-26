DROP TABLE employees CASCADE CONSTRAINTS;
CREATE TABLE employees (
    emp_id NUMBER(6) PRIMARY KEY,
    firstname VARCHAR2(20) NOT NULL,
    lastname VARCHAR2(20) NOT NULL,
    username VARCHAR2(20) NOT NULL UNIQUE,
    password VARCHAR2(20) NOT NULL,
    email VARCHAR2(30)NOT NULL,
    title VARCHAR2(20),
    address VARCHAR2(20),
    city VARCHAR2(20),
    state VARCHAR2(20),
    tel VARCHAR2(20),
    supervisor_id NUMBER(6)
);

DROP TABLE status CASCADE CONSTRAINTS;
CREATE TABLE status (
    code NUMBER(3) PRIMARY KEY,
    status VARCHAR2(100)
);
INSERT INTO status VALUES (1, 'Pending Supervisor Approval');
INSERT INTO status VALUES (2, 'Pending Department Head Approval');
INSERT INTO status VALUES (3, 'Pending BenCo Approval');
INSERT INTO status VALUES (4, 'Require Presentation/Grade Upload');
INSERT INTO status VALUES (5, 'Pending BenCo Confirmation');
INSERT INTO status VALUES (6, 'Awarded');

DROP TABLE applications CASCADE CONSTRAINTS;
CREATE TABLE applications (
    app_id NUMBER(6) PRIMARY KEY,
    emp_id NUMBER(6) NOT NULL,
    firstname VARCHAR2(20) NOT NULL,
    lastname VARCHAR2(20) NOT NULL,
    email VARCHAR2(30)NOT NULL,
    address VARCHAR2(20),
    city VARCHAR2(20),
    state VARCHAR2(20),
    tel VARCHAR2(20),
    event_date DATE,
    event_location VARCHAR2(50),
    event_type VARCHAR2(50) NOT NULL,
    event_relation VARCHAR2(100),
    event_cost NUMBER(5,2) NOT NULL,
    grading_format VARCHAR2(20),
    description VARCHAR2(300),
    status_code NUMBER(3) NOT NULL,
    sv_decision VARCHAR2(10),
    sv_comment VARCHAR2(100),
    dh_decision VARCHAR2(10),
    dh_comment VARCHAR2(100),
    bc_decision VARCHAR2(10),
    bc_comment VARCHAR2(100),
    submit_date DATE,
    CONSTRAINT fk_emp_id FOREIGN KEY (emp_id) 
        REFERENCES employees (emp_id) ON DELETE CASCADE,
    CONSTRAINT fk_status FOREIGN KEY (status_code)
        REFERENCES status (code) ON DELETE CASCADE
);

INSERT INTO employees (emp_id, firstname, lastname, username, password, title, email, supervisor_id)
VALUES(0, 'admin', 'admin', 'admin', 'admin', 'admin', 'admin@trms.com', NULL);

INSERT INTO employees (emp_id, firstname, lastname, username, password, title, email, supervisor_id)
VALUES(1, 'Waylon', 'Dalton', 'wdalton', '12345', 'Department Head', 'wdalton@trms.com', 1);

INSERT INTO employees (emp_id, firstname, lastname, username, password, title, email, supervisor_id)
VALUES(2, 'Nancy', 'Berry', 'nberry', '12345', 'BenCo', 'nberry@trms.com', NULL);

INSERT INTO employees (emp_id, firstname, lastname, username, password, title, email, supervisor_id)
VALUES(3, 'James', 'Cook', 'jcook', '12345', 'Employee', 'jcook@trms.com', 1);

INSERT INTO employees (emp_id, firstname, lastname, username, password, title, email, supervisor_id)
VALUES(4, 'jiaqi', 'zhang', 'jiaqizhang', '12345', 'Employee', 'jiaqizhang@trms.com', 3);

INSERT INTO employees (emp_id, firstname, lastname, username, password, title, email, supervisor_id)
VALUES(5, 'bob', 'barn', 'bbarn', '12345', 'Employee', 'bbarn@trms.com', 1);

INSERT INTO employees (emp_id, firstname, lastname, username, password, title, email, supervisor_id)
VALUES(6, 'newbob', 'barn', 'nbbarn', '12345', 'Employee', 'bbarn@trms.com', 5);

CREATE OR REPLACE VIEW EmployeeView AS
SELECT a.*, (b.firstname || ' ' || b.lastname)As Supervisor from employees a
left join employees b
on b.emp_id = a.supervisor_id
ORDER BY a.EMP_ID;

CREATE OR REPLACE VIEW ApplicationView AS
SELECT a.*, b.STATUS from applications a
left join STATUS b
on b.CODE = a.STATUS_CODE
ORDER BY a.SUBMIT_DATE DESC;


commit;

rollback;

SELECT * FROM employees WHERE username = 'admin';

SELECT * FROM employees;

SELECT * FROM EMPLOYEEVIEW;

select * from applicationView;

SELECT A.* FROM APPLICATIONS A
LEFT JOIN EMPLOYEEVIEW B
ON B.EMP_ID = A.EMP_ID
WHERE B.SUPERVISOR_ID = 7
ORDER BY A.SUBMIT_DATE DESC;
