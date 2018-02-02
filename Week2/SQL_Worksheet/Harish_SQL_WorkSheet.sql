--2.0 SQL Queries

-- Select all records from the Employee table.
SELECT * FROM EMPLOYEE; 

--Select all records from the Employee table where last name is King.
SELECT * FROM EMPLOYEE 
WHERE LASTNAME = 'King'; 

--Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM EMPLOYEE 
WHERE FIRSTNAME = 'Andrew' OR REPORTSTO IS NULL; 

--Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM ALBUM 
ORDER BY TITLE DESC; 

--Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME FROM CUSTOMER 
ORDER BY CITY;

--Insert two new records into Genre table
INSERT INTO GENRE VALUES (26,'Documentry');
INSERT INTO GENRE VALUES (27,'International');

--Insert two new records into Employee table
INSERT INTO EMPLOYEE 
VALUES (9,	'Chandramohan', 'Harish', 'IT Staff', 6, '02-OCT-91', '04-MAR-04', '923 7 ST NW', 'Lethbridge', 
'AB', 'Canada', 'T1H 1Y8',	'+1 (403) 467-3351', '+1 (403) 467-8772', 'laura@chinookcorp.com');
INSERT INTO EMPLOYEE 
VALUES (10, 'Richard', 'Peter', 'IT Staff', 6, '01-OCT-95', '05-MAR-05', '913 7 ST NW', 'Lethbridge', 
'AB', 'Canada', 'T1H 1Y8',	'+1 (403) 467-3351', '+1 (403) 467-8772', 'peter@chinookcorp.com');

--Insert two new records into Customer table
INSERT INTO CUSTOMER 
VALUES (60, 'Harish', 'Chandramohan', NULL, '3 Raj Bhavan Road', 'Bangalore', NULL, 'India', '560001',
'+91 080 22289999', NULL, 'harish@yahoo.in', 4);
INSERT INTO CUSTOMER 
VALUES (61, 'Richard', 'Peter', NULL, '34 Lahoore Road', 'Bangalore', NULL, 'India', '561111',
'+91 080 34289999', NULL, 'Peter@yahoo.in', 5);

--Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER 
SET FIRSTNAME = 'Robert' 
WHERE FIRSTNAME = 'Aaron';
UPDATE CUSTOMER 
SET LASTNAME = 'Walter' 
WHERE LASTNAME = 'Mitchell';

--Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE ARTIST 
SET NAME = 'CCR' 
WHERE NAME = 'Creedence Clearwater Revival';

--Select all invoices with a billing address like “T%”
SELECT * FROM INVOICE 
WHERE BILLINGADDRESS LIKE 'T%';

--Select all invoices that have a total between 15 and 50
SELECT * FROM INVOICE 
WHERE TOTAL BETWEEN 15 AND 50;

--Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM EMPLOYEE 
WHERE HIREDATE BETWEEN TO_DATE('01/06/2003', 'DD/MM/YYYY') AND TO_DATE('01/03/2004', 'DD/MM/YYYY');

--Delete a record in Customer table where the name is Robert Walter 
--(There may be constraints that rely on this, find out how to resolve them).
ALTER TABLE INVOICELINE 
DROP CONSTRAINT FK_INVOICELINEINVOICEID;
ALTER TABLE INVOICELINE 
ADD CONSTRAINT FK_INVOICELINEINVOICEID FOREIGN KEY (INVOICEID) REFERENCES INVOICE(INVOICEID) ON DELETE CASCADE;
ALTER TABLE INVOICE 
DROP CONSTRAINT FK_INVOICECUSTOMERID;
ALTER TABLE INVOICE 
ADD CONSTRAINT FK_INVOICECUSTOMERID FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID) ON DELETE CASCADE;
DELETE FROM CUSTOMER 
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

--4.0 Stored Procedures

--Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE GET_EMPLOYEE_NAME 
IS
BEGIN
dbms_output.put_line('Hello World');
--  FOR i IN (SELECT FIRSTNAME, LASTNAME
--		  FROM EMPLOYEE) LOOP
--            dbms_output.put_line(i.FIRSTNAME || ' ' ||i.LASTNAME);
--	END LOOP;
END GET_EMPLOYEE_NAME;
/

BEGIN
    dbms_output.put_line('Hello World');
END;
/