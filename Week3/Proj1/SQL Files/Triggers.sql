create or replace TRIGGER awarded
AFTER UPDATE ON REIMBURSEMENT
FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.BENCOAPP > 0 AND :NEW.DEPTAPP > 0
    THEN
    UPDATE TOYEAR
    SET TOYEAR.PENDING = TOYEAR.PENDING - :NEW.amount
    WHERE toyear.username = :NEW.username;
    UPDATE TOYEAR
    SET TOYEAR.AWARDED = TOYEAR.AWARDED + :NEW.amount
    WHERE toyear.username = :NEW.username;
    END IF;
END;

create or replace TRIGGER createRecordToYear
AFTER INSERT ON EMPLOYEE
FOR EACH ROW
DECLARE
    toyearusr EMPLOYEE.USERNAME%TYPE;
    emptype EMPLOYEE.TYPE%TYPE;
BEGIN
    IF :NEW.type = 0
    THEN
    toyearusr := :NEW.username;
    INSERT INTO TOYEAR (username)
    VALUES (toyearusr);
    END IF;
END;

create or replace TRIGGER event_seq_trigger --for auto incrementing the pk
BEFORE INSERT ON EVENT
FOR EACH ROW
BEGIN --This keyword signifies a block for a transaction
    IF :new.id IS NULL THEN
    SELECT event_seq.nextval INTO :new.id from dual;
    END IF;
END;

create or replace TRIGGER finalapp
AFTER INSERT ON REIMBURSEMENT
FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.bencoapp > 0
    THEN
    UPDATE REIMBURSEMENT
    SET FINALAPP = 1
    WHERE RID = :NEW.RID;
    END IF;
END;

create or replace TRIGGER newrecordtoyear
AFTER INSERT ON REIMBURSEMENT
FOR EACH ROW
DECLARE
    toyearusr EMPLOYEE.USERNAME%TYPE;
    emptype EMPLOYEE.TYPE%TYPE;
BEGIN
    IF :NEW.amount > 0
    THEN
    UPDATE TOYEAR
    SET TOYEAR.PENDING = TOYEAR.PENDING + :NEW.amount
    WHERE toyear.username = :NEW.username;
    END IF;
END;

create or replace TRIGGER rejected
AFTER UPDATE ON REIMBURSEMENT
FOR EACH ROW
DECLARE
    amount REIMBURSEMENT.AMOUNT%TYPE;
BEGIN
    amount := :NEW.amount;
    IF :NEW.rejected > 0
    THEN
    UPDATE TOYEAR
    SET TOYEAR.PENDING = TOYEAR.PENDING - amount
    WHERE toyear.username = :NEW.username;
    END IF;
END;