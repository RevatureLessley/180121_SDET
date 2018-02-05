--2.1-----------------------------------------------------------------------------------------------
SELECT * FROM Employee;
SELECT * FROM Employee WHERE LASTNAME = 'King';
SELECT * FROM Employee WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;
--2.2-----------------------------------------------------------------------------------------------
SELECT * FROM Album ORDER BY TITLE DESC;
SELECT FIRSTNAME FROM Customer ORDER BY CITY ASC;
--2.3-----------------------------------------------------------------------------------------------
INSERT INTO GENRE (GENREID, NAME)
VALUES (26, 'Electronic');
INSERT INTO GENRE (GENREID, NAME)
VALUES (27, 'Disco');
INSERT INTO EMPLOYEE (EMPLOYEEID, FIRSTNAME, LASTNAME)
VALUES (9, 'BARRY', 'DAVIS');
INSERT INTO EMPLOYEE (EMPLOYEEID, FIRSTNAME, LASTNAME)
VALUES (10, 'CAROLINE', 'CAMERON');
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
VALUES (60, 'EVANKA', 'OSMAK', 'EOSMAK@yahoo.com');
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
VALUES (61, 'BARRY', 'DAVIS', 'BDAVIS@yahoo.com');
--2.4-----------------------------------------------------------------------------------------------
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert',
    LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';
--2.5-----------------------------------------------------------------------------------------------
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
--2.6-----------------------------------------------------------------------------------------------
SELECT * FROM INVOICE WHERE TOTAL >= 15 AND TOTAL <= 30;
SELECT * FROM EMPLOYEE WHERE HIREDATE > '01-JUN-03' AND HIREDATE < '01-MAR-04';
--2.7-----------------------------------------------------------------------------------------------
--DELETE FROM INVOICELINE INTERSECT SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 32;
DELETE FROM INVOICELINE WHERE INVOICEID in (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 32);
DELETE FROM INVOICE WHERE CUSTOMERID = 32;
DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

--3.1-----------------------------------------------------------------------------------------------
SELECT CURRENT_TIMESTAMP FROM DUAL;
-- Audiotypes dont have lengths
--3.2-----------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_avg_invoice
RETURN NUMBER
IS
    avg_invoice float(6); --Number size defn not required
BEGIN
    SELECT AVG(total) into avg_invoice FROM invoice;
    RETURN avg_invoice;
END get_avg_invoice;
/
DECLARE
    avg_invoice float;
BEGIN
    avg_invoice := get_avg_invoice();
    DBMS_OUTPUT.PUT_LINE('Average invoice: ' || avg_invoice);
END;
/

CREATE OR REPLACE FUNCTION get_max_unitprice
RETURN NUMBER
IS
    max_unitprice float(6); --Number size defn not required
BEGIN
    SELECT MAX(unitprice) into max_unitprice FROM track;
    RETURN max_unitprice;
END get_max_unitprice;
/
DECLARE
    max_unitprice float;
BEGIN
    max_unitprice := get_max_unitprice();
    DBMS_OUTPUT.PUT_LINE('Max unitprice: ' || max_unitprice);
END;
/

--3.3-----------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION get_avg_invoiceline
RETURN NUMBER
IS
    avg_invoiceline float(6); --Number size defn not required
BEGIN
    SELECT AVG(UNITPRICE) into avg_invoiceline FROM invoiceline;
    RETURN avg_invoiceline;
END;
/
DECLARE
    avg_invoiceline float;
BEGIN
    avg_invoiceline := get_avg_invoiceline();
    DBMS_OUTPUT.PUT_LINE('Avg invoiceline: ' || avg_invoiceline);
END;
/

--3.4-----------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE get_after_birthdate (born IN DATE, cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR --Cursor is like a stream, you OPEN it FOR some QUERY.
    SELECT employeeid, lastname, firstname, title FROM employee WHERE birthdate > born;
END;
/
DECLARE
    my_cursor SYS_REFCURSOR;
    emp_id employee.employeeid%TYPE; --Make the datatype match THAT of the employees.column's datatype.
    emp_lname employee.lastname%TYPE;
    emp_fname employee.firstname%TYPE;
    emp_title employee.title%TYPE;
BEGIN
    get_after_birthdate('12-DEC-68',my_cursor); --Have our cursor represent the cursor for employees

    LOOP
        FETCH my_cursor INTO emp_id, emp_lname, emp_fname, emp_title;
        EXIT WHEN my_cursor%NOTFOUND; --%NOTFOUND does not exist until there are no records left.
        DBMS_OUTPUT.PUT_LINE(emp_id || ' ' || emp_lname || ' ' || emp_fname || ' ' || emp_title);
    END LOOP;
END;
/

DECLARE
    after_date float;
BEGIN
    after_date := get_after_birthdate('12-DEC-68');
    DBMS_OUTPUT.PUT_LINE('After date: ' || after_date);
END;
/
--4.1-----------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE get_fname_table (cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR --Cursor is like a stream, you OPEN it FOR some QUERY.
    SELECT firstname, lastname FROM employee;
END;
/
DECLARE
    my_cursor SYS_REFCURSOR;
    emp_lname employee.lastname%TYPE;
    emp_fname employee.firstname%TYPE;
BEGIN
    get_fname_table(my_cursor); --Have our cursor represent the cursor for employees

    LOOP
        FETCH my_cursor INTO emp_lname, emp_fname;
        EXIT WHEN my_cursor%NOTFOUND; --%NOTFOUND does not exist until there are no records left.
        DBMS_OUTPUT.PUT_LINE(emp_lname || ' ' || emp_fname);
    END LOOP;
END;
/
--4.2-----------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE update_employee (empid IN NUMBER)
IS
BEGIN
    UPDATE EMPLOYEE SET CITY = 'OTTAWA' WHERE empid = employeeId;
END;
/
DECLARE
    idnum NUMBER;
BEGIN
    update_employee(1);
END;
/
CREATE OR REPLACE PROCEDURE get_managers (cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR --Cursor is like a stream, you OPEN it FOR some QUERY.
    SELECT firstname, lastname, title FROM employee WHERE title LIKE '%Manager%';
END;
/
DECLARE
    my_cursor SYS_REFCURSOR;
    emp_lname employee.lastname%TYPE;
    emp_fname employee.firstname%TYPE;
    emp_title employee.title%TYPE;

BEGIN
    get_managers(my_cursor); --Have our cursor represent the cursor for employees

    LOOP
        FETCH my_cursor INTO emp_lname, emp_fname, emp_title;
        EXIT WHEN my_cursor%NOTFOUND; --%NOTFOUND does not exist until there are no records left.
        DBMS_OUTPUT.PUT_LINE(emp_lname || ' ' || emp_fname || ' ' || emp_title);
    END LOOP;
END;
/
--4.3-----------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE get_name_customer (cust_id IN NUMBER, cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR --Cursor is like a stream, you OPEN it FOR some QUERY.
    SELECT firstname, lastname, company FROM customer WHERE customerid LIKE cust_id;
END;
/
DECLARE
    my_cursor SYS_REFCURSOR;
    cust_lname customer.lastname%TYPE;
    cust_fname customer.firstname%TYPE;
    cust_company customer.company%TYPE;

BEGIN
    get_name_customer(41,my_cursor); --Have our cursor represent the cursor for employees

    LOOP
        FETCH my_cursor INTO cust_lname, cust_fname, cust_company;
        EXIT WHEN my_cursor%NOTFOUND; --%NOTFOUND does not exist until there are no records left.
        DBMS_OUTPUT.PUT_LINE(cust_fname || ' ' || cust_lname || ' ' || cust_company);
    END LOOP;
END;
/
--5.1---------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE delete_invoice (invoice_id IN NUMBER)
IS
BEGIN
    DELETE INVOICELINE WHERE INVOICEID = invoice_id;
    DELETE INVOICE WHERE INVOICEID = invoice_id;
END;
/
DECLARE
    invoiceId NUMBER;
BEGIN
    invoiceId := 2;
    delete_invoice(invoiceId); --Have our cursor represent the cursor for employees
END;
/
CREATE OR REPLACE PROCEDURE add_cust (CustID IN NUMBER, fname IN Varchar2, lname IN varchar2, elecmail IN varchar2)
IS
BEGIN
    INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
    VALUES (custid, fname, lname, elecmail);
END;
/
DECLARE
    custid NUMBER;
    fname varchar2 (50);
    lname varchar2(50);
    elecmail varchar2(50);
BEGIN
    custid := 10001;
    fname := 'Fred';
    lname := 'Flintstone';
    elecmail := 'yabba_doodle_do@hotmail.com';
    add_cust(custid, fname, lname, elecmail); --Have our cursor represent the cursor for employees
END;
/
--6.1------------------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER emp_seq_trigger --for auto incrementing the pk
AFTER INSERT ON employee
FOR EACH ROW
BEGIN --This keyword signifies a block for a transaction
    IF :old.title IS NULL THEN
    SELECT 'Janitor' INTO :old.title from dual;
    END IF;
END;   
/
INSERT INTO EMPLOYEE (EMPLOYEEID, FIRSTNAME, LASTNAME)
VALUES (100001, 'Fred', 'Flintstone');

CREATE OR REPLACE TRIGGER album_seq_trigger
AFTER UPDATE ON ALBUM
DECLARE
    titlechange varchar2(100);
    albid number(9);
BEGIN
    SELECT title into titlechange from album;
    SELECT albumid into albid from album;
    IF lower(titlechange) LIKE '%fred%' THEN
    UPDATE ALBUM SET title = 'FRED';
    END IF;
END;
/

CREATE OR REPLACE TRIGGER after_delete_customer
AFTER DELETE ON CUSTOMER
BEGIN
    INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, email)
    VALUES (12312,'a','b','hey@email.com');
END; 
/
DELETE CUSTOMER WHERE
customerID = 12312;


--7.1------------------------------------------------------------------------------------------------
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.FIRSTNAME, INVOICE.INVOICEID 
from CUSTOMER 
INNER JOIN INVOICE 
ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID;
--7.2-----------------------------------------------------------------------------------------------
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, 
CUSTOMER.FIRSTNAME, INVOICE.INVOICEID,
INVOICE.TOTAL
from CUSTOMER , INVOICE
WHERE INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID;
--7.3-----------------------------------------------------------------------------------------------
SELECT ARTIST.NAME, ALBUM.TITLE FROM ARTIST
RIGHT JOIN ALBUM
ON ARTIST.ARTISTID = ALBUM.ARTISTID;
--7.4-----------------------------------------------------------------------------------------------
SELECT * FROM ALBUM
CROSS JOIN ARTIST
ORDER BY ARTIST.NAME;
--7.5-----------------------------------------------------------------------------------------------
SELECT * FROM EMPLOYEE a
JOIN EMPLOYEE b
ON a.REPORTSTO = b.REPORTSTO;
/*
SELECT COUNT(A.REPORTSTO) FROM EMPLOYEE a
JOIN EMPLOYEE b
ON a.REPORTSTO = b.REPORTSTO
GROUP BY A.REPORTSTO
ORDER BY A.REPORTSTO;
*/