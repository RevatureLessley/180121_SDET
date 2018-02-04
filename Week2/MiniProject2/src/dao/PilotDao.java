package dao;

import java.util.List;

import beans.Pilot;

public interface PilotDao {
	public List<Pilot> getAllPilots();
	
	public boolean logInPilot();
	
	public Pilot selectPilotByName(String name);
	
	public int updatePilotByName(Pilot ship);
}
