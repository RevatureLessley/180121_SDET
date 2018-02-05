DROP VIEW shipswithpilots;
CREATE VIEW shipswithpilots AS
SELECT * FROM ship
where ship_id in (
SELECT distinct pilotandship.SHIP_ID from pilotandship
inner join pilot
on pilot.pilot_id = pilotandship.pilot_id);
select * from shipswithpilots;

DROP VIEW shipswithoutpilots;
CREATE VIEW shipswithoutpilots AS
SELECT * FROM ship
where ship_id not in (
SELECT distinct pilotandship.SHIP_ID from pilotandship
inner join pilot
on pilot.pilot_id = pilotandship.pilot_id);
select * from shipswithoutpilots;

SELECT * FROM ship
where ship_id in (
SELECT distinct pilotandship.SHIP_ID from pilotandship
inner join pilot
on 1 = pilotandship.pilot_id)
AND ISAPPROVED = 1;



UPDATE ship
SET isapproved = 1
WHERE ship_id = 1001;