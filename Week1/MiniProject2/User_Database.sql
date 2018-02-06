--Intial DDL
DROP TABLE user_list;
DROP TABLE admin_Table;
DROP TABLE transaction_Records;
CREATE TABLE user_List (
    username VARCHAR2(32) PRIMARY KEY NOT NULL,
    pass VARCHAR2(32) NOT NULL,
    val VARCHAR2(3) NOT NULL,
    bits NUMBER(8) NOT NULL);
    
CREATE TABLE admin_Table(
    Admin_Username VARCHAR(32),
    Admin_Password VARCHAR2(32)
);

CREATE TABLE transaction_Records(
    trans_id NUMBER (10),
    username VARCHAR2 (32),
    amount NUMBER(8),
    type VARCHAR(1)
    );
/


-- INTIAL DML
INSERT INTO user_List ( username,pass, val, bits)
VALUES ('Christian','Diaz','YEP',1000);

INSERT INTO user_List ( username,pass, val, bits)
VALUES ('Jasmin','Tepale','YEP',5000);

INSERT INTO user_List ( username,pass, val, bits)
VALUES ('Bastion','Tepale','NOP',0);


INSERT INTO admin_Table (Admin_Username, Admin_Password)
VALUES ('Admin','SecurePassword1');


INSERT INTO transaction_Records (trans_id, username, amount, type)
VALUES (0,'Christian',500,'D');

INSERT INTO transaction_Records ( trans_id, username, amount, type)
VALUES (1,'Christian',500,'D');

SAVEPOINT;
/

--TEST DQL;

SELECT * FROM user_List;
SELECT * FROM admin_Table;
SELECT * FROM transaction_Records;
/


--TCL
commit;




