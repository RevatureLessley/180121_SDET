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
    regular VARCHAR2(1),
    ben_co VARCHAR2(1),
    dir_sup VARCHAR2(1),
    dep_head VARCHAR2(1),
    CONSTRAINT a_email_fk FOREIGN KEY (email) REFERENCES personal_info(email)
);

CREATE TABLE event_status(
    email VARCHAR2(100) PRIMARY KEY,
    event VARCHAR2(100),
    dep_head_email VARCHAR2(100),
    ben_co_email VARCHAR2(100),
    dir_sup_email VARCHAR2(100),
    justification VARCHAR2(300),
    grade VARCHAR2(2),
    pass_fail VARCHAR2(2),
    approval_email VARCHAR2(100),
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

INSERT INTO reimbursements
VALUES ('RRose@zmail.com', 0, 0, 0, 0, '25.Feb.1996');

SELECT * FROM reimbursements;
SELECT * FROM personal_info;
commit;

INSERT INTO personal_info
VALUES ('RRose@zmail.com', 'rich', 'rose', '1232', 'bloop', '25.Feb.1996');
INSERT INTO personal_info
VALUES ('tto@zmail.com', 'treh', 'rse', '1lld232', 'bldf', '24.Mar.1991');