package dao;

import java.util.List;

import beans.Ship;

public interface ShipDao {
	public List<Ship> getAllShips();
	
	public boolean addShip(Ship ship);
	
	public boolean deleteShipByName(Ship ship);
		
	public boolean addFuel(Ship ship, int volume);//adds fuel
	
	public boolean flyShip(Ship ship, int volume); //uses fuel
	
	public boolean approveShip(Ship ship);
	
	public boolean unapproveShip(Ship ship);
	
	public List<Ship> shipsWithoutPilots();
	
//	public int createRandomId(List<Ship> ships);
	
//	public void printAllShips(List<Ship> ships);
	
}
