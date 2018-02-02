DROP TABLE BANK;
CREATE TABLE bank(
    user_name VARCHAR2(100) PRIMARY KEY,
    password VARCHAR2(100),
    amount NUMBER(9),
    status VARCHAR2(100)
);

INSERT INTO BANK VALUES('harish','kumar',1000,'Approved');
INSERT INTO BANK VALUES('google','amazon',1000,'NotApproved');
INSERT INTO BANK VALUES('robert','bob',1000,'NotApproved');

COMMIT;