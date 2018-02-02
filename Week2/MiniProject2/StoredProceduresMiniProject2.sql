CREATE OR REPLACE PROCEDURE deleteUser_procedure
IS
BEGIN
    DELETE FROM USERS
    WHERE userName = 'User3';
END;

BEGIN
    deleteUser_procedure();
END;

CREATE OR REPLACE PROCEDURE addUser_procedure(userN IN varchar2 , userP IN varchar2 , bankB IN FLOAT)
IS
BEGIN
    INSERT INTO USERS (userName, userPassword, bank_balance)
    VALUES (userN , userP , bankB);
END;

BEGIN
    addUser_procedure('bobbbbbbbert', 'bobby', 65.5);
END;

ALTER TABLE USERS
MODIFY USERNAME varchar2(100);