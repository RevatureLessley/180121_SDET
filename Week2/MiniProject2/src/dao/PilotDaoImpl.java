package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Pilot;
import beans.Ship;
import util.Connections;

public class PilotDaoImpl implements PilotDao {

	@Override
	public List<Pilot> getAllPilots() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Pilot> pilots = new ArrayList<>();
		try{
			Connections.getConnection();

			try{

				Connection conn = Connections.getConnection();
				String sql = "SELECT * FROM Pilot";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //Executing queries, brings back resultsets
				Pilot pilot;
				while(rs.next()){
					pilot = new Pilot();
					pilot.setName(rs.getString("pilot_name"));
					pilot.setPassword(rs.getString("pilot_password"));
					pilot.setId(rs.getInt("pilot_id"));
					pilots.add(pilot);
	
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				close(stmt);
				close(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pilots;
	}

	@Override
	public void logInPilot(List<Pilot> pilots, Scanner scan) {
		System.out.println(pilots);

		System.out.println("A pilot must login before being allowed to fly a spaceship.\n Enter your name:\n");
		String input = scan.nextLine();
		Pilot match = null;
		for (Pilot p : pilots) {
			if (p.getName().equals(input)) {
				match = p;
				break;
			}
		}
		if(match != null) {
			System.out.println("Enter the password for: " + match.getName());
			input = scan.nextLine();
			if(match.getPassword().equals(input)) {
				System.out.println("Welcome, " + match.getName());
				chooseShip(match, scan);
				
			}
			else {
				System.out.println("Sorry, wrong password");	

			}
		}
		else {
			System.out.println("No pilot found with that name");
		}

	}
	
	public void chooseShip(Pilot pilot, Scanner scan) {
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		List<Ship> ships = new ArrayList<>();
		try{

			Connection conn = Connections.getConnection();
			String sql = "SELECT * FROM ship WHERE ship_id in (SELECT distinct pilotandship.ship_id from pilotandship inner join pilot on pilotandship.pilot_id = ? ) AND isApproved = 1";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,pilot.getId());
	        
	        rs = stmt.executeQuery();
	        
	        Ship ship = null;
			while(rs.next()){
				ship = new Ship();
				ship.setName(rs.getString("ship_name"));
				Integer approved = rs.getInt("isApproved");
				Boolean isApproved = approved == 1 ? true : false;
				ship.setApproved(isApproved);
				ship.setFuel_level(rs.getFloat("fuel_level"));
				ship.setShip_id(rs.getInt("ship_id"));
				ships.add(ship);

			}
			System.out.println("These are the APPROVED ships in " + pilot.getName() + "'s garage");
	        System.out.println("What ship would like to fly?");
	        
	        System.out.println(ships);
	        
	        String input = scan.nextLine();
	        for(Ship s : ships) {
	        	if (s.getName().equals(input)) {
	        		ShipDao dao = new ShipDaoImpl();
	        		dao.flyShip(s, (int) (Math.random()*s.getFuel_level()));
	        		break;
	        	}
	        }
	        
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(stmt);
			close(rs);
		}	
	}
	

	@Override
	public void assignShipToPilot(Ship ship, Scanner scan) {
		PilotDao pDao = new PilotDaoImpl();
		List<Pilot> pilots = pDao.getAllPilots();
		System.out.println(pilots);
		System.out.println("Enter the name of the  pilot you would like to assign the ship to");
		String choosePilot = scan.nextLine();
		Boolean foundPilot = false;
		Pilot pilot = new Pilot();
		for (Pilot p : pilots) {
			if(p.getName().equals(choosePilot)) {
				foundPilot = true;
				pilot = p;
			}
		}
		if (!foundPilot) {
			System.out.println("No match found");

			return;
		}	
		
		PreparedStatement stmt = null;
		try {
			Connection conn = Connections.getConnection();
			String sql = "INSERT INTO PILOTANDSHIP (SHIP_ID, PILOT_ID) VALUES ( ? , ? ))";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ship.getShip_id());
			stmt.setInt(2,pilot.getId());
			stmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);			
			
		}
	}

	@Override
	public void createPilot(Scanner scan) {
		Pilot p = new Pilot();
		System.out.println("Enter your name: ");
		String input = scan.nextLine();
		p.setName(input);
		System.out.println("Enter your password: ");
		p.setPassword(scan.nextLine());
		PilotDao pDao = new PilotDaoImpl();

		List<Pilot> pilots = pDao.getAllPilots();
		List<Integer> ids = new ArrayList<>();
		for(Pilot pilot : pilots) {
			ids.add(pilot.getId());
		}
		int randomId = -1;
		while(true) {
			randomId = (int) (Math.random() * 10000);
			if(ids.contains(randomId) == false) {
				break;
			}
		}
		p.setId(randomId);
		
		PreparedStatement stmt = null;
		try {
			Connection conn = Connections.getConnection();
			String sql = "INSERT INTO PILOT (PILOT_ID, PILOT_NAME, PILOT_PASSWORD) VALUES ( ? , ?, ? )";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, p.getId());
			stmt.setString(2, p.getName());
			stmt.setString(3, p.getPassword());
			stmt.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);			
			
		}
		
	}


}
