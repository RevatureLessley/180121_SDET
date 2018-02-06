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
    
--    CONSTRAINT pilotAndShip_pk PRIMARY KEY (ship_id),
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

commit;