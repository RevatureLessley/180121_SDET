DROP TABLE USERS CASCADE CONSTRAINTS;

CREATE TABLE USERS (
    userName varchar2(6) primary key not null,
 	userPassword varchar2(100) not null,
	idNum integer,
	bank_balance float not null,
	isApproved integer default 0 --1 for true, 0 for false   
);
commit;
INSERT INTO USERS (userName, userPassword, bank_balance)
VALUES ('user1', 'mypassword', 100);

UPDATE USERS
SET idNum = 101
WHERE userName = 'user1';