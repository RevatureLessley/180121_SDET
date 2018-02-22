DROP TABLE personal_info;
DROP TABLE account_info;
DROP TABLE reimbursements;
DROP TABLE events;

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
    justification CLOB,
    grade VARCHAR2(2),
    pass_fail VARCHAR2(2),
    start_date VARCHAR2(20),
    end_date VARCHAR2(20),
    CONSTRAINT e_email_fk FOREIGN KEY (email) REFERENCES personal_info(email)
);

CREATE TABLE reimbursements(
    email VARCHAR2(100) PRIMARY KEY,
    available BINARY_DOUBLE,
    pending BINARY_DOUBLE,
    awarded BINARY_DOUBLE,
    total BINARY_DOUBLE,
    last_reimb VARCHAR2(20),
    CONSTRAINT r_email_fk FOREIGN KEY (email) REFERENCES personal_info(email)
);

INSERT INTO reimbursements
VALUES ('RRose@zmail.com', 0, 0, 0, 0, '25.Feb.1996');

SELECT * FROM reimbursements;
SELECT * FROM personal_info;
SELECT * FROM account_info;
SELECT * FROM events;
commit;

INSERT INTO personal_info
VALUES ('RRose@zmail.com', 'rich', 'rose', '1232', 'bloop', '25.Feb.1996');
INSERT INTO personal_info
VALUES ('tto@zmail.com', 'treh', 'rse', '1lld232', 'bldf', '24.Mar.1991');
INSERT INTO account_info
VALUES ('RRose@zmail.com', 'rich', 'rose', 0, 0, 0);
INSERT INTO account_info
VALUES ('tto@zmail.com', 'treh', 'rse', 0, 0, 0);
INSERT INTO events
VALUES ('RRose@zmail.com', 'kjhl', 'kkjkj', 'jhg', 'bloop', '25.Feb.1996', 'dff', 'f', 'd', 'dfgtres', 'fdswqa');
INSERT INTO events
VALUES ('tto@zmail.com', 'kjhl', 'kkjkj', 'jhg', 'bloop', '25.Feb.1996', 'dff', 's', 'd', 'dfgtres', 'fdswqa');
INSERT INTO reimbursements
VALUES ('RRose@zmail.com', 3434, 4343, 3443, 3334, '25.Feb.1996');
INSERT INTO reimbursements
VALUES ('tto@zmail.com', 3424, 2344, 2343, 343, '24.Mar.1991');