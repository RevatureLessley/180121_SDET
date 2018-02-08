--This is a SQL Developer comment!
/*
    THIS IS AN SQL BLOCK COMMENT
*/

/* SUB LANGUAGES
DDL
-CREATE
-ALTER
-DROP
-TRUNCATE
DML
-SELECT
-INSERT
-UPDATE
-DELETE
TCL
-COMMIT
-ROLLBACK
-SAVEPOINT
-SET_TRANSACTION
DCL
-GRANT
-REVOKE
DQL
-SELECT
*/



--Database cleanup
/*
    CASCADE CONSTRAINTS will drop any constraints that are conflicted by
    the specific drop statement.
*/
DROP TABLE animals cascade constraints;
DROP TABLE pets cascade constraints;
DROP TABLE pet_shop cascade constraints;
DROP TABLE owners cascade constraints;

/ --Separates transactions

--DDL
CREATE TABLE animals (
    animal_id NUMBER(6) PRIMARY KEY,
    animal_name VARCHAR2(100) UNIQUE,
    animal_leg_count number(2) CHECK (animal_leg_count>1) NOT NULL
);

CREATE TABLE owners (
    owner_id number(6),
    owner_name varchar2(100) not null,
    owner_address varchar2(200),
    owner_height number(6),
    owner_age number(6),
    CONSTRAINT owner_id_pk PRIMARY KEY (owner_id)
);

CREATE TABLE pet_shop (
    shop_id number(6),
    shop_name varchar2(100) NOT NULL,
    shop_owner_id number(6),
    CONSTRAINT shop_id_pk PRIMARY KEY (shop_id),
    --CONSTRAINT constraintName CONSTRAINT TYPE (applicable column)
    CONSTRAINT shop_owner_id_fk FOREIGN KEY (shop_owner_id) 
        REFERENCES owners (owner_id) ON DELETE CASCADE
);

CREATE TABLE pets (
    pet_id number(6),
    pet_name varchar2(100),
    animal_id number(6),
    shop_id number(6),
    owner_id number(6),
    CONSTRAINT pet_id_pk PRIMARY KEY (pet_id),
    CONSTRAINT shop_id_fk FOREIGN KEY (shop_id) 
        REFERENCES pet_shop (shop_id)  ON DELETE CASCADE,
    CONSTRAINT animal_id_fk FOREIGN KEY (animal_id) 
        REFERENCES animals (animal_id) ON DELETE CASCADE,
    CONSTRAINT owner_id_fk FOREIGN KEY (owner_id) 
        REFERENCES owners (owner_id) ON DELETE CASCADE
    --CONSTRAINT null_check1 CHECK (shop_id is null AND owner_id is null)
    --,CONSTRAINT null_check2 CHECK (shop_id = '(null)' AND owner_id = '(null)')
);
/
--DML
INSERT INTO animals (ANIMAL_ID, ANIMAL_NAME, ANIMAL_LEG_COUNT)
VALUES (1, 'Dog', 4); --For strings, use SINGLE QUOTES: 'string'

--If you omit the column names portion of an insert statement, it will
--implicitly assume you are providing data for each column in the order they 
--appear in the table
INSERT INTO animals
VALUES (2, 'Cat', 4);

INSERT INTO animals
VALUES (3, 'cat', 4); --Note, case sensitive string values for unique constraints

INSERT INTO animals
VALUES (4, null, 10);

INSERT INTO animals
VALUES(5, 'Ocelet', 4);

INSERT INTO animals
VALUES (null, 'Octopus', 8); --Not valid due to  PK constraint

INSERT INTO animals (ANIMAL_ID, ANIMAL_LEG_COUNT)
VALUES (5, 10); --Unique WILL allow multiple null values.
--Omitting a column name defaults the missing value to null.

INSERT INTO owners 
VALUES(1, 'Bobbert', '1861 Bobbert LN', 58, 131);
INSERT INTO owners 
VALUES(2, 'Ryan', 'Manhattan', 45, 78);
INSERT INTO owners 
VALUES(3, 'Petar', 'New York', 74, 403);
INSERT INTO owners 
VALUES(4, 'Tom', 'On the flippin'' moon', 82, 99);

INSERT INTO PET_shop 
VaLUES (1, 'Bobbert''s Bobbables', 1); --Double single quote, writes the quote as data
INSERT INTO PET_shop 
VaLUES (2, 'Tom''s Tommables', 4);


INSERT INTO PETS (PET_ID, PET_NAME, animal_id, shop_id, owner_id)
VALUES(1, 'Jim', 5, 1, null);
INSERT INTO PETS (PET_ID, PET_NAME, animal_id, shop_id, owner_id)
VALUES(2, 'Jim But Better', 1, 2, null);
INSERT INTO PETS (PET_ID, PET_NAME, animal_id, shop_id, owner_id)
VALUES(3, 'I Hate Tom', 2, null, 2);
INSERT INTO PETS (PET_ID, PET_NAME, animal_id, shop_id, owner_id)
VALUES(4, 'Bobbert', 5, null, 3);
INSERT INTO PETS (PET_ID, PET_NAME, animal_id, shop_id, owner_id)
VALUES(5, 'Bobbert', 4, null, 3);


select * from pets;
select * from owners;

/*
    REFERENTIAL INTEGRITY
    -The enforcement of foreign key relations. It prevents a user form inserting
    a record into a table with a FK that points t a non existent element. It also
    prevents a user from deleting a record that another table is referencing.
    On top of this, we cant drop a table, that another table referecnes.
    This is all to prevent orphan records. IE, a record with no parent.
*/


--WHERE CLAUSE
/*
    Use a WHERE clause to apply any conditions to a CRUD operation.
    
*/
--select * from pets where PET_ID > 2;
--delete from pets where pet_id > 4;
--delete from owners where owner_id = 3;

--UPDATE
--Used to change date of records
update pets
set pet_name = 'Dogbert'
where lower(pet_name) = 'bobbert';

--ALTER
CREATE TABLE garbage(
    stuff number(9)
);

INSERT INTO garbage
VALUES(1);
INSERT INTO garbage
VALUES(2);
INSERT INTO garbage
VALUES(3);

ALTER TABLE garbage
RENAME TO different_garbage;

select * from different_garbage;

ALTER TABLE different_garbage
ADD CONSTRAINT check_con CHECK (stuff>0);
ALTER TABLE different_garbage
DROP CONSTRAINT check_con;


/*
 SQL DEVELOPER does NOT auto-commit data;
 You MUST use commit to persist data;
*/

INSERT INTO ANimals
VALUES(12, 'I wont be around long...', 2);
select * from animals;
rollback;
select * from animals;

commit; --Persist all data

/*Difference between DELETE and TRUNCATE
You can rollback a DELETE.
you cannot rollback a TRUNCATE.
*/

delete from pets;
rollback;
select * from pets;


--TRUNCATE TABLE pets;
--rollback;
--select * from pets;


