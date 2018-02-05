CREATE OR REPLACE PROCEDURE get_approved_ships(pilotid IN NUMBER, cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR --Cursor is like a stream, you OPEN it FOR some QUERY.
    SELECT * FROM ship
    where ship_id in (
    SELECT distinct pilotandship.SHIP_ID from pilotandship
    inner join pilot
    on pilotid = pilotandship.pilot_id)
    AND ISAPPROVED = 1;
END;

DECLARE
    app_pilot_cursor SYS_REFCURSOR;
    ship_id ship.ship_id%TYPE; --Make the datatype match THAT of the employees.column's datatype.
    ship_name ship.ship_name%TYPE;
    isApproved ship.isapproved%TYPE;
    fuel_level ship.fuel_level%TYPE;
BEGIN
    get_approved_ships(2, app_pilot_cursor); --Have our cursor represent the cursor for employees
    LOOP
        FETCH app_pilot_cursor INTO ship_id, ship_name, isApproved, fuel_level;
        EXIT WHEN app_pilot_cursor%NOTFOUND; --%NOTFOUND does not exist until there are no records left.
        DBMS_OUTPUT.PUT_LINE(ship_id || ' ' || ship_name || ' ' || isApproved || ' ' || fuel_level);
    END LOOP;
END;

UPDATE ship
SET isApproved = 1;
