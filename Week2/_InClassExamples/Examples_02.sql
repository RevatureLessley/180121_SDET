DROP TABLE employees cascade constraints;

CREATE TABLE Employees(
    E_ID number(6) primary key,
    E_Name varchar2(100),
    E_Salary number(9),
    E_Title varchar2(100)
);

INSERT INTO EMPLOYEES
VALUES(1, 'Bobbert', 235000, 'TRAINER');
INSERT INTO EMPLOYEES
VALUES(2, 'Ryan', 0, 'TRAINER');
INSERT INTO EMPLOYEES
VALUES(3, 'Tom', 120000, 'MARKETER');
INSERT INTO EMPLOYEES
VALUES(4, 'Dogbert', 450000, 'SALES');
INSERT INTO EMPLOYEES
VALUES(5, 'Bill Nai', 25000, 'SALES');
INSERT INTO EMPLOYEES
VALUES(6, 'Overlord Ryan Lessley', 30000, 'TRAINER');
INSERT INTO EMPLOYEES
VALUES(7, 'Tom', 220000, 'MARKETER');
INSERT INTO EMPLOYEES
VALUES(8, 'tim', 220000, 'MARKETER');
INSERT INTO EMPLOYEES
VALUES(9, 'TAM', 220000, 'MARKETER');
INSERT INTO EMPLOYEES
VALUES(10, 'Tommy', 220000, 'MARKETER');

/*
    Aggregate Functions
    -An aggregate function is a function you apply to a column that will process
    all the data and return a signle value back. Examples are:
    SUM()\
    AVG()
    MAX()
    MIN()
    STDDEV()
    VARIANCE()
    COUNT() <- a big one!
    LAST()
    FIRST()
    
*/

select count(*) from Employees;
--Use AS to rename a column for a query.
--You MUST use quotation marks.

select count(*) AS "Number of Employees" from employees;

--In order to show multiple columns of data along side an aggregate function, 
--we need to tell how we are going to actually group non matching data.
select sum(e_salary), e_title from employees
group by e_title
order by sum(e_salary); --Ascending by default, do: ORDER BY SUM(e_salary) DESC for descending order.
/*
    You must use GROUP BY to signify which column you want to group your data by
    with aggregate funct
*/

/*
    SCALAR FUNCTIONS
    -a function that applies to each cell of a column
    -e.g. Alters the value within each cell of a column
    -when in doubt, think String manipulaiton methods
    lower() <--change the casing of a string
    upper()
    ABS()   <-- absolute value of a number
    cos,sin,tan()
    ROUND() <-- to nearest whole number
    TRUNC() 
    CONCAT()
    LENGTH()
    TRIM()
    LTRIM()
    RTRIM()
    -All functions for casting datatypes are considered scalar as well.
*/

select upper(e_name), lower(e_title) from employees;

/*
    HAVING
    -Having is a conditional that can be applied to aggregate data.
*/
select sum(e_salary), lower(e_title) from employees
where e_salary < 200000
group by e_title
having sum(e_salary) > 100000
order by e_title;

select e_title, e_salary from employees
order by e_title, e_salary;
--Use order by to sort the data, one column at a time.


/*
    LIKE
    -we use the LIKE clause to search for data that matches a sort of regular
    expression criteria.
    There are two wildcards you can use:
    _  - ONE of any character
    %  - 0-many of any character
    
*/
SELECT * FROM employees
where lower(e_name) LIKE 't%';
--All names that start with 't', not case sensitive.

SELECT * FROM employees
where lower(e_name) LIKE 'far%y%o%l__';
--Words that start with far, have a 'y' appear before an 'o' and 'l', as well
--'l' being the 3rd to last letter.

--JOINS
select a.pet_name as "PET NAME",
CASE b.animal_name 
    WHEN NULL THEN 'UNKNOWN'
    ELSE b.animal_name
END as "ANIMAL"
,
c.owner_name as "OWNER" from pets a
inner join animals b
on a.ANIMAL_ID = b.animal_id
inner join owners c
on a.owner_id = c.owner_id;

/*
 Above we performed a two inner joins in one query.
 Due to using an '=' comparator, we can call this a 'equijoin'.
 We also use alias for the tables for ease of referencing individual columns.
*/

DROP TABLE TA;
DROP TABLE TB;

CREATE TABLE TA(
    nums number(6),
    chars varchar2(10)
);

CREATE TABLE TB(
    nums number(6),
    otherchars varchar2(10)
);

INSERT INTO TA VALUES(1,'a');
INSERT INTO TA VALUES(2,'b');
INSERT INTO TA VALUES(3,'c');
INSERT INTO TA VALUES(4,'d');

INSERT INTO TB VALUES(3,'C');
INSERT INTO TB VALUES(4,'D');
INSERT INTO TB VALUES(5,'E');
INSERT INTO TB VALUES(6,'F');
INSERT INTO TB VALUES(7,'G');

select * from TA;
select * from TB;
select * from TA
inner join TB
on TA.nums = TB.nums;
--Inner join yields 2 records
--TA has 4 records, TB has 5 records.

select * from TA
left join TB
on TA.nums = TB.nums
ORDER BY ta.nums;

select * from TA
right join TB
on TA.nums = TB.nums
ORDER BY ta.nums;

select * from TA
full outer join TB
on TA.nums = TB.nums
ORDER BY ta.nums;
-- Gauranteed 5 to 9 records;

select * from TA
cross join TB
ORDER BY ta.nums;
--Can also cross join like so:
select * from TA,TB
ORDER BY ta.nums;

--Self join

DROP Table emps;
CREATE TABLE emps(
    emp_id number(6) primary key,
    e_name varchar2(100),
    super_id number(6),
    CONSTRAINT some_fk FOREIGN KEY (super_id) REFERENCES emps(emp_id)
);

INSERT INTO emps VALUES(1,'Bobbert', null);
INSERT INTO emps VALUES(2,'Ryan', 1);
INSERT INTO emps VALUES(3,'Bobby', 2);
INSERT INTO emps VALUES(4,'Bob', 2);
INSERT INTO emps VALUES(5,'Robert', 2);

select * from emps;

/*
    This employee per supervisor count query would be an example of a good time
    to perform a self join, since we would need information pertaining to the 
    supervisor id's name.
*/
select a.e_name, count(a.emp_id) from emps a
inner join emps b
on a.emp_id = b.super_id
group by a.e_name;

select * from emps;


--SET OPERATIONS

select NUMS, chars from TA
UNION
select NUMS, lower(otherchars) from TB;

select NUMS, chars from TA
UNION ALL
select NUMS, lower(otherchars) from TB
order by Nums;

select NUMS, chars from TA
INTERSECT
select NUMS, lower(otherchars) from TB
order by Nums;

select NUMS, chars from TA
MINUS
select NUMS, lower(otherchars) from TB
order by Nums;

select a.nums, a.chars from TA a
left join TB b
on a.nums = b.nums
where b.nums is null;


select * from ta 
natural join tb;

select nums, chars, upper(chars) as "OTHERCHARS" from ta
intersect 
select nums, lower(otherchars), otherchars as "OTHERCHARS" from tb;


/*
    IN
    -The IN clause is used to confirm that a value is inside a column.
    -Sort of works like a comma separated OR statement.
*/

select * from animals
where ANIMAL_NAME IN ('Dog','cat','Ocelet');

--HOWEVER, the IN clause is very powerful in that it lets us utilize
--nested select statements.

Select * from pets where shop_id in
(SELECT shop_id FROM pet_shop where shop_owner_id in
(SELECT owner_id from Owners where owner_name='Bobbert'));

--EXISTS
/*
    EXISTS works like IN, except as opposed to seeing if a specific value is in
    a collection like IN does, EXISTS only aims to succeed if at least ONE record
    id returned to it.
*/

select * from pets where EXISTS(
    select * from pet_shop WHERE EXISTS(
        select * from owners WHERE owner_name = 'Bobbert' AND pet_shop.shop_owner_id = owners.owner_id
    )   AND pets.SHOP_ID = pet_Shop.shop_id 
);



/*
    EXISTS VS IN
    -Both of these commands can be used to perform conditional checks
    -High level overview; EXISTS is GARBAGE;
    -It is highly inefficient if the outer query is even remotely large;
    This is because, with EXISTS, for each record in the outer query, we run
    the inner query once. Should there be, yet another inner query, we run the
    innermost for each record of the middle query, and again for each record in the
    outermost query in an exponential fashion.
    
    Nested select statements with IN, works to combine queries together as it digs in
    deeper. So to combine a query with low record count, to an inner query with high
    record count will yield slower results than EXISTS.
    But Vice versa is significantly faster.
*/

select * from owners where exists(
    select * from pet_shop where owners.owner_id = pet_shop.shop_owner_id
);


/*
    Selecting 3rd highest something from a table
*/

select * from 
(select * from (
select * from employees
order by e_salary desc, E_ID desc)
where rownum != 4
order by e_salary asc)
where rownum = 1;


(select * from (
select * from employees
order by e_salary desc, E_ID desc)
where rownum != 4)
MINUS
(select * from (
select * from employees
order by e_salary desc, E_ID desc)
where rownum != 3);


/*
    NOTE: ROWNUM stops retrieving records as soon as it equals false for just
    one record.
*/

select * from employees where e_salary between 50000 and 200000;

/*
    Views are virtual tables that can be created to ease the complexity of
    in depth queries. You can query a database and save the query + results in a
    virtual (Also known as temporary) table that can be referenced down or even
    used in more complex queries.
*/

DROP VIEW PET_OWNERSHIP;
CREATE VIEW Pet_Ownership AS
select a.pet_name as "PET NAME",
CASE b.animal_name 
    WHEN NULL THEN 'UNKNOWN'
    ELSE b.animal_name
END as "ANIMAL"
,
c.owner_name as "OWNER" from pets a
inner join animals b
on a.ANIMAL_ID = b.animal_id
inner join owners c
on a.owner_id = c.owner_id;

select * from Pet_Ownership;


delete from pet_ownership;

select * from pets;