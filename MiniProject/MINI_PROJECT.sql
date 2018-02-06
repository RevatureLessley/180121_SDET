DROP TABLE ACCOUNT_TRANS;
DROP TABLE ADMIN;
DROP TABLE CREDIT_CARD;
DROP TABLE CREDIT_CARD_ACCOUNT;
DROP TABLE useraccount;
DROP TABLE TRANSACTIONS;
DROP TABLE USEeRACCOUNT_TEMP;

CREATE TABLE account_trans (
    trans_num     NUMBER(5) NOT NULL,
    trans_date    DATE,
    amount        NUMBER(8,2),
    ref2          VARCHAR2(16 CHAR),
    description   VARCHAR2(200 CHAR)
);

CREATE TABLE admin (
    admin_id     VARCHAR2(5 CHAR) NOT NULL,
    ssn          VARCHAR2(9 CHAR) NOT NULL,
    first_name   VARCHAR2(50 CHAR),
    last_name    VARCHAR2(50 CHAR),
    email        VARCHAR2(100 CHAR),
    phone        VARCHAR2(11 CHAR),
    username     VARCHAR2(100 CHAR) NOT NULL,
    password     VARCHAR2(100 CHAR) NOT NULL,
    address      VARCHAR2(100),
    state        CHAR(2),
    city         VARCHAR2(50 CHAR),
    zipcode      VARCHAR2(7 CHAR)
);

ALTER TABLE admin ADD CONSTRAINT admin_pk PRIMARY KEY ( admin_id );

CREATE TABLE credit_card (
    card_ref    NUMBER(2) NOT NULL,
    card_name   VARCHAR2(50 CHAR),
    card_type   VARCHAR2(50 CHAR)
);

ALTER TABLE credit_card ADD CONSTRAINT credit_card_pk PRIMARY KEY ( card_ref );

CREATE TABLE credit_card_account (
    card_num                  VARCHAR2(16 CHAR) NOT NULL,
    current_balance           NUMBER(8,6),
    avaible_balance           NUMBER(8,2),
    pay_due_date              DATE,
    statement_balace          NUMBER(8,2),
    minimum_due               NUMBER(8,2),
    credit_limit              NUMBER(8,2),
    credit_card_card_ref      NUMBER(2) NOT NULL,
    useraccount_account_num   VARCHAR2(16 CHAR) NOT NULL
);

ALTER TABLE credit_card_account ADD CONSTRAINT credit_card_account_pk PRIMARY KEY ( card_num,
credit_card_card_ref );

CREATE TABLE useeraccount_temp (
    username         VARCHAR2(50 CHAR) NOT NULL,
    password         VARCHAR2(50 CHAR) NOT NULL,
    ssn              VARCHAR2(9 CHAR) NOT NULL,
    first_name       VARCHAR2(50 CHAR),
    last_name        VARCHAR2(50 CHAR),
    email            VARCHAR2(50 CHAR),
    phone            VARCHAR2(11 CHAR),
    address          VARCHAR2(100 CHAR),
    state            CHAR(2 CHAR),
    city             VARCHAR2(50 CHAR),
    zipcode          VARCHAR2(7 CHAR),
    id_num           NUMBER(5),
    admin_admin_id   VARCHAR2(5 CHAR) NOT NULL
);

ALTER TABLE useeraccount_temp ADD CONSTRAINT useeraccount_temp_pk PRIMARY KEY ( username );

CREATE TABLE useraccount (
    account_num      VARCHAR2(16 CHAR) NOT NULL,
    ssn              VARCHAR2(9 CHAR) NOT NULL,
    first_name       VARCHAR2(50 CHAR),
    last_name        VARCHAR2(50 CHAR),
    username         VARCHAR2(50 CHAR),
    password         VARCHAR2(50 CHAR),
    email            VARCHAR2(100 CHAR),
    phone            VARCHAR2(11 CHAR),
    total_checking   NUMBER(8,2),
    total_saving     NUMBER(8,2),
    address          VARCHAR2(100),
    state            CHAR(2),
    city             VARCHAR2(50 CHAR),
    zipcode          VARCHAR2(7 CHAR)
);

ALTER TABLE useraccount ADD CONSTRAINT useraccount_pk PRIMARY KEY ( account_num );

ALTER TABLE account_trans
    ADD CONSTRAINT a_t_ua_fk FOREIGN KEY ( ref2 )
        REFERENCES useraccount ( account_num )
            ON DELETE CASCADE;

 
ALTER TABLE credit_card_account
    ADD CONSTRAINT caccount_card_fk FOREIGN KEY ( credit_cref )
        REFERENCES credit_card ( card_ref );

 
ALTER TABLE credit_card_account
    ADD CONSTRAINT c_a_ut_fk FOREIGN KEY ( u_a_num )
        REFERENCES useraccount ( account_num );

ALTER TABLE useeraccount_temp
    ADD CONSTRAINT us_fk FOREIGN KEY ( admin_admin_id )
        REFERENCES admin ( admin_id );


----------SEQUENCEs----------------------------------------------------------------------
        
DROP SEQUENCE TRANS_SEQ;
CREATE SEQUENCE TRANS_SEQ
    start with 1
    increment by 1;

-- A TRIGGER TO INCREMENT THE trans-num

CREATE OR REPLACE TRIGGER TRANS_seq_trigger 
BEFORE INSERT ON account_trans
FOR EACH ROW
BEGIN 
    SELECT TRANS_seq.nextval INTO :new.TRANS_num from dual;
    
END;

--a trigger that make sure after an account is approved to delete the record from temperary table.
CREATE OR REPLACE TRIGGER DELETETEMPACCOUNT
AFTER INSERT ON USERACCOUNT
BEGIN
DELETE FROM USERACCOUNT_TEMP
WHERE USERNAME IN (SELECT USERACCOUNT_TEMP.USERNAME 
                   FROM USERACCOUNT, USERACCOUNT_TEMP
                   WHERE USERACCOUNT.USERNAME=USERACCOUNT_TEMP.USERNAME);
END;
--Procedure  to track all the states and the behaviors of the userAccount object:  update the the balance of the account transactions
CREATE OR REPLACE PROCEDURE transations_ACCOUNT(A_NUM IN VARCHAR2, Amount IN numeric, descr IN VARCHAR2, balance in numeric) IS
d date;
BEGIN
select SYSDATE into d from dual;
INSERT INTO ACCOUNT_TRANS (REF2, AMOUNT, TRANS_DATE, DESCRIPTION) 
values (A_NUM , AMOUNT , d , descr);
UPDATE USERACCOUNT SET TOTAL_CHECKING=BALANCE
WHERE ACCOUNT_NUM=A_NUM;
end;
 

--call transations_account('10000',100,'direct deposit',700);


