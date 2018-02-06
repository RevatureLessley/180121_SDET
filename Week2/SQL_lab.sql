

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




