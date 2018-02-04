DROP TABLE Ship CASCADE CONSTRAINTS;
CREATE TABLE Ship (
    ship_id number(9),
    ship_name varchar2(100) not null unique,
    isApproved number(9) default 0,
    fuel_level float(12) default 0,
    CONSTRAINT ship_id_pk PRIMARY KEY (ship_id)

);

DROP TABLE Pilot CASCADE CONSTRAINTS;
CREATE TABLE Pilot (
    pilot_id number(9),
    pilot_name varchar2(100) unique not null,
    pilot_password varchar2(100) not null,
    CONSTRAINT pilot_id_pk PRIMARY KEY (pilot_id)
);
commit;
DROP TABLE PilotAndShip CASCADE CONSTRAINTS;
CREATE TABLE PilotAndShip (
    ship_id number(9) not null,
    pilot_id number(9) not null,
    
    CONSTRAINT pilotAndShip_pk PRIMARY KEY (ship_id),
    CONSTRAINT ship_id_fk FOREIGN KEY (ship_id)
        REFERENCES ship (ship_id) ON DELETE CASCADE,  
    CONSTRAINT pilot_id_fk FOREIGN KEY (pilot_id)
        REFERENCES pilot (pilot_id) ON DELETE CASCADE
);

ALTER TABLE SHIP
MODIFY isApproved float;

INSERT INTO PILOTANDSHIP
VALUES(1004,1);
INSERT INTO PILOTANDSHIP
VALUES(1003,2);
INSERT INTO PILOTANDSHIP
VALUES(1001,1);

INSERT INTO PILOT
VALUES (1, 'Leia Organa', 'leia');
INSERT INTO PILOT
VALUES (2, 'Chewbacca', 'chewy');
INSERT INTO PILOT
VALUES (3, 'Rey', 'rey');
INSERT INTO PILOT
VALUES (4, 'Luke', 'luke');
INSERT INTO PILOT
VALUES (5, 'Jar jar binks', 'jar jar');

SELECT SHIP.SHIP_ID,
SHIP.SHIP_NAME ,
SHIP.ISAPPROVED ,
SHIP.FUEL_LEVEL FROM SHIP
JOIN PILOT
ON pilotandship.pilot_id = PILOT.PILOT_ID;
DROP VIEW shipswithpilots;

DROP VIEW shipswithoutpilots;
CREATE VIEW shipswithoutpilots AS
SELECT * FROM ship
where ship_id in (
SELECT distinct pilotandship.SHIP_ID from pilotandship
inner join pilot
on pilot.pilot_id = pilotandship.pilot_id);
select * from shipswithpilots;

DROP PROCEDURE isShipApproved;
CREATE OR REPLACE PROCEDURE isShipApproved(shipId IN number, approval OUT float)
IS
BEGIN
    SELECT isApproved INTO approval FROM SHIP WHERE ship_id = shipId;
END;
DECLARE
    idnum number(9);
    approval number(9);
BEGIN
    idnum := 1003;
    isShipApproved(idnum, approval);
    DBMS_OUTPUT.PUT_LINE('Ship: ' || idnum || ' approval status: ' || approval);
END;


CREATE OR REPLACE PROCEDURE getFuel(shipId IN number, addFuel IN number, fuelLevel OUT float)
IS
BEGIN
    select fuel_level INTO fuelLevel from Ship where ship_id = shipId;
    fuelLevel := fuelLevel + addFuel;
    IF fuelLevel > 10000
    THEN
        fuelLevel := 10000;
    END IF;
    setFuel(shipId, fuelLevel);
END;
commit;

CREATE OR REPLACE PROCEDURE setFuel(shipId IN number, newFuel IN FLOAT)
IS
BEGIN
    UPDATE Ship set fuel_level = newFuel WHERE ship_id = shipId;
END;

CREATE OR REPLACE PROCEDURE useFuel(shipId IN number, useFuel IN number, fuelLevel OUT float)
IS
BEGIN
    select fuel_level INTO fuelLevel from Ship where ship_id = shipId;
    fuelLevel := fuelLevel - useFuel;
    IF fuelLevel < 0
    THEN
        fuelLevel := 0;
    END IF;
    setFuel(shipId, fuelLevel);
END;


DECLARE
    input number(6);
    addFuel float(12);
BEGIN
    addFuel := 100;
    input := 1001;
    setFuel(input, addFuel);
END;

DECLARE
    input number(6);
    addFuel number(9);
    results float(12);
BEGIN
    input := 1003;
    addFuel := 101000;
    getFuel(input, addFuel, results);
END;

DECLARE
    input number(6);
    addFuel number(6);
    results float(12);
BEGIN
    input := 1003;
    addFuel := 900;
    useFuel(input, addFuel, results);
END;


commit;