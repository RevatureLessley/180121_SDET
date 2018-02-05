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
DECLARE
    input number(6);
    addFuel number(9);
    results float(12);
BEGIN
    input := 1003;
    addFuel := 101000;
    getFuel(input, addFuel, results);
END;

CREATE OR REPLACE PROCEDURE setFuel(shipId IN number, newFuel IN FLOAT)
IS
BEGIN
    UPDATE Ship set fuel_level = newFuel WHERE ship_id = shipId;
END;
DECLARE
    input number(6);
    addFuel float(12);
BEGIN
    addFuel := 100;
    input := 1001;
    setFuel(input, addFuel);
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
    addFuel number(6);
    results float(12);
BEGIN
    input := 1003;
    addFuel := 900;
    useFuel(input, addFuel, results);
END;

