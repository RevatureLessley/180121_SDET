--Database Cleanup
DROP TABLE accounts CASCADE CONSTRAINTS;
DROP TABLE account_status CASCADE CONSTRAINTS;
DROP TABLE user_role CASCADE CONSTRAINTS;

--Create Database
CREATE TABLE user_role (
    role_id NUMBER(6) PRIMARY KEY,
    role_name VARCHAR2(20)
);
    
CREATE TABLE account_status (
    status_index NUMBER(2) PRIMARY KEY,
    status VARCHAR2(20)
);
CREATE TABLE accounts (
    userid NUMBER(6) PRIMARY KEY,
    username VARCHAR2(20),
    user_password VARCHAR2(20),
    user_role_id NUMBER(2),
    acc_status NUMBER(2),
    balance NUMBER(10,2),
    CONSTRAINT user_role_fk FOREIGN KEY (user_role_id)
        REFERENCES user_role (role_id),
    CONSTRAINT acc_status_fk FOREIGN KEY (acc_status)
        REFERENCES account_status (status_index)
);

INSERT INTO user_role VALUES(1, 'Admin');
INSERT INTO user_role VALUES(2, 'User');
INSERT INTO user_role VALUES(3, 'Banker');

INSERT INTO account_status VALUES (1, 'Active');
INSERT INTO account_status VALUES (2, 'Pending Appoval');
INSERT INTO account_status VALUES (3, 'Frozen');

INSERT INTO accounts VALUES (1, 'admin', '12345', 1, 1, 0.0);
INSERT INTO accounts VALUES (2, 'jiaqi', '11111', 2, 1, 200.0);
INSERT INTO accounts VALUES (3, 'wayne', '00000', 2, 2, 0.0);

CREATE OR REPLACE VIEW All_Accounts AS
SELECT x.USERID ,x.USERNAME ,x.USER_PASSWORD ,y.role_name ,z.status ,x.BALANCE FROM accounts x 
inner join USER_ROLE y on x.USER_ROLE_ID = y.ROLE_ID
inner join account_status z on x.ACC_STATUS = z.STATUS_INDEX
ORDER BY x.USERID;

SELECT * FROM All_Accounts;

commit;





SELECT * FROM accounts;


--UPDATE accounts SET balance = 500 Where userid = 3;
--rollback;