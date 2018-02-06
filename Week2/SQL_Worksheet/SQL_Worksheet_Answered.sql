--Task – Select all records from the Employee table.
select * from Employee;


--Task – Select all records from the Employee table where last name is King.
select * from Employee where LASTNAME = 'King';


--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
select * from Employee where FIRSTNAME = 'Andrew' AND REPORTSTO is NULL;


--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM album ORDER BY title DESC;


--Task – Select first name from Customer and sort result set in ascending order by city
SELECT firstname FROM customer ORDER BY city;


--Task – Insert two new records into Genre table
INSERT INTO genre (GENREID,NAME)
VALUES(26,'New Genre');

INSERT INTO genre (GENREID,NAME)
VALUES(27,'Other Genre');


--Task – Insert two new records into Employee table
INSERT INTO employee (EMPLOYEEID,LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE,HIREDATE,ADDRESS,CITY,STATE, COUNTRY, POSTALCODE,PHONE,FAX,EMAIL)
VALUES(9,'new last name','new first name','new title',(null),'01-JAN-01','02-JAN-01','111 Address street','New City','NS','New Country',11111,'+1 (111) 111-1111', '+1 (111) 111 1112','newemail@email.com');

INSERT INTO employee (EMPLOYEEID,LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE,HIREDATE,ADDRESS,CITY,STATE, COUNTRY, POSTALCODE,PHONE,FAX,EMAIL)
VALUES(10,'new last name2','new first name2','new title2',(null),'01-JAN-01','02-JAN-01','111 Address street','New City','NS','New Country',11111,'+1 (111) 111-1111', '+1 (111) 111 1112','newemail2@email.com');


--Task – Insert two new records into Customer table
INSERT  INTO customer
VALUES (50,'New1','Record1', 'Messiah Zero', '111-11 street','New City', 'NC',11111,'+1 (111) 111-1111', '+1 (111) 111 1112' );

INSERT  INTO customer
VALUES (50,'New2','Record2', 'Messiah Zero', '112-12 street','New City', 'NC',11112,'+1 (111) 111-1112', '+1 (111) 111 1114' );


--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE Customer
SET FIRSTNAME = 'Robert'
WHERE FIRSTNAME = 'Aaron';

UPDATE Customer
SET LASTNAME = 'Walter'
WHERE LASTNAME = 'Mitchell';


--Task – Update name of artist in the Artist table “Creedence Clearwater Revival�? to “CCR�?
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';


--Task – Select all invoices with a billing address like “T%�?
SELECT * FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';


--Task – Select all invoices that have a total between 15 and 50
SELECT * FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;


--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM EMPLOYEE
WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';


--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).


DELETE FROM INVOICE
WHERE CUSTOMERID = 32;

DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'; 



SELECT * FROM CUSTOMER;
SELECT * FROM INVOICE;

SELECT * FROM INVOICE
WHERE CUSTOMERID = 32;

SELECT * FROM INVOICELINE;

--Task – Create a function that returns the current time.
SELECT CURRENT_TIMESTAMP FROM DUAL;


--Task – create a function that returns the length of a mediatype from the mediatype table
SELECT length(name) FROM MEDIATYPE;


--Task – Create a function that returns the average total of all invoices
SELECT avg(total) FROM INVOICE;


--Task – Create a function that returns the most expensive track
SELECT max(UNITPRICE) FROM TRACK;


--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
SELECT avg(UNITPRICE) FROM INVOICELINE;


--Task – Create a function that returns all employees who are born after 1968.

--Task – Create a stored procedure that selects the first and last names of all the employees.
--Task – Create a stored procedure that updates the personal information of an employee.
--Task – Create a stored procedure that returns the managers of an employee.
--Task – Create a stored procedure that returns the name and company of a customer.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.

--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId
SELECT customer.FIRSTNAME, customer.LASTNAME, invoice.INVOICEID 
FROM customer
INNER JOIN invoice
ON invoice.CUSTOMERID = customer.CUSTOMERID;


--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.

--Task � Create a right join that joins album and artist specifying artist name and title.
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
--Task – Perform a self-join on the employee table, joining on the reportsto column.

