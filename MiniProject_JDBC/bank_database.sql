--DROP TABLE account_info;
--DROP TABLE user_info CASCADE CONSTRAINTS;
--DROP TABLE balance_info;

CREATE TABLE user_info( --Parent table.
    u_email VARCHAR2(100) PRIMARY KEY,
    u_first_name VARCHAR2(100),
    u_last_name VARCHAR2(100)
);

CREATE TABLE balance_info( --child table
    b_email VARCHAR2(100) PRIMARY KEY,
    b_checkings BINARY_DOUBLE,
    b_savings BINARY_DOUBLE,
    CONSTRAINT b_email_fk FOREIGN KEY (b_email) REFERENCES user_info(u_email)
);

CREATE TABLE account_info( --child table
    a_email VARCHAR2(100) PRIMARY KEY,
    a_username VARCHAR2(100),
    a_password VARCHAR2(20),
    a_is_admin NUMBER(1),
    a_is_active NUMBER(1),
    a_is_closed NUMBER(1),
    CONSTRAINT a_email_fk FOREIGN KEY (a_email) REFERENCES user_info(u_email)
);

--admin 
INSERT INTO user_info
VALUES ('RRose@zmail.com', 'Richard', 'Rosario');

INSERT INTO account_info
VALUES ('RRose@zmail.com', 'RRoseAdmin', 'adminpassword', 1, 1, 1);

INSERT INTO balance_info
VALUES ('RRose@zmail.com', 100, 100);

COMMIT;


