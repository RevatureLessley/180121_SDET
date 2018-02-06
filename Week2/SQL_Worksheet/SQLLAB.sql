/*
Omowumi Lynda Ademola
SQL Lab
DUE: February 6, 2018
*/

/*
    SQL QUERIES
*/
--2.1 SELECT
SELECT * FROM employee;
SELECT * FROM employee WHERE lastname = 'King';
SELECT * FROM employee WHERE firstname = 'Andrew' AND reportsto IS NULL;

--2.2 ORDER BY
SELECT * FROM album
ORDER BY title DESC;
SELECT firstname FROM customer
ORDER BY city;

--2.3 INSERT INTO
INSERT INTO genre VALUES (26, 'K-Pop');
INSERT INTO genre VALUES (27, 'Lo-Fi');

INSERT INTO employee VALUES (9, 'Kim', 'Namjoon', 'Leader', 1 , TO_DATE('12/09/1994', 'dd/mm/yyyy'), NULL, 
'10-35 Big Hit', 'Seoul','SK','South Korea',19374, 9747269375, 9747269375, 'kingnamjoon@bighit.com'); 
INSERT INTO employee VALUES (10, 'Boo', 'Ghost', 'Spectre', 7 , TO_DATE('12/04/1923', 'dd/mm/yyyy'), NULL, 
'50th Street', 'Server','AR','United States of America',96536, 0763352522, 0763352522, 'bghost@grave.com');

INSERT INTO customer VALUES (60, 'Sia', 'Furler', 'Atlantic', '232 England', 'Adelaide', 'South Australia', 
'Australia', 97492, 9373658223, 9373658223, 'sia@atlantic.com', 4);
INSERT INTO customer VALUES (61, 'Aaron', 'McCain', 'Reflection', '67 Redfarm st', 'Chicago', 'Illinois', 
'Unitd States of America', 19846, 3947593478, 3947593478, 'canon@reflection.com', 3);

--2.4 UPDATE
UPDATE customer 
SET firstname = 'Robert', lastname = 'Walter'
WHERE customerid = 32;

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

--2.5 LIKE
SELECT * FROM invoice
WHERE billingaddress LIKE 'T%';

--2.6 BETWEEN
SELECT * FROM invoice
WHERE total BETWEEN 15 AND 50;

SELECT * FROM employee
WHERE hiredate 
BETWEEN TO_DATE('01/06/2003','dd/mm/yyyy') AND TO_DATE('01/03/2004','dd/mm/yyyy');

--2.7 DELETE
SAVEPOINT before_delete;

ALTER TABLE invoice 
DROP CONSTRAINT fk_invoicecustomerid;
ALTER TABLE invoice
ADD CONSTRAINT fk_invoicecustomerid FOREIGN KEY (customerid)
    REFERENCES customer (customerid) ON DELETE CASCADE;
    
ALTER TABLE invoiceline
DROP CONSTRAINT fk_invoicelineinvoiceid;
ALTER TABLE invoiceline
ADD CONSTRAINT fk_invoicelineinvoiceid FOREIGN KEY (invoiceid)
    REFERENCES invoice (invoiceid) ON DELETE CASCADE;
    
DELETE FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter';

/*
    SQL FUNCTIONS
*/
--3.1 SYSTEM DEFINED FUNCTIONS
SELECT SYSTIMESTAMP FROM dual;
SELECT LENGTH(name) AS "mediatype length" FROM mediatype WHERE mediatypeid=1;

--3.2 SYSTEM DEFINED AGGREGATE FUNCTIONS
SELECT ROUND(AVG(total), 2) AS "invoice total's avg" FROM invoice;
SELECT name, unitprice FROM track
WHERE unitprice = (SELECT MAX(unitprice) FROM track);

--3.3 USER DEFINED SCALER FUNCIONS
CREATE OR REPLACE FUNCTION avgpriceinvoice
RETURN NUMBER
IS
    avgprice NUMBER;
BEGIN
    SELECT AVG(unitprice) INTO avgprice FROM invoiceline;
    RETURN avgprice;
END;

DECLARE
    avgprice NUMBER(6,2);
BEGIN
    avgprice := avgpriceinvoice;
    DBMS_OUTPUT.PUT_LINE('AVG INVOICELINE PRICE: ' || avgprice);
END;
/

--3.4 USER DEFINED TABLE VALUED FUNCTIONS
CREATE OR REPLACE FUNCTION emp_after_year(inyear IN VARCHAR2)
RETURN SYS_REFCURSOR
IS
    sys_cursor SYS_REFCURSOR;
BEGIN
    OPEN sys_cursor FOR
    --SELECT * FROM employee WHERE birthdate > TO_DATE('1968-12-31', 'yyyy-mm-dd');
    SELECT employeeid, lastname, firstname, title, birthdate FROM employee WHERE birthdate > TO_DATE(inyear, 'yyyy');
    RETURN sys_cursor;
END;
/

DECLARE
    my_cursor SYS_REFCURSOR;
    empid employee.employeeid%TYPE;
    emplname employee.lastname%TYPE;
    empfname employee.firstname%TYPE;
    emptitle employee.title%TYPE;
    empbdate employee.birthdate%TYPE;
BEGIN
    my_cursor := emp_after_year('1968');
    LOOP
        FETCH my_cursor INTO empid, emplname, empfname, emptitle, empbdate;
        EXIT WHEN my_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(empid || ' | ' || emplname || ', ' || empfname || ' | ' || emptitle || ' | ' || empbdate);
    END LOOP;
END;
/

/*
    STORED PROCEDURES
*/
--4.1 BASIC STORE PROCEDURE
CREATE OR REPLACE PROCEDURE get_first_last_emp(my_cursor OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN my_cursor FOR
    SELECT firstname, lastname FROM employee;
END;
/

DECLARE
    my_cursor SYS_REFCURSOR;
    empfname employee.firstname%TYPE;
    emplname employee.lastname%TYPE;
BEGIN
    get_first_last_emp(my_cursor);
    LOOP
        FETCH my_cursor INTO empfname, emplname;
        EXIT WHEN my_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(empfname || ' ' || emplname);
    END LOOP;
END;
/

--4.2 STORED PROCEDURE INPUT PARAMETERS
CREATE OR REPLACE PROCEDURE update_employee(in_id IN NUMBER, in_title IN VARCHAR2, in_phone IN VARCHAR2, in_postal IN VARCHAR2)
IS
BEGIN
    UPDATE employee SET title = in_title, phone = in_phone, postalcode = in_postal WHERE employeeid = in_id; 
END;
/

BEGIN
    update_employee(10, 'Poltergeist', '9379470376', '00000');
END;
/

CREATE OR REPLACE PROCEDURE get_managers(emp_id IN NUMBER, manage_cursor OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN manage_cursor FOR
    SELECT a.firstname, a.lastname, a.title, m.firstname, m.lastname, m.title
    FROM employee a RIGHT JOIN employee m ON a.reportsto = m.employeeid 
    WHERE a.employeeid = emp_id;
END;
/

DECLARE
    my_cursor SYS_REFCURSOR;
    afname employee.firstname%TYPE;
    alname employee.lastname%TYPE;
    atitle employee.title%TYPE;
    mfname employee.firstname%TYPE;
    mlname employee.lastname%TYPE;
    mtitle employee.title%TYPE;
BEGIN
    get_managers(4, my_cursor);
    FETCH my_cursor INTO afname, alname, atitle, mfname, mlname, mtitle;
    DBMS_OUTPUT.PUT_LINE(atitle || ' ' || alname || ', ' || afname || 
        ' REPORTSTO ' || mtitle || ' ' || mlname || ', ' || mfname);
END;

--4.3 STORED PROCEDURE OUTPUT PARAMETERS
CREATE OR REPLACE PROCEDURE customer_name_company(cust_id IN NUMBER, 
    cust_fname OUT VARCHAR2, cust_lname OUT VARCHAR2, cust_company OUT VARCHAR2)
IS
BEGIN
    SELECT firstname, lastname, company 
    INTO cust_fname, cust_lname, cust_company 
    FROM customer WHERE customerid = cust_id;
END;
/

DECLARE
    c_fname customer.firstname%TYPE;
    c_lname customer.lastname%TYPE;
    c_company customer.company%TYPE;
BEGIN
    customer_name_company(5, c_fname, c_lname, c_company);
    DBMS_OUTPUT.PUT_LINE(c_lname || ', ' || c_fname || ' works at ' || c_company);
END;

/*
    TRANSACTIONS
*/
CREATE OR REPLACE PROCEDURE delete_invoice(in_invoice_id IN NUMBER)
IS
BEGIN
    DELETE FROM invoice WHERE invoiceid = in_invoice_id;
    COMMIT;
END;

BEGIN
    delete_invoice(1);
END;

CREATE OR REPLACE PROCEDURE add_customer(in_id IN NUMBER, in_fn IN VARCHAR2, in_ln IN VARCHAR2, in_comp IN VARCHAR2,
    in_country IN VARCHAR2, in_postalcode IN VARCHAR2, in_email IN VARCHAR2)
IS
BEGIN
    INSERT INTO customer (customerid, firstname, lastname, company, country, postalcode, email) 
    VALUES (in_id, in_fn, in_ln, in_comp, in_country, in_postalcode, in_email);
    COMMIT;
END;

BEGIN
    add_customer(62, 'Vernon', 'Hansol', 'SEVENTEEN', 'South Korea', '09833', 'vhc@pledis.com');
END;

/*
    TRIGGERS
*/
--6.1 AFTER/FOR
CREATE OR REPLACE TRIGGER after_emp_insert
AFTER INSERT ON employee
FOR EACH ROW
BEGIN
    UPDATE customer SET lastname = upper(lastname);
END;
/

CREATE OR REPLACE TRIGGER after_album_insert
AFTER UPDATE ON album
BEGIN
    UPDATE artist SET name = upper(name);
END;
/

CREATE OR REPLACE TRIGGER after_customer_delete
AFTER DELETE ON customer
BEGIN
    UPDATE customer SET lastname = lower(lastname);
END;
/

/*
    JOINS
*/
--7.1 INNER
SELECT a.firstname, a.lastname, b.invoiceid FROM customer a INNER JOIN invoice b ON a.customerid = b.customerid;

--7.2 OUTER
SELECT a.customerid, a.firstname, a.lastname, b.invoiceid, b.total 
FROM customer a FULL OUTER JOIN invoice b ON a.customerid = b.customerid;

--7.3 RIGHT
SELECT b.name, a.title FROM album a RIGHT JOIN artist b ON a.artistid = b.artistid;

--7.4 CROSS
SELECT b.name, a.title FROM album a CROSS JOIN artist b
ORDER BY b.name;

--7.5 SELF
SELECT a.employeeid, a.lastname, a.firstname, a.reportsto, b.lastname, b.firstname 
FROM employee a JOIN employee b ON a.reportsto = b.employeeid;