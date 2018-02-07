SELECT * FROM Employee;

SELECT * FROM Employee
WHERE LastName = 'King';

SELECT * FROM Employee
WHERE FirstName = 'Andrew' AND ReportsTo IS NULL;

SELECT * FROM Album
ORDER BY Title DESC;

SELECT FirstName FROM Customer
ORDER BY City;


INSERT INTO Genre Values (26, 'Genre 1');
INSERT INTO Genre Values (27, 'Genre 2');

INSERT INTO Employee Values (9, 'Amr', 'Hosny', 'Staff', 1, '09-FEB-95', '20-JAN-18', '1234 That Place Av', 'Brooklyn', 'NY', 'United States', 'TYG 4N1', '+1 (347) 123-4567', '+1 (347) 343-4437', 'amr@revature.com');
INSERT INTO Employee Values (10, 'John', 'Doe', 'Manager', 2, '02-AUG-73', '1-FEB-01', '4321 That Place Av', 'Brooklyn', 'NY', 'United States', 'TYG 4N1', '+1 (347) 123-4567', '+1 (347) 343-4437', 'john@revature.com');

INSERT INTO Customer Values (60, 'Amr', 'Hosny', 'Revature', '1234 That Place Av', 'Brooklyn', 'NY', 'United States', '122241-00', '+1 (347) 123-4567', '+1 (347) 343-4437', 'amr@revature.com', 1);
INSERT INTO Customer Values (61, 'John', 'Doe', 'Revature', '5234 That Place Av', 'Brooklyn', 'NY', 'United States', '173441-00', '+1 (347) 463-4507', '+1 (347) 029-9448', 'john@revature.com', 4);

UPDATE Customer
SET FirstName = 'Robert', LastName = 'Walter'
WHERE FirstName = 'Aaron' AND LastName = 'Mitchell';


UPDATE Artist
SET Name = 'CCR'
Where Name = 'Creedence Clearwater Revival';

SELECT * FROM Invoice 
WHERE BillingAddress LIKE 'T%';

SELECT * FROM Invoice 
WHERE Total BETWEEN 15 AND 50;

SELECT * FROM Employee
WHERE HireDate BETWEEN '1-JUN-03' AND '1-MAR-04';


ALTER TABLE Invoice DROP CONSTRAINT FK_InvoiceCustomerId;
ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId) ON DELETE CASCADE ;

DELETE FROM Customer
WHERE FirstName = 'Robert' AND LastName = 'Walter';

SELECT CURRENT_TIMESTAMP from dual;

SELECT count(*) from MediaType;

SELECT AVG(Total) AS "Average Total" FROM Invoice; 

SELECT MAX(UnitPrice) AS "Highest Price" FROM Track;

SELECT AVG(UnitPrice) AS "Average Price" FROM InvoiceLine;

SELECT * FROM Employee 
WHERE BirthDate > '31-DEC-1968';

--- 4 - 6 Enter Here ---
CREATE OR REPLACE PROCEDURE getManager (emp_id IN NUMBER, mgr_firstname OUT varchar2, mgr_lastname OUT varchar2 )
IS 
BEGIN
    SELECT DISTINCT b.FirstName, b.LastName INTO mgr_firstname, mgr_lastname  FROM Employee a 
    INNER JOIN Employee b 
    ON a.EmployeeId = emp_id AND a.EmployeeId = b.ReportsTo;
END;
/

DECLARE
    emp_id Employee.EmployeeId%TYPE;
    emp_firstname Employee.FirstName%TYPE;
    emp_lastname Employee.LastName%TYPE;

BEGIN
    emp_id := 1;
    getManager(emp_id, emp_firstname, emp_lastname);
    DBMS_OUTPUT.PUT_LINE(emp_firstname || ' ' || emp_lastname);
END;
/

CREATE OR REPLACE PROCEDURE ReturnAllNames (cursorPar OUT SYS_REFCURSOR)
IS 
BEGIN
OPEN cursorPar FOR
SELECT FirstName, LastName FROM Employee;

END;

DECLARE
my_cursor SYS_REFCURSOR;
emp_firstname Employee.FirstName%TYPE;
emp_lastname Employee.LastName%TYPE;

BEGIN 
ReturnAllNames(my_cursor);

    LOOP 
        FETCH my_cursor INTO emp_firstname, emp_lastname;
        EXIT WHEN my_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(emp_firstname || ' ' || emp_lastname);
    END LOOP;
END;

CREATE OR REPLACE PROCEDURE getNameAndCompany (customer_id IN NUMBER, firstname OUT varchar2, company OUT varchar2 )
IS 
BEGIN
    SELECT DISTINCT FirstName, Company INTO firstname, company  FROM Customer 
    WHERE Customer_Id = customer_id;
END;

DECLARE

firstname Customer.FirstName%TYPE;
company Customer.Company%TYPE;

BEGIN 
        getNameAndCompany(5, firstname, company);
        DBMS_OUTPUT.PUT_LINE(firstname || ' ' || company);
END;

-- BEGIN TRANSACTION DeleteInvoice

CREATE OR REPLACE TRIGGER emp_after_trigger 
AFTER INSERT ON Employee
FOR EACH ROW
BEGIN
END;    

CREATE OR REPLACE TRIGGER emp_update_trigger 
AFTER UPDATE ON Employee
BEGIN
END;   

CREATE OR REPLACE TRIGGER emp_delete_trigger 
AFTER DELETE ON Employee
BEGIN
END;   

SELECT a.FirstName, a.LastName, b.InvoiceId
FROM Customer a
INNER JOIN Invoice b
ON a.CustomerId = b.CustomerId;

SELECT a.CustomerId, a.FirstName, a.LastName, b.InvoiceId, b.total
FROM Customer a
FULL OUTER JOIN Invoice b
ON a.CustomerId = b.CustomerId;

SELECT a.Name, b.Title
FROM Artist a 
RIGHT OUTER JOIN Album b
ON a.ArtistId = b.ArtistId;

SELECT a.Name, b.Title
FROM Artist a 
CROSS JOIN Album b
ORDER BY a.Name;

SELECT *
FROM Employee a
JOIN Employee b
ON a.ReportsTo = b.ReportsTo;

commit;
exit;