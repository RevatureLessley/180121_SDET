--Steven Caraballo



-- 2.1 select
SELECT * FROM employee ;

SELECT * FROM employee Where lastname = 'King';

SELECT * FROM  employee 
Where firstname = 'Andrew' AND reportsto is null ;

--2.2 Order By
SELECT * FROM album
ORDER BY title;

SELECT firstname, city FROM customer
ORDER BY city;

--2.3 INSERT INTO
INSERT INTO genre VALUES(26,'Crazy music');
INSERT INTO genre VALUES(27,'Retarted music');

INSERT INTO employee VALUES(9,'Caraballo','Steven','Software Developer',6,'26-JAN-93','22-JAN-18'
,'1111 sedgwick ave','Bronx','NY','USA',10453,(718)-299-4000,(718)-299-6000,'Steven26@aol.com');
INSERT INTO employee VALUES(10,'Padilla','Fabian','Sales associate',2,'30-June-93','20-may-17'
,'2323 crotona ave','Bronx','NY','USA',10453,(347)-684-8787,(917)-683-6000,'Fabe23@aol.com');

Insert Into customer VALUES(60,'Steven','Caraballo','Revature','25 sedgwick ave','Bronx','NY','USA',10453,718873176,7185657171,
'steven26@aol.com',4);
Insert Into customer VALUES(61,'Kevin','Santana','Bmw','200 Macombs Road','New York','NY','USA',002451,7188787122,7189823476,
'kev18@aol.com',5);

--2.4
update customer
set firstname = 'Robert' , lastname= 'Walter'
where firstname = 'Aaron' and lastname = 'Mitchell';

update artist
set name = 'CCR'
where name = 'Creedence Clearwater Revival';

--2.5
select * from invoice
where billingaddress like 'T%';

--2.6
select * from invoice
where invoiceid between 15 and 50;

select * from employee
where hiredate between to_date('01-06-03','DD-MM-RR' ) and to_date ('01-03-04','DD-MM-RR');

--2.7
Alter table customer
drop constraint FK_INVOICECUSTOMERID cascade
Delete from customer where firstname = 'Robert' and lastname = 'Walter';

--3.1 
SELECT CURRENT_DATE FROM dual;

Select Length(name) from mediatype
where name = 'AAC audio file';

--3.2
select avg(total) from invoice;

select max(total) from invoice;

--3.3
SELECT AVG(UnitPrice) AS "Average Price" FROM InvoiceLine;


--3.4
SELECT * FROM Employee 
WHERE BirthDate > '31-DEC-1968';

--4.0
CREATE PROCEDURE FindAllEmployees @EmpLName varchar(20),@EmpFName varchar(20)
AS  
SELECT @EmpLName, @EmpFName 
SELECT e.FirstName, e.LastName  
FROM employee e
WHERE e.LastName LIKE @EmpLName; 
GO  
EXEC FindEmployee @EmpLName = 'Barb';  
CREATE PROCEDURE getall_customers
    @LastName nvarchar(50), 
    @FirstName nvarchar(50) 
AS 

    SET NOCOUNT ON;
    SELECT FirstName, LastName
    FROM employee
    WHERE FirstName = @FirstName AND LastName = @LastName;
GO

--4.2
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE 
(
  THE_EMPLOYEEID IN NUMBER,
  NEW_LASTNAME IN VARCHAR2,
  NEW_FIRSTNAME IN VARCHAR2,
  NEW_TITLE IN VARCHAR2,
  NEW_REPORTSTO IN NUMBER,
  NEW_BIRTHDATE IN DATE,
  NEW_HIREDATE IN DATE,
  NEW_ADDRESS IN VARCHAR2,
  NEW_CITY IN VARCHAR2,
  NEW_STATE IN VARCHAR2,
  NEW_COUNTRY VARCHAR2,
  NEW_POSTALCODE VARCHAR2,
  NEW_PHONE VARCHAR2,
  NEW_FAX VARCHAR2,
  NEW_EMAIL VARCHAR2
)
AS 
BEGIN
  UPDATE EMPLOYEE
  SET LASTNAME = 
        CASE NEW_LASTNAME 
        WHEN NULL THEN 
          LASTNAME
        ELSE NEW_LASTNAME
        END,
      FIRSTNAME =
        CASE NEW_FIRSTNAME 
        WHEN NULL THEN 
          FIRSTNAME
        ELSE NEW_FIRSTNAME
        END,
      TITLE = NEW_TITLE,
      REPORTSTO = NEW_REPORTSTO,
      BIRTHDATE = NEW_BIRTHDATE,
      HIREDATE = NEW_HIREDATE,
      ADDRESS = NEW_ADDRESS,
      CITY = NEW_CITY,
      STATE = NEW_STATE,
      COUNTRY = NEW_COUNTRY,
      POSTALCODE = NEW_POSTALCODE,
      PHONE = NEW_PHONE,
      FAX = NEW_FAX,
      EMAIL = NEW_EMAIL
    WHERE EMPLOYEEID = THE_EMPLOYEEID;
END UPDATE_EMPLOYEE;


create or replace PROCEDURE MANAGER_OF_EMPLOYEE 
(
  THE_EMPLOYEEID IN NUMBER
)
AS 
  TEMP VARCHAR2(20);
  TEMP2 VARCHAR2(20);
  TEMP3 VARCHAR2(20);
  TEMP4 VARCHAR2(20);
BEGIN
  SELECT MGR.FIRSTNAME, MGR.LASTNAME, EMP.FIRSTNAME, EMP.LASTNAME INTO TEMP, TEMP2, TEMP3, TEMP4
  FROM EMPLOYEE EMP
  INNER JOIN EMPLOYEE MGR 
  ON MGR.EMPLOYEEID = EMP.REPORTSTO
  WHERE EMP.EMPLOYEEID = THE_EMPLOYEEID AND EMP.REPORTSTO = MGR.EMPLOYEEID;
  DBMS_OUTPUT.PUT_LINE(TEMP || ' ' || TEMP2 || ' IS THE MANAGER OF ' || TEMP3 || ' ' || TEMP4);
END MANAGER_OF_EMPLOYEE;

--5.0
create procedure getInvoice
@InvoiceID int
AS
BEGIN
  BEGIN TRY
    BEGIN TRANSACTION;

    
    WITH R AS (
      SELECT ArticleID, SUM(Quantity) ReclaimedQuantity
      FROM InvoiceLine
      WHERE InvoiceID = @InvoiceID
      GROUP BY ArticleID
    )
    UPDATE Article
    SET Quantity = Quantity + R.ReclaimedQuantity
    FROM Article INNER JOIN
         R ON Article.ArticleID = R.ArticleID;

    
    DELETE FROM InvoiceLine
    WHERE InvoiceID = @InvoiceID;

    
    DELETE FROM Invoice
    WHERE InvoiceID = @InvoiceID;

    COMMIT TRANSACTION;
  END TRY
  BEGIN CATCH
    ROLLBACK TRANSACTION;
  END CATCH
END




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

--6.1
CREATE OR REPLACE TRIGGER AfterTrigger 
AFTER INSERT ON Employee
FOR EACH ROW
BEGIN
END;    

CREATE OR REPLACE TRIGGER UpdateTrigger 
AFTER UPDATE ON Employee
BEGIN
END;   

CREATE OR REPLACE TRIGGER DeleteTrigger 
AFTER DELETE ON Employee
BEGIN
END;   

--7.1
SELECT a.FirstName, a.LastName, b.InvoiceId
FROM Customer a
INNER JOIN Invoice b
ON a.CustomerId = b.CustomerId;


--7.2
SELECT a.CustomerId, a.FirstName, a.LastName, b.InvoiceId, b.total
FROM Customer a
FULL OUTER JOIN Invoice b
ON a.CustomerId = b.CustomerId;

--7.3
SELECT a.Name, b.Title
FROM Artist a 
RIGHT OUTER JOIN Album b
ON a.ArtistId = b.ArtistId;


--7.4
SELECT a.Name, b.Title
FROM Artist a 
CROSS JOIN Album b
ORDER BY a.Name;


--7.5
SELECT *
FROM Employee a
JOIN Employee b
ON a.ReportsTo = b.ReportsTo;

commit;
exit;


