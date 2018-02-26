DROP TABLE account_info;
DROP TABLE reimbursements;
DROP TABLE events;
DROP TABLE personal_info;

CREATE TABLE personal_info( --parent table
    email VARCHAR2(100) PRIMARY KEY,
    first_name VARCHAR2(100),
    last_name VARCHAR2(100),
    address VARCHAR2(100),
    join_date VARCHAR2(20)
);

CREATE TABLE account_info(
    email VARCHAR2(100) PRIMARY KEY,
    pw VARCHAR2(100),
    ben_co VARCHAR2(1),
    dir_sup VARCHAR2(1),
    dep_head VARCHAR2(1),
    approved VARCHAR2(1),
    CONSTRAINT a_email_fk FOREIGN KEY (email) REFERENCES personal_info(email)
);

CREATE TABLE events(
    email VARCHAR2(100) PRIMARY KEY,
    event VARCHAR2(100),
    dep_head_email VARCHAR2(100),
    ben_co_email VARCHAR2(100),
    dir_sup_email VARCHAR2(100),
    approval_email VARCHAR2(100),
    justification VARCHAR(300),
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

SELECT email FROM personal_info;
INSERT INTO personal_info
VALUES ('RRose@zmail.com', 'Richard', 'Rosario', '123 Green St', '25.Feb.2017');
INSERT INTO personal_info
VALUES ('GGin@zmail.com', 'Gary', 'Gin', '13 Grn St', '25.Feb.2017');
INSERT INTO personal_info
VALUES ('EFort@zmail.com', 'Erica', 'Fort', '244 Brown Rd', '24.Jul.2017');
INSERT INTO personal_info
VALUES ('BGrimes@zmail.com', 'Ben', 'Grimes', '12 Ben St', '25.Aug.2017');
INSERT INTO personal_info
VALUES ('JSmith@zmail.com', 'John', 'Smith', '244 Brow Rd', '24.Jul.2017');
INSERT INTO account_info
VALUES ('RRose@zmail.com', 'password', 1, 0, 0, 1);    --Benefits Coordinator
INSERT INTO account_info
VALUES ('EFort@zmail.com', 'password', 0, 0, 1, 1);
INSERT INTO account_info
VALUES ('GGin@zmail.com', 'password', 0, 1, 0, 1);
INSERT INTO account_info
VALUES ('BGrimes@zmail.com', 'password', 0, 0, 1, 1);   
INSERT INTO account_info
VALUES ('JSmith@zmail.com', 'password', 0, 0, 1, 1);
INSERT INTO events
VALUES ('RRose@zmail.com', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA');
INSERT INTO events
VALUES ('GGin@zmail.com', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA');
INSERT INTO events
VALUES ('EFort@zmail.com', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA');
INSERT INTO events
VALUES ('BGrimes@zmail.com', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA');
INSERT INTO events
VALUES ('JSmith@zmail.com', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA');
INSERT INTO reimbursements
VALUES ('RRose@zmail.com', 0, 0, 0, 0, 'NA');
INSERT INTO reimbursements
VALUES ('GGin@zmail.com', 0, 0, 0, 0, 'NA');
INSERT INTO reimbursements
VALUES ('EFort@zmail.com', 0, 0, 0, 0, 'NA');
INSERT INTO reimbursements
VALUES ('BGrimes@zmail.com', 0, 0, 0, 0, 'NA');
INSERT INTO reimbursements
VALUES ('JSmith@zmail.com', 0, 0, 0, 0, 'NA');

SELECT * FROM reimbursements;
SELECT * FROM personal_info;
SELECT * FROM account_info;
SELECT * FROM events;
commit;