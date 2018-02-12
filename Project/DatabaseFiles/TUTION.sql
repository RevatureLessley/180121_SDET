DROP TABLE TEST;

CREATE TABLE EMPLOYEE (
    E_ID NUMBER(10) PRIMARY KEY,
    E_FIRST_NAME VARCHAR2(100) NOT NULL,
    E_LAST_NAME VARCHAR2(100) NOT NULL,
    E_TITLE VARCHAR2(100)NOT NULL,
    E_SUPERVISIOR NUMBER(10),
    E_EMAIL VARCHAR2(100)NOT NULL  
);

DROP TABLE LOGIN;
CREATE TABLE LOGIN (
    E_ID NUMBER (10),
    E_USERNAME VARCHAR2(100) PRIMARY KEY,
    E_PASSWORD VARCHAR2(100),
    CONSTRAINT E_ID_FK FOREIGN KEY (E_ID) REFERENCES EMPLOYEE(E_ID) ON DELETE CASCADE
);

--Sequence to Generate E_ID 
DROP SEQUENCE E_SEQ;
CREATE SEQUENCE E_SEQ
    start with 1000
    increment by 1;
    
--Trigger to add E_ID before Insertion    
CREATE OR REPLACE TRIGGER E_SEQ_TRIGGER 
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN 
    IF :NEW.E_ID IS NULL THEN
    SELECT E_SEQ.NEXTVAL INTO :NEW.E_ID FROM DUAL;
    END IF;
END;    
/

INSERT INTO EMPLOYEE(E_FIRST_NAME, E_LAST_NAME, E_TITLE, E_SUPERVISIOR, E_EMAIL) 
VALUES('OMOWUMI','ADEMOLA', 'DEPT HEAD', (SELECT E_ID FROM EMPLOYEE WHERE E_FIRST_NAME = 'NONE' AND E_LAST_NAME = 'NONE')
,'omowumi.l.ademola@gmail.com');

SELECT E_FIRST_NAME, E_LAST_NAME FROM EMPLOYEE 
WHERE E_ID = (SELECT E_SUPERVISIOR FROM EMPLOYEE WHERE E_FIRST_NAME = 'OMOWUMI' AND E_LAST_NAME = 'ADEMOLA');

INSERT INTO LOGIN 
VALUES((SELECT E_ID FROM EMPLOYEE WHERE E_FIRST_NAME = 'OMOWUMI' AND E_LAST_NAME = 'ADEMOLA'),'OMOWUMI','PASSWORD');

SELECT E_ID FROM EMPLOYEE WHERE E_FIRST_NAME = 'NONE' AND E_LAST_NAME = 'NONE';

--Procedure to insert emlployee
CREATE OR REPLACE PROCEDURE INSERT_EMPLOYEE(FIRSTNAME IN VARCHAR2, LASTNAME IN VARCHAR2, USERNAME IN VARCHAR2,PASSWORD IN VARCHAR2,
TITLE IN VARCHAR2, S_FIRSTNAME IN VARCHAR2, S_LASTNAME IN VARCHAR2, EMAIL IN VARCHAR2)
IS 
BEGIN
--fill employee table
INSERT INTO EMPLOYEE(E_FIRST_NAME, E_LAST_NAME, E_TITLE, E_SUPERVISIOR, E_EMAIL) 
VALUES(FIRSTNAME,LASTNAME, TITLE, 
(SELECT E_ID FROM EMPLOYEE WHERE E_FIRST_NAME = S_FIRSTNAME AND E_LAST_NAME = S_LASTNAME)
,EMAIL);
--fill login table
INSERT INTO LOGIN 
VALUES((SELECT E_ID FROM EMPLOYEE WHERE E_FIRST_NAME = FIRSTNAME AND E_LAST_NAME = LASTNAME),
USERNAME,PASSWORD);
END;
/






ALTER TABLE employee
ADD COLUMN e_supervisior NUMBER();

create table supervisior (
    e_id number(10),
    e_supervisior varchar2(100)

);