
SELECT * FROM SHIP
WHERE SHIP.ISAPPROVED = 1;

SELECT distinct pilot.pilot_id from PILOT
JOIN PILOTANDSHIP
ON pilot.pilot_id = pilotandship.pilot_id
WHERE pilot.pilot_id = 3;

CREATE OR REPLACE VIEW APPROVEDSHIPSOFPILOT AS
SELECT ship_name, isapproved, fuel_level FROM SHIP
JOIN PILOTANDSHIP
ON ship.ship_id = pilotandship.ship_id;

SELECT * FROM APPROVEDSHIPSOFPILOT
WHERE pilot_id = 3;

DELETE FROM PILOT
WHERE PILOT_PASSWORD = 'connor';
commit;