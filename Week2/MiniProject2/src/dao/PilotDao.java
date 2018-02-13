package dao;

import java.util.List;
import java.util.Scanner;

import beans.Pilot;
import beans.Ship;

public interface PilotDao {
	public List<Pilot> getAllPilots();
		
	public void logInPilot(List<Pilot> pilots, Scanner scan);
	
	public void chooseShip(Pilot pilot, Scanner scan);
	
//	public Pilot selectPilotByName(String name);
	
//	public int updatePilotByName(Pilot ship);
	
	public void assignShipToPilot(Ship ship, Scanner scan);
	
	public void createPilot(Pilot p, Scanner scan);
	
}
