
Drop Table bank cascade constraints;
Drop Table client cascade constraints;
Drop Table amount cascade constraints;
DROP SEQUENCE client_seq;
DROP TRIGGER client_seq_trigger;

CREATE TABLE bank (
    bank_id int,
    bank_name varchar(255),
    CONSTRAINT bank_id_pk PRIMARY KEY (bank_id)
);

CREATE TABLE amount (
    amount_id int,
    amount NUMBER(2),
    CONSTRAINT amount_id_pk PRIMARY KEY (amount_id)
);

CREATE TABLE client (
    client_id int,
    client_name varchar(255),
    client_password varchar(255),
    client_bank_id int,
    client_amount_id int,
    CONSTRAINT client_id_pk PRIMARY KEY (client_id),
    CONSTRAINT client_owner_id_fk FOREIGN KEY (client_bank_id)
        REFERENCES bank (bank_id)  ON DELETE CASCADE,
    CONSTRAINT client_amount_id_fk FOREIGN KEY (client_amount_id)
        REFERENCES amount (amount_id)  ON DELETE CASCADE,
    CONSTRAINT client_name_u UNIQUE (client_name)
);

CREATE SEQUENCE client_seq
    start with 1
    increment by 1;

CREATE OR REPLACE TRIGGER client_seq_trigger --for auto incrementing the pk
BEFORE INSERT ON client
FOR EACH ROW
BEGIN --This keyword signifies a block for a transaction
    IF :new.client_id IS NULL THEN
    SELECT client_seq.nextval INTO :new.client_id from dual;
    SELECT client_seq.currval INTO :new.client_amount_id from dual;
    INSERT INTO amount(amount_id, amount) VALUES (client_seq.currval, 0);
    END IF;
END;



SELECT * from bank;
SELECT * from amount;
SELECT  * from client;

INSERT INTO bank VALUES (1, 'chase');
INSERT INTO amount VALUES (1, 0);
INSERT INTO client( client_name, client_password, client_bank_id)
VALUES ('adam', 'adam', 1);

SELECT * From amount JOIN client ON client_bank_id = amount.amount_id;

SELECT * From client;

SELECT * FROM client WHERE client_name ='adam';

COMMIT ;