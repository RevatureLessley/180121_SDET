--Task – Select all records from the Employee table.
SELECT * FROM EMPLOYEE; 
--Task – Select all records from the Employee table where last name is King.
SELECT * FROM EMPLOYEE
WHERE LASTNAME='King';
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
select * from employee
where firstname='Andrew' and REPORTSTO is null;

--    2.2 ORDER BY
--    Task – Select all albums in Album table and sort result set in descending order by title.
select * from album
order by title desc;
--    Task – Select first name from Customer and sort result set in ascending order by city
select firstname 
from 
customer
order by city desc;
--    2.3 INSERT INTO
--    Task – Insert two new records into Genre table
insert into genre 
values(26,'kabyle');
insert into genre
values (27,'chaabi' );
--    Task – Insert two new records into Employee table

INSERT INTO EMPLOYEE VALUES 
(9,'Smith','Thomas','Developer',1,'17-aug-1985','27-feb-2005','239 18th avenue','brooklyn','NY','USA','11200','1 (347) 429 9834','1 (347) 429 9834','thomas@chinookcorp.com');

INSERT INTO EMPLOYEE VALUES 
(10,'John','Hanks','Manager',null,'04-jan-1960','02-feb-1999','5763 w 5th street','Manhattan','NY','USA','10030','1 (928) 439 6834','1 (347) 429 9834','john@chinookcorp.com');

--    Task – Insert two new records into Customer table
INSERT INTO customer VALUES 
(60,'Louis','Johnson',null,'69 Smith st','brooklyn','NY','USA','11100','1 (347) 449 4434','1 (917) 429 9834','louis@gmail.com',3);

INSERT INTO customer VALUES 
(61,'Lury','davis',null,'69 Amestedam Avenue','New York','NY','USA','10003','1 (347) 449 4434','1 (917) 429 9834','davis@gmail.com',10);

--    2.4 UPDATE
--    Task – Update Aaron Mitchell in Customer table to Robert Walter
update Customer set  firstname ='Robert',lastname='Walter'
where (firstname ='Aaron' and lastname='Mitchell');
--    Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update Artist set name='CCR' 
where NAME='Creedence Clearwater Revival';
--    2.5 LIKE
--    Task – Select all invoices with a billing address like “T%”
select * from INVOICE 
where BILLINGADDRESS like 'T%';
--    2.6 BETWEEN
--    Task – Select all invoices that have a total between 15 and 50
select * from INVOICE
where TOTAL between 15 and 50;
--    Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
Select * from employee
where hiredate between '01-jun-2003' and '01-mar-2004';
--    2.7 DELETE
--    Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
   ALTER TABLE INVOICE DROP CONSTRAINT FK_INVOICECUSTOMERID;  
   ALTER TABLE INVOICE ADD CONSTRAINT FK_INVOICECUTOMERID FOREIGN KEY (CUSTOMERID) REFERENCES
              CUSTOMER(CUSTOMERID) ON DELETE CASCADE;  
   ALTER TABLE INVOICEline DROP CONSTRAINT FK_INVOICELINEINVOICEID;  
   ALTER TABLE INVOICELINE ADD CONSTRAINT FK_INVOICELINEINVOICEID FOREIGN KEY (INVOICEID) REFERENCES
              INVOICE(INVOICEID) ON DELETE CASCADE;
    delete CUSTOMER cascade
where FIRSTNAME='Robert' and LASTNAME='Walter';
--    3.0	SQL Functions
--    In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
--    3.1 System Defined Functions
--    Task – Create a function that returns the current time.

create or replace function currentTime return varchar2 as
t varchar2(8);
begin
select to_char(SYSDATE, 'HH24:MI:SS') into t from dual;
return t;
end ;
--use the function 
select CURRENTTIME from dual;

--    Task – create a function that returns the length of a mediatype from the mediatype table

create or replace function length_mediatype return int 
is 
c int;
begin 
select count(*) into c 
from MEDIATYPE;
return c ;  
end;
--USE IT 
select LENGTH_MEDIATYPE from dual;


--    3.2 System Defined Aggregate Functions
--    Task – Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION INVOICE_AVR RETURN NUMERIC
IS 
AVERAGE NUMERIC(5,2);
BEGIN 
SELECT AVG(TOTAL) INTO AVERAGE
FROM INVOICE;
RETURN AVERAGE;
END;

SELECT INVOICE_AVR FROM DUAL;
--    Task – Create a function that returns the most expensive track
--    IF YOU ASK FOR THE NAME
CREATE OR REPLACE FUNCTION MOST_EXPENSIVE_TRACK RETURN VARCHAR2
IS 
TR VARCHAR2(50);
BEGIN 
SELECT NAME INTO TR
FROM (SELECT * FROM TRACK 
      ORDER BY UNITPRICE DESC)
WHERE ROWNUM=1;
RETURN TR;
END;

SELECT MOST_EXPENSIVE_TRACK FROM DUAL;

--if you asked for number:
CREATE OR REPLACE FUNCTION MOST_EXPENSIVE_TRACK RETURN NUMERIC
IS 
TR NUMERIC(5,2);
BEGIN 
SELECT max(unitprice)into TR FROM TRACK ;
      
RETURN TR;
END;
select MOST_EXPENSIVE_TRACK  from dual;


--    3.3 User Defined Scalar Functions
--    Task – Create a function that returns the average price of invoiceline items in the invoiceline table
create or replace function avr_price return numeric
is 
price numeric;
begin
select AVG(unitprice) into price from INVOICELINE;
return price;
end;

select avr_price from dual;
--    3.4 User Defined Table Valued Functions
--    Task – Create a function that returns all employees who are born after 1968.

CREATE OR REPLACE FUNCTION EMP_BORN_AFTER1968 RETURN SYS_REFCURSOR AS 
EMP_CURSOR SYS_REFCURSOR;
BEGIN
  OPEN EMP_CURSOR FOR
  SELECT * FROM EMPLOYEE
  WHERE (BIRTHDATE >='01-JAN-68');
  RETURN EMP_CURSOR;
END EMP_BORN_AFTER1968;
select EMP_BORN_AFTER1968 from dual;
declare
    EmployeeId NUMBER;
    LastName VARCHAR2(20);
    FirstName VARCHAR2(20);
    Title VARCHAR2(30);
    ReportsTo NUMBER;
    BirthDate DATE;
    HireDate DATE;
    Address VARCHAR2(70);
    City VARCHAR2(40);
    State VARCHAR2(40);
    Country VARCHAR2(40);
    PostalCode VARCHAR2(10);
    Phone VARCHAR2(24);
    Fax VARCHAR2(24);
    Email VARCHAR2(60);

    c SYS_REFCURSOR;
BEGIN
c:=EMP_BORN_AFTER1968;
LOOP 
FETCH c INTO EmployeeId,LastName,FirstName,TITLE,REPORTSTO,BirthDate,HIREDATE,ADDRESS,CITY,STATE,COUNTRY,POSTALCODE,
PHONE,FAX,EMAIL;
EXIT WHEN c%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(employeeid || ' ' || lastname || ' ' || firstname ||' '|| TITLE || ' ' || BirthDate);
END LOOP;
END;

--    4.0 Stored Procedures
--     In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
--    4.1 Basic Stored Procedure
--    Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE EMPL_FULLNAME(CURSORNAME OUT SYS_REFCURSOR)
IS
BEGIN
OPEN CURSORNAME FOR
SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;

DECLARE
FULLNAME SYS_REFCURSOR;
FNAME VARCHAR2(20);
LNAME VARCHAR2(20);
BEGIN
EMPL_FULLNAME(FULLNAME);
LOOP
FETCH FULLNAME INTO FNAME, LNAME;
EXIT WHEN FULLNAME%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(FNAME ||' '||LNAME);
END LOOP;
END;
--    4.2 Stored Procedure Input Parameters
--    Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE EMPLOYEE_UPDATE(EmployeeId IN NUMBER,LastName IN VARCHAR2,FirstName IN VARCHAR2,
Title IN VARCHAR2,ReportsTo IN NUMBER,BirthDate IN DATE,HireDate IN DATE,Address IN VARCHAR2,City IN VARCHAR2,
State IN VARCHAR2,Country IN VARCHAR2,PostalCode IN VARCHAR2,Phone VARCHAR2,Fax IN VARCHAR2,Email IN VARCHAR2)
IS
BEGIN
UPDATE EMPLOYEE 
SET employee.EmployeeId =employeeid,
    EMPLOYEE.LastName =lastname,
    EMPLOYEE.FirstName =  firstname,
    EMPLOYEE.Title = title,
    EMPLOYEE.ReportsTo =reportsto,
    EMPLOYEE.BirthDate =birthdate,
    EMPLOYEE.HireDate =hiredate,
    EMPLOYEE.Address= address,
    EMPLOYEE.City =city,
    EMPLOYEE.State =state,
    EMPLOYEE.Country= country,
    EMPLOYEE.PostalCode=postalcode,
    EMPLOYEE.Phone =phone,
    EMPLOYEE.Fax =fax,
    EMPLOYEE.Email =email
    where (EMPLOYEE.EMPLOYEEID=employeeid);
END;
--    Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE MANAGERS(EMP_ID IN NUMERIC, FNAME OUT VARCHAR2,LNAME OUT VARCHAR2)
IS
BEGIN
SELECT E1.FIRSTNAME, E1.LASTNAME INTO FNAME,LNAME 
FROM EMPLOYEE E1 INNER JOIN EMPLOYEE E2 ON E1.EMPLOYEEID=E2.REPORTSTO
WHERE E2.EMPLOYEEID=EMP_ID;
END;
DECLARE
FNAME VARCHAR2(20);
LNAME VARCHAR2(20);
BEGIN
MANAGERS(6,FNAME,LNAME);
DBMS_OUTPUT.put_line(FNAME||' '||LNAME);
END;
--    4.3 Stored Procedure Output Parameters
--    Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE CUSTOMER_BUSSINESS(CUST_ID IN NUMERIC, FNAME OUT VARCHAR2,LNAME OUT VARCHAR2, COMPANY_NAME OUT VARCHAR2)
IS
BEGIN
SELECT FIRSTNAME, LASTNAME, COMPANY INTO FNAME,LNAME, COMPANY_NAME 
FROM CUSTOMER
WHERE CUSTOMERID=CUST_ID;
END;
DECLARE
FNAME VARCHAR2(20);
LNAME VARCHAR2(20);
CUST NUMERIC;
COMPY VARCHAR(20);
BEGIN
CUSTOMER_BUSSINESS(10,FNAME,LNAME,COMPY);
DBMS_OUTPUT.put_line(FNAME||' '||LNAME||' '||COMPY);
END;

--    5.0 Transactions
--    In this section you will be working with transactions. Transactions are usually nested within a stored procedure.

--    Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE DELETE_INVOICE(ID_ IN NUMERIC)
IS
BEGIN
DELETE FROM INVOICE
WHERE INVOICEID=ID_;
END;
BEGIN
DELETE_INVOICE(412);
END;

--    Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE INSERT_CUSTOMER(CUSTOMERId IN NUMBER,LastName IN VARCHAR2,FirstName IN VARCHAR2,
COMPANY IN VARCHAR2,Address IN VARCHAR2,City IN VARCHAR2,State IN VARCHAR2,Country IN VARCHAR2,PostalCode IN VARCHAR2,
Phone VARCHAR2,Fax IN VARCHAR2,Email IN VARCHAR2,SUPPORTREPID IN NUMBER) IS
BEGIN
    INSERT INTO CUSTOMER
    VALUES (CUSTOMERId,LastName,FirstName,COMPANY,Address,City,State,Country,PostalCode,PHone,Fax,Email,SUPPORTREPID);
END;
BEGIN
INSERT_CUSTOMER(412,'Bin','Joe','Ultra','234 65 street','Orange','NJ','USA','43943','3459248294','8479388484','joebein@yahoo.com',3);
END;
--    6.0 Triggers
--    In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.

--    6.1 AFTER/FOR
--    Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER EMP_INSERT_TRIGGER
AFTER INSERT ON EMPLOYEE
BEGIN
DBMS_OUTPUT.PUT_LINE('new record is inserted into employee table ');
END;
--    Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER alb_update_TRIGGER
AFTER update ON album
BEGIN
DBMS_OUTPUT.PUT_LINE('Album table updated');
END;
--    Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER cust_deleteT_TRIGGER
AFTER INSERT ON customer
BEGIN
DBMS_OUTPUT.PUT_LINE('customer deleted');
END;
--    7.0 JOINS
--    In this section you will be working with combing various tables through the use of joins. 
--    You will work with outer, inner, right, left, cross, and self joins.
--    7.1 INNER
--    Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT I.INVOICEID, C.FIRSTNAME, C.LASTNAME
FROM CUSTOMER C INNER JOIN INVOICE I ON (C.CUSTOMERID=I.CUSTOMERID);
--    7.2 OUTER
--    Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId,
--        firstname, lastname, invoiceId, and total.

SELECT C.CUSTOMERID, C.FIRSTNAME,C.LASTNAME,I.INVOICEID, I.TOTAL
FROM CUSTOMER C FULL OUTER JOIN INVOICE I ON (C.CUSTOMERID=I.CUSTOMERID);
--    7.3 RIGHT
--    Task – Create a right join that joins album and artist specifying artist name and title.
SELECT NAME,TITLE
FROM ALBUM RIGHT JOIN ARTIST ON (ALBUM.ARTISTID=ARTIST.ARTISTID);
--    7.4 CROSS
--    Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT *
FROM ALBUM 
CROSS JOIN ARTIST 
ORDER BY ARTIST.NAME;
--    7.5 SELF
--    Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT * FROM 
EMPLOYEE E1 LEFT JOIN EMPLOYEE E2 
ON (E1.EMPLOYEEID=E2.EMPLOYEEID);
