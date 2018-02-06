SELECT * FROM EMPLOYEE;
SELECT * FROM EMPLOYEE WHERE LASTNAME='King';
SELECT * FROM EMPLOYEE WHERE FIRSTNAME='Andrew' AND REPORTSTO IS NULL;
/*
    2.1 SELECT
    Task – Select all records from the Employee table.
    Task – Select all records from the Employee table where last name is King.
    Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
*/
SELECT * FROM Album ORDER BY title DESC;
SELECT FIRSTNAME FROM Customer ORDER BY city ASC;
/*
    2.2 ORDER BY
    Task – Select all albums in Album table and sort result set in descending order by title.
    Task – Select first name from Customer and sort result set in ascending order by city'
    */
    
    
    /*
    2.3 INSERT INTO
    Task – Insert two new records into Genre table
    Task – Insert two new records into Employee table
    Task – Insert two new records into Customer table
  */  
INSERT INTO Genre VALUES (26, 'Bachata');

INSERT INTO Genre VALUES (27, 'PBRNB');

INSERT INTO Employee VALUES (9, 'Brown', 'Jbird', 'IT Staff', 6, '10-FEB-95', '11-FEB-06', 
  '2468 Group Ave NW', 'Las Vegas', 'DC', 'Saudia Malaysia', 
  'T3S 8HK', '+1 (469) 263-4526', '+1 (607) 263-2234', 'Jbird@chinookcorp.com'
);
INSERT INTO Employee VALUES (9, 'Shely', 'Cooper', 'IT Staff', 6, '15-MAR-97', '20-MAY-04', 
  '13 Pig RD W', 'Craptown', 'CB', 'China', 'T3S 8HK', '+1 (469) 263-4526', 
  '+1 (607) 263-2234', 'Cooper@chinookcorp.com'
);
INSERT INTO Customer VALUES (60, 'Paul','Simon', 'Portoeur S.A.',	'348 Broadway', 'New Hector', 
'LF', 'Mexico', '12347-002', '+56 (15) 4420-9999', '+56 (15) 4420-9999', 'pauls@embr.com', 3
);

INSERT INTO Customer VALUES (61, 'Bryce', 'Terrence', 'Grantown', 'Av. Brigadeiro Lima, 224', 'São José dos Campos', 
'SP', 'Brazil', '12227-000', '+56 (14) 3222-7697', '56 (14) 3222-7697', 'brycet@hemono.org', 3
);

/*    
    2.4 UPDATE
    Task – Update Aaron Mitchell in Customer table to Robert Walter
    Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
    */
    
    
    UPDATE CUSTOMERS SET LASTNAME='MITCHELL', FIRSTNAME='AARON' WHERE LASTNAME= 'WALTER' AND FIRSTNAME='ROBERT';
    UPDATE ARTIST SET NAME='CCR' WHERE ARTISTID=76;
    /*
    2.5 LIKE
    Task – Select all invoices with a billing address like “T%”
    */
    SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
    
    /*
    2.6 BETWEEN
    Task – Select all invoices that have a total between 15 and 50
    Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
    2.7 DELETE
    Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
*/

    SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
    SELECT * FROM employee WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';
    DELETE FROM Customer WHERE LASTNAME= 'WALTER' AND FIRSTNAME='ROBERT';
    
    
/* 3. 3.0	SQL Functions
    In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
    3.1 System Defined Functions
    Task – Create a function that returns the current time.
*/
CREATE OR REPLACE FUNCTION timeAt
RETURN VARCHAR2
IS
    cur_time VARCHAR2(100);
BEGIN
    SELECT TO_CHAR(CURRENT_TIMESTAMP, 'HH.MI PM') INTO cur_time FROM DUAL;
    RETURN cur_time;
END;


BEGIN
    dbms_output.put_line('The Time is ' || timeat());
END;    
    DROP FUNCTION medialen;
    
    CREATE OR REPLACE FUNCTION medialen
    (namey IN VARCHAR2) RETURN NUMBER 
    AS thelen number;
        cursor mycur is
            Select * from MEDIATYPE.NAME where name = namey;
        BEGIN
        
            LENGTH();
            RETURN
        END medialen;
    
   Jawauntb.medialen('MPEG audio file') FROM mediatype;

/*
    3.2 System Defined Aggregate Functions
    Task – Create a function that returns the average total of all invoices
    Task – Create a function that returns the most expensive track*/
    
    SELECT
    AVG(invoice.TOTAL)
    FROM invoice;
    
    SELECT
    MAX(UNITPRICE) FROM track;
    
    /*
    3.3 User Defined Scalar Functions
    Task – Create a function that returns the average price of invoiceline items in the invoiceline table
    3.4 User Defined Table Valued Functions
    Task – Create a function that returns all employees who are born after 1968.
*/    
     SELECT
     AVG(UNITPRICE)FROM invoiceline;
     
     --bjhik
     
     CREATE OR REPLACE FUNCTION olderthan68
    (namey IN VARCHAR2) RETURN  
    AS
    DECLARE   
        cursor mycur is
            Select * from MEDIATYPE.NAME where name = namey;
        BEGIN
        
        RETURN
        END olderthan68;
        
     SELECT * FROM employee WHERE birthdate > '31-DEC-1968';
/*    
    4.0 Stored Procedures
     In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
    4.1 Basic Stored Procedure
    Task – Create a stored procedure that selects the first and last names of all the employees.
    4.2 Stored Procedure Input Parameters
    Task – Create a stored procedure that updates the personal information of an employee.
    Task – Create a stored procedure that returns the managers of an employee.
    4.3 Stored Procedure Output Parameters
    Task – Create a stored procedure that returns the name and company of a customer.
    
  */
    
    
    
    
/*
5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
6.1 AFTER/FOR
Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
Task – Create an after update trigger on the album table that fires after a row is inserted in the table
Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
7.0 JOINS
In this section you will be working with combing various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
7.1 INNER
Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
7.2 OUTER
Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
7.3 RIGHT
Task – Create a right join that joins album and artist specifying artist name and title.
7.4 CROSS
Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
7.5 SELF
Task – Perform a self-join on the employee table, joining on the reportsto column.
