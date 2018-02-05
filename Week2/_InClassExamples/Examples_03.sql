/*
    Oracle has an extension called PL/SQL. (Procedural langauge/Strucutred Query Language.)
    -It provides tools that we can use with SQL
    --Such as, functions, stored procedures, sequences, and triggers.
    --As well as exception handling.
    --In other words, it provides a means to write actual code in SQL.
*/

--SEQUENCE
/*
    A sequence is an object that will maintain a counter for you.
*/
DROP SEQUENCE emp_seq;
CREATE SEQUENCE emp_seq
    start with 150
    increment by 1;
    --This sequence will be used to handle the id field for employees.
    --In order to actually utilize it, we will need to build something that
    --reacts to situations where an employee inserted. E.G. Triggers.
/  
--TRIGGERS
/*
    A trigger is an object aht we can create that waits for actions to occur
    on a specific table the trigger was made for.
    A trigger can be coded to react to most CRUD operations. (Excluding select)
*/

--A trigger can react via 'BEFORE' or 'AFTER' some '
CREATE OR REPLACE TRIGGER emp_seq_trigger --for auto incrementing the pk
BEFORE INSERT ON employees
FOR EACH ROW
BEGIN --This keyword signifies a block for a transaction
    IF :new.e_id IS NULL THEN
    SELECT emp_seq.nextval INTO :new.e_id from dual;
    END IF;
END;    
/

--STORED PROCEDURES
/*
    [anything in brackets is optional]

    A named transaction that can be invoked when called.
    
    CREATE [OR REPLACE] proc_name
    IS
        This section is where you can DECLARE variables
    BEGIN
        This section is where you can write the execution, or utilize other transactions
    [EXCEPTION]
        Perform exception handling here
    
    END;
    
    You can invoke a stored procedure in two ways:
    BEGIN
        proc_name();
    END;
    
    ---or
    
    call proc_name();
*/

commit;

CREATE OR REPLACE PROCEDURE hello_world_procedure
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello World'); --SQL DEVELOPER equivalent to SYSO in Java
END;
/

BEGIN
    hello_world_procedure();
END;
/
/*
    In order to access the dbms_output console, go to View > DBMS_OUTPUT
    -This brings up the dbms_output window where you will click the '+' icon.
    -Select the schema you are currently using and it will open a console connection
    to it. Where you can then see console output.
*/

--Procedures can allow us to define executions (transactions) that can change
--data. Most actions can be taken against a table using procedures.

/*
    Things of note:
        Parameters in stored procedures are a bit unique.
        You have the following keywords to use with parameters:
            -IN
            -OUT
            -IN OUT
        IN parameters, whatever is passed as input during the procedure call
        OUT parameters, whatever is returned from the procedure
        IN OUT parameters, A variable that is used as input then transformed as output.
*/
--SYNTAX
--varname IN/OUT/IN OUT DATATYPE

CREATE OR REPLACE PROCEDURE insertIntoEmployee(empName IN varchar2, empSal IN NUMBER, empTitle varchar2)
IS
BEGIN
    INSERT INTO employees (e_name, e_salary, e_title) 
    VALUES(empName, empSal, empTitle);
    commit;
END;
/

call insertIntoEmployee('bobbert', 53427, 'Professional Bob');


CREATE OR REPLACE PROCEDURE get_name(emp_id IN number, emp_name OUT varchar2)
IS
BEGIN
    select e_name INTO emp_name from employees where e_id = emp_id;
END;
/

--Calling this procedure in SQL
DECLARE --NOTE, you must provide datatype sizes in the declaration block.
    input number(6);
    results varchar(200);
BEGIN
    input := 4;
    get_name(input, results);
    DBMS_OUTPUT.PUT_LINE(results);
END;
/

CREATE OR REPLACE PROCEDURE get_title(proc_var IN OUT varchar)
IS
BEGIN
    select e_title INTO proc_var from employees where e_name = proc_var;
END;
/
DECLARE
    input varchar2(200);
BEGIN
    input := 'Bill Nai';
    get_title(input);
    DBMS_OUTPUT.PUT_LINE(input);
END;
/

/*
    CURSORS!
    -A cursor is essentially like a pointer to a table or view.
    -We can use them to iterate through entire queries from the database.
    -When we want to pass entire tables or queries, we need to use cursors.
    
    NOTE: PL/SQL offers a CURSOR and SYS_REFCURSOR
    The SYS_REFCURSOR is a stronger cursor (Therefor more costly) that is allowed
    to be passed outside of the scope of the procedure it is in. A normal CURSOR must
    be created and die withing the scope of the procedure that it was created in.
*/

CREATE OR REPLACE PROCEDURE get_all_employees(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR --Cursor is like a stream, you OPEN it FOR some QUERY.
    SELECT * FROM employees;
END;
/
DECLARE
    my_cursor SYS_REFCURSOR;
    emp_id employees.e_id%TYPE; --Make the datatype match THAT of the employees.column's datatype.
    emp_name employees.e_name%TYPE;
    emp_sal employees.e_salary%TYPE;
    emp_title employees.e_title%TYPE;
BEGIN
    get_all_employees(my_cursor); --Have our cursor represent the cursor for employees

    LOOP
        FETCH my_cursor INTO emp_id, emp_name, emp_sal, emp_title;
        EXIT WHEN my_cursor%NOTFOUND; --%NOTFOUND does not exist until there are no records left.
        DBMS_OUTPUT.PUT_LINE(emp_id || ' ' || emp_name || ' ' || emp_sal || ' ' || emp_title);
    END LOOP;
END;
/
/*
    These were examples of EXPLICIT CURSORS where we manually defined a cursor and fetched and iterated 
    through it. 
    IMPLICIT CURSORS are created in the event of any DQL performed by a user on the database.
    We get access to the entire tables contents without having to create a cursor ourselves.
*/
--USER DEFINED FUNCTIONS
/*
    The key differences between a function and a stored procedure
    -A stored procedure does NOT have to return anything
    -A stored procedure CAN have as many IN/OUT parameters it wants.
    -A stored procedure can alter the databse using most DML statements.
    -A stored procedure can NOT be called mid query.
    -A stored procedure can call other stored procedures with it
    -A stored procedure can call functions.
    
    -A function MUST return ONE and only ONE resource.
    -A function CAN use OUT parameters, but this is highly frowned upon.
    -A function can NOT perform database DML
    -A function can be called mid query
    -A function can call other functions.
*/

CREATE OR REPLACE FUNCTION get_max_id --If no parameters, then no parenthesis
RETURN NUMBER
IS
    max_id number(6); --Number size defn not required
BEGIN
    SELECT max(e_id) into max_id FROM employees;
    RETURN max_id;
END;
/
DECLARE
    max_id number;
BEGIN
    max_id := get_max_id();
    DBMS_OUTPUT.PUT_LINE('Max employee id: ' || max_id);
END;
/

--EXCEPTION HANDLING
CREATE OR REPLACE PROCEDURE exceptionExample
IS
    CURSOR badCURSE IS
        select * from employees;
    emp_id number(6);
    emp_name varchar2(200);
    emp_salary number(6);
    emp_title varchar2(200);
BEGIN
    --OPEN badCURSE;
    LOOP
        FETCH badCURSE into emp_id, emp_name, emp_salary, emp_title;
        EXIT WHEN badCURSE%NOTFOUND;
    END LOOP;
    CLOSE badCURSE;
    
    emp_id := 1/0;
    
    EXCEPTION
        WHEN invalid_cursor THEN
            dbms_output.put_line('CURSOR ERROR');
        WHEN zero_divide THEN
            dbms_output.put_line('DIVISION BY ZERO ERROR');
    
END;
/

BEGIN
    EXCEPTIONEXAMPLE();
END;
/


CREATE TABLE indexTable (
    nums number(6)
)
/

INSERT INTO indexTable VALUES(1);
INSERT INTO indexTable VALUES(2);
INSERT INTO indexTable VALUES(3);
INSERT INTO indexTable VALUES(5);
INSERT INTO indexTable VALUES(4);
INSERT INTO indexTable VALUES(6);
INSERT INTO indexTable VALUES(7);
INSERT INTO indexTable VALUES(8);
INSERT INTO indexTable VALUES(9);

INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
INSERT INTO indexTable (select * from indexTable);
delete from indexTable where rownum < 350000;
INSERT INTO indexTable VALUES(12);
select count(*) from indexTable;
select * from indexTable where nums = 12;

drop index numsIndex;

CREATE INDEX numsindex on indexTable(nums);

truncate table indexTable;


select count(distinct(nums)) from indexTable;

