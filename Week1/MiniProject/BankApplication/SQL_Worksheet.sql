/*******************************************************************************
   Name : Ernst Nathaniel Blanchard
   Description: SQL lab Sql worksheet.
   Date: 02/ 1/ 2018
********************************************************************************/

/* ------------------------------- 2.1 Select ------------------------------- */

/*  select all records from employee table */
SELECT * FROM EMPLOYEE;

/*  select all records from employee table whose last name is king */
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';

/*  select all records from employee table whose first name is Andrew and
 reportsto is null */
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO = NULL ;

/* ------------------------------- 2.2 Order By ------------------------------- */

/* Select all albums in Album table and sort result set in descending order by title */
SELECT * FROM ALBUM ORDER BY (TITLE) DESC;

/* Select first name from Customer and sort result set in ascending order by city */
SELECT FIRSTNAME, CITY FROM CUSTOMER ORDER BY (CITY) ASC;

/*  -------------------------------2.3 Insert Into ------------------------------- */

/* Insert two new records into Genre table */
INSERT INTO GENRE (GENREID, NAME) VALUES (26, 'Rock N Roll');
INSERT INTO GENRE (GENREID, NAME) VALUES (27, 'Hip Hop');
SELECT * FROM GENRE;

/* ------------------------------- 2.3 Insert Into ------------------------------- */
/* Insert two new records into Employee table */
SELECT * FROM EMPLOYEE;
INSERT INTO EMPLOYEE(EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES
(9, 'Jennis', 'Letta', 'Factory Worker', null, TO_DATE('1853-8-29 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('1123-8-29 00:00:00','yyyy-mm-dd hh24:mi:ss'), '734 east 93rd street',
'brooklyn', 'New York', 'USA', '12345','+1 567 876 3214', '+1 654 986 5643', 'jennis@mail.com');
INSERT INTO EMPLOYEE(EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES
(10, 'Benji', 'Benny', 'CTO', null, TO_DATE('1873-8-29 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('1873-8-29 00:00:00','yyyy-mm-dd hh24:mi:ss'), '837 east 93rd street',
'queens', 'New York', 'USA', '12345','+1 567 876 3214', '+1 654 986 5643', 'kj@mail.com');

/* Insert two new records into Customer table */
SELECT * FROM CUSTOMER;
INSERT INTO CUSTOMER(CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) VALUES
(60, 'Sam', 'Winchester', 'Hunters', 'homeless', 'Brooklyn', 'New York', 'United States', '12345', '+1 234 598 5643', '+1 485 674 2534', 'sam@mail.com',3);
INSERT INTO CUSTOMER(CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) VALUES
(61, 'Dean', 'Winchester', 'Hunters', 'homeless', 'Brooklyn', 'New York', 'United States', '54321', '+1 234 658 3456', '+1 485 234 6352', 'dean@mail.com',5);

/* ------------------------------- 2.4 Update ------------------------------- */

/* Update Aaron Mitchell in Customer table to Robert Walter */
UPDATE CUSTOMER SET CUSTOMER.FIRSTNAME = 'Robert', CUSTOMER.LASTNAME = 'Walter'
WHERE FIRSTNAME='Aaron' AND LASTNAME='Mitchell';
/* Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR” */
UPDATE ARTIST SET ARTIST.NAME = 'CCR'
WHERE ARTIST.NAME= 'Creedence Clearwater Revival';

/* ------------------------------- 2.5 Like ------------------------------- */
/* Select all invoices with a billing address like “T%” */
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

/* ------------------------------- 2.6 Between ------------------------------- */

/* Select all invoices that have a total between 15 and 50 */
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;

/* Select all employees hired between 1st of June 2003 and 1st of March 2004 */
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN TO_DATE('2003-1-1 00:00:00','yyyy-mm-dd hh24:mi:ss') AND TO_DATE('2004-3-1 00:00:00','yyyy-mm-dd hh24:mi:ss');

/* ------------------------------- 2.7 DELETE ------------------------------- */
/* Task – Delete a record in Customer table where the name is Robert Walter
(There may be constraints that rely on this, find out how to resolve them).
*/
DELETE FROM CUSTOMER WHERE FIRSTNAME='Robert' AND LASTNAME='Walter';


/* ------------------------------- 3.1 System Defined Functions ------------------------------- */
/* Create a function that returns the current time. */


/* Create a function that returns the length of a
media type from the media type table */


/* ------------------------------- 3.2 System Defined Aggregate Functions ------------------------------- */
/* Create a function that returns the average total of all invoices */
SELECT max(INVOICE.TOTAL) FROM INVOICE;

/* Create a function that returns the most expensive track */
SELECT * FROM MEDIATYPE;
SELECT max(MEDIATYPE.MEDIATYPEID) FROM MEDIATYPE;

/* ------------------------------- 3.3 User Defined Table Valued Functions ------------------------------- */
/* Create a function that returns all employees who are born after 1968. */
SELECT * FROM EMPLOYEE WHERE BIRTHDATE = TO_DATE('1968','yyyy');

/* ------------------------------- 3.4 User Defined Scalar Functions ------------------------------- */
/* Create a function that returns the average price of invoice
line items in the invoice line table */

/* ------------------------------- 7.0 Joins -------------------------------*/
/* Create an inner join that joins customers
and orders and specifies the name of the customer and the invoiceId */
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID
FROM CUSTOMER
INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

/* Create an outer join that joins the customer and invoice table,
specifying the CustomerId, firstname, lastname, invoiceId, and total */
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, CUSTOMER.CustomerId ,INVOICE.INVOICEID, INVOICE.total
FROM CUSTOMER
FULL OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;