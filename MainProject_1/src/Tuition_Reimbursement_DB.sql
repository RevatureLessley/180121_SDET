CREATE USER DB_ADMIN
IDENTIFIED BY ADMINDB;

GRANT DBA TO DB_ADMIN;

DROP TABLE ReimbursmentRequest;
DROP TABLE Associates;
DROP TABLE Supervisor;
DROP TABLE BenCo;

ALTER TABLE Department DROP CONSTRAINT FK_DEPARTMENT_HEAD;

DROP TABLE DepartmentHead;
DROP TABLE Department;
DROP TABLE Employees;


CREATE TABLE Employees
(
    EmployeeId NUMBER(6) NOT NULL,
    LastName VARCHAR2(20) NOT NULL,
    FirstName VARCHAR2(20) NOT NULL,
    Title VARCHAR2(30),
    Phone VARCHAR2(24),
    Email VARCHAR2(60),
    CONSTRAINT PK_EMPLOYEES PRIMARY KEY  (EmployeeId)
);

CREATE TABLE Associates
(
    AssociateId NUMBER(6) NOT NULL,
    LastName VARCHAR2(20) NOT NULL,
    FirstName VARCHAR2(20) NOT NULL,
    UserName VARCHAR2(20) NOT NULL,
    PW VARCHAR2(20) NOT NULL,
    Title VARCHAR2(30),
    SupervisorId NUMBER(6) NOT NULL,
    Phone VARCHAR2(24),
    Email VARCHAR2(60),
   Balance_Available NUMBER(4),
    -- HAVE IT SO THAT BALANCE AVAILABLE RESETS AFTER A YEAR --
    CONSTRAINT PK_ASSOCIATES PRIMARY KEY  (AssociateId),
    CONSTRAINT FK_EMPLOYEE_A FOREIGN KEY (AssociateId) 
    REFERENCES Employees (EmployeeId)  ON DELETE CASCADE
);

CREATE TABLE Supervisor
(
    SupervisorId NUMBER(6) NOT NULL,
    LastName VARCHAR2(20) NOT NULL,
    FirstName VARCHAR2(20) NOT NULL,
    Title VARCHAR2(30),
    DepartmentId NUMBER(3),
    Phone VARCHAR2(24),
    Email VARCHAR2(60),
    CONSTRAINT PK_SUPERVISOR PRIMARY KEY  (SupervisorId),
    CONSTRAINT FK_EMPLOYEE_S FOREIGN KEY (SupervisorId) 
    REFERENCES Employees (EmployeeId)  ON DELETE CASCADE
    
);

CREATE TABLE Department
(
    DepartmentId NUMBER(3) NOT NULL,
    Department_Name VARCHAR2(20) NOT NULL,
    Department_Head_Id NUMBER NOT NULL,
    CONSTRAINT PK_DEPARTMENT PRIMARY KEY  (DepartmentId)
    
);


CREATE TABLE DepartmentHead
(
    Department_Head_Id NUMBER(6) NOT NULL,
    LastName VARCHAR2(20) NOT NULL,
    FirstName VARCHAR2(20) NOT NULL,
    Title VARCHAR2(30),
    DepartmentId NUMBER(3),
    Phone VARCHAR2(24),
    Email VARCHAR2(60),
    CONSTRAINT PK_DEPARTMENT_HEAD PRIMARY KEY  (Department_Head_Id),
    CONSTRAINT FK_EMPLOYEE_D FOREIGN KEY (Department_Head_Id) 
    REFERENCES Employees (EmployeeId)  ON DELETE CASCADE,
    CONSTRAINT FK_DEPARTMENT FOREIGN KEY (DepartmentId) 
    REFERENCES Department (DepartmentId)  ON DELETE CASCADE
);

CREATE TABLE BenCo
(
    BenCo_Id NUMBER(6) NOT NULL,
    LastName VARCHAR2(20) NOT NULL,
    FirstName VARCHAR2(20) NOT NULL,
    Title VARCHAR2(30),
    Phone VARCHAR2(24),
    Email VARCHAR2(60),
    CONSTRAINT PK_BENCO PRIMARY KEY  (BenCo_Id),
    CONSTRAINT FK_EMPLOYEE_BC FOREIGN KEY (BenCo_Id) 
    REFERENCES Employees (EmployeeId)  ON DELETE CASCADE
);


CREATE TABLE ReimbursmentRequest
(
    RequestId NUMBER(6) NOT NULL,
    EmployeeId NUMBER(6) NOT NULL,
    LastName VARCHAR2(20) NOT NULL,
    FirstName VARCHAR2(20) NOT NULL,
    Title VARCHAR2(30),
    Amount_Requested NUMBER(4),
    Balance_Available NUMBER(4),
    Pending_Reimbursment NUMBER(4),
    Supervisor_Approval NUMBER(1),
    Department_Head_Approval NUMBER(1),
    BenCo_Approval NUMBER(1),
    CONSTRAINT PK_REQUEST PRIMARY KEY  (RequestId),
    CONSTRAINT FK_ASSOICATE FOREIGN KEY (EmployeeId) 
    REFERENCES Associates (AssociateId)  ON DELETE CASCADE
);

ALTER TABLE Department ADD CONSTRAINT FK_DEPARTMENT_HEAD FOREIGN KEY (Department_Head_Id) 
      REFERENCES DepartmentHead (Department_Head_Id)  ON DELETE CASCADE;
      
ALTER TABLE Supervisor ADD CONSTRAINT FK_DEPARTMENT_S FOREIGN KEY (DepartmentId) 
      REFERENCES Department (DepartmentId)  ON DELETE CASCADE;
    
ALTER TABLE Associates ADD CONSTRAINT FK_SUPERVISOR FOREIGN KEY (SupervisorId) 
    REFERENCES Supervisor (SupervisorId)  ON DELETE CASCADE;  
      
SELECT * FROM Employees;
SELECT * FROM Associates;
SELECT * FROM Department;
SELECT * FROM Supervisor;
SELECT * FROM DepartmentHead;
SELECT * FROM Department;
SELECT * FROM BenCo;
SELECT * FROM ReimbursmentRequest;

