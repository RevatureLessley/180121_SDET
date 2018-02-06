CREATE USER MINIPROJECT
IDENTIFIED BY MINIPROJECT;

GRANT DBA TO MINIPROJECT;

DROP TABLE BankAccount CASCADE CONSTRAINTS;
DROP TABLE AdminAccount CASCADE CONSTRAINTS;
DROP TABLE Transactions CASCADE CONSTRAINTS;

CREATE TABLE BankAccount (
    Account_ID NUMBER(6),
    FirstName VARCHAR2(20),
    LastName VARCHAR2(20),
    UserName VARCHAR2(20) UNIQUE,
    PW VARCHAR2(20),
    Balance NUMBER(10),
    AdminApproval NUMBER(20),
    CONSTRAINT account_id_pk PRIMARY KEY (Account_ID)
);

CREATE TABLE AdminAccount (
    Admin_ID NUMBER (6),
    Admin_Name VARCHAR2 (20),
    UserName VARCHAR2(20) UNIQUE,
    PW VARCHAR2(20),
    CONSTRAINT admin_id_pk PRIMARY KEY (Admin_ID)
);

CREATE TABLE Transactions (
    Transaction_ID NUMBER(6),
    Account_ID NUMBER(6),
    UserName VARCHAR2(20),
    Transaction_Type VARCHAR2(20),
    Amount NUMBER(10),
     CONSTRAINT transaction_id_pk PRIMARY KEY (Transaction_ID),
     CONSTRAINT account_id_fk FOREIGN KEY (Account_ID) 
        REFERENCES BankAccount (Account_ID)  ON DELETE CASCADE,
     CONSTRAINT user_name_fk FOREIGN KEY (UserName) 
        REFERENCES BankAccount (UserName) ON DELETE CASCADE
);

DROP SEQUENCE bank_acct_seq;
CREATE SEQUENCE bank_acct_seq
    start with 100
    increment by 1;

CREATE OR REPLACE TRIGGER bank_acct_id_seq 
BEFORE INSERT ON BankAccount
FOR EACH ROW
BEGIN 
    IF :new.Account_ID IS NULL THEN
    SELECT bank_acct_seq.nextval INTO :new.Account_ID from dual;
    END IF;
END;    
/

CREATE OR REPLACE PROCEDURE insertIntoAdmin(admin_id IN NUMBER, admin_name IN varchar2, username IN varchar2 , pw IN varchar2)
IS
BEGIN
    INSERT INTO AdminAccount (Admin_ID, Admin_Name, UserName, PW) 
    VALUES(admin_id, admin_name, username, pw);
    commit;
END;
/

call insertIntoAdmin(87215, 'Bobbert', 'ADMIN', 'ADMIN12345');

DROP SEQUENCE transaction_seq;
CREATE SEQUENCE transaction_seq
    start with 1111
    increment by 1;


CREATE OR REPLACE TRIGGER transaction_id_seq 
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN 
    IF :new.Transaction_ID IS NULL THEN
    SELECT transaction_seq.nextval INTO :new.Transaction_ID from dual;
    END IF;
END;    
/


select * from BankAccount;
select * from AdminAccount;
select * from Transactions;

commit;