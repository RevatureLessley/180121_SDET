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
select * from employees;