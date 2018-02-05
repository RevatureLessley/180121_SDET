package dao;

import static util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import beans.Ship;
import util.Connections;

public class ShipDaoImpl implements ShipDao {

	@Override
	//tested and it works
	public List<Ship> getAllShips() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Ship> ships = new ArrayList<>();
		try{
			Connections.getConnection();

			try{

				Connection conn = Connections.getConnection();
				String sql = "SELECT * FROM SHIP";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //Executing queries, brings back resultsets
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
		
		return ships;
	}

	@Override
	public boolean approveShip(Ship ship) {
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try{

			Connection conn = Connections.getConnection();
			String sql = "UPDATE ship SET isApproved = 1 WHERE ship_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ship.getShip_id());
			
			stmt.executeUpdate(); //Executing queries, brings back resultsets
			System.out.println(ship.getName() + " is now approved for flight!");
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			close(stmt);
			close(rs);
		}
	}
	
	@Override
	public boolean unapproveShip(Ship ship) {
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try{

			Connection conn = Connections.getConnection();
			String sql = "UPDATE ship SET isApproved = 0 WHERE ship_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ship.getShip_id());
			
			stmt.executeUpdate(); //Executing queries, brings back resultsets
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			close(stmt);
			close(rs);
		}
	}

	@Override
	public boolean deleteShipByName(Ship ship) {
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try{

			Connection conn = Connections.getConnection();
			String sql = "DELETE FROM SHIP WHERE SHIP_ID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ship.getShip_id());
			
			stmt.executeUpdate(); //Executing queries, brings back resultsets
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			close(stmt);
			close(rs);
		}
	}

	
	@Override
	public boolean addShip(Ship ship) {
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		ShipDao shipDao = new ShipDaoImpl();
		List<Ship> ships = shipDao.getAllShips();
		List<Integer> ids = new ArrayList<>();
		for (Ship s : ships) {
			ids.add(s.getShip_id());
		}
		int randomId = -1;
		while(true) {
			randomId = (int) (Math.random() * 10000);
			if(ids.contains(randomId) == false) {
				break;
			}
		}
		ship.setShip_id(randomId);
		ship.setApproved(false);
		
		try{

			Connection conn = Connections.getConnection();
			String sql = "INSERT INTO Ship (ship_id, ship_name, isApproved, fuel_level)" + 
						"VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ship.getShip_id());
			stmt.setString(2, ship.getName());
			Integer isApproved = ship.isApproved() ? 1 : 0;
			stmt.setInt(3,  isApproved);
			stmt.setDouble(4, ship.getFuel_level());
			
			stmt.executeUpdate(); //Executing queries, brings back resultsets
			System.out.println("Your ship " + ship.getName() + " has been added to the garage and has a fuel level of " + ship.getFuel_level());
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			close(stmt);
			close(rs);
		}
		
	}

	@Override
	//adds fuel
	//tested and it works
	public boolean addFuel(Ship ship, int volume) {
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try{

			Connection conn = Connections.getConnection();
/*			String sql = "DECLARE" + 
					"    input number(6);" + 
					"    results float(12);" + 
					"BEGIN" + 
					"    input := ?;" + 
					"    getFuel(input, ?);" + 
					"    DBMS_OUTPUT.PUT_LINE(results);" + 
					"END;";*/
/*			String sql = "DECLARE\r\n" + //attempted with stored function, didnt work 
					"    fuelLevel number;\r\n" + 
					"BEGIN\r\n" + 
					"    ? := get_fuel_level(1002," +  blob + ");\r\n" + 
					"END;";*/

			String sql = "{call getFuel(? , ? ,?)}";
			
	        CallableStatement cs = conn.prepareCall(sql);
	        
	        cs.setInt(1, ship.getShip_id());
	        cs.setInt(2, volume);
	        int results = -1;
	        cs.registerOutParameter(3, Types.FLOAT);

//			cs.setInt(3, results );
	        rs = cs.executeQuery();
	        Float f = cs.getFloat(3);
	        ship.setFuel_level(f);
/*	        cs.registerOutParameter(2, Types.FLOAT);

	        boolean hadResults = cs.execute();

	        //
	        // Process all returned result sets
	        //
	        Float result = -1F;
	        while (hadResults) {
	            rs = cs.getResultSet();
	            result = cs.getFloat(2);

	            hadResults = cs.getMoreResults();
	        }
	        
	        System.out.println(result);*/
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			close(stmt);
			close(rs);
		}
	}

	@Override
	//Uses fuel
	public boolean flyShip(Ship ship, int volume) {
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try{

			Connection conn = Connections.getConnection();
			
			String sql1 = "{call isShipApproved(? , ?)}";
	        CallableStatement cs = conn.prepareCall(sql1);
	        cs.setInt(1,ship.getShip_id());
	        cs.registerOutParameter(2, Types.FLOAT);
	        
	        rs = cs.executeQuery();

	        Float a = cs.getFloat(2);
	        
	        Boolean isApproved = a == 1 ? true : false;
	        if(!isApproved) {
	        	System.out.println("This spaceship must still be inspected by a mechanic!");
	        	return false;
	        }
	        else {
	        	System.out.println("\nYour ship has been approved by the mechanic and is ready for flight\n");
	        }
	        


			String sql = "{call useFuel(? , ? ,?)}";
			
	        cs = conn.prepareCall(sql);
	        
//	        cs.registerOutParameter(1, Types.FLOAT);
	        cs.setInt(1, ship.getShip_id());
	        cs.setInt(2, volume);
	        cs.registerOutParameter(3, Types.FLOAT);

	        rs = cs.executeQuery();
	        Float f = cs.getFloat(3);
	        ship.setFuel_level(f);
	        try{
	        	Thread.sleep(3000);
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        System.out.println("Welcome back! Your ship burned " + volume + " gallons of fuel and now has " + f + " gallons in the tank");
/*	        cs.registerOutParameter(2, Types.FLOAT);

	        boolean hadResults = cs.execute();

	        //
	        // Process all returned result sets
	        //
	        Float result = -1F;
	        while (hadResults) {
	            rs = cs.getResultSet();
	            result = cs.getFloat(2);

	            hadResults = cs.getMoreResults();
	        }
	        
	        System.out.println(result);*/
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			close(stmt);
			close(rs);
		}
	}

	@Override
	public List<Ship> shipsWithoutPilots() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Ship> ships = new ArrayList<>();
		try{
			Connections.getConnection();

			/*
			 * Try-With-Resources will close any streams you create within the
			 * parenthesis' of the try block, once the try-catch is finished.
			 */
			try {
				//First step in using the Statement object, is create a SQL query to 
				//use for the database;
				Connection conn = Connections.getConnection();
				String sql = "SELECT * FROM shipswithoutpilots";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //Executing queries, brings back resultsets
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
		return ships;
	}



/*	@Override
	public void printAllShips(List<Ship> ships) {
		for (Ship ship : ships) {
			System.out.println(ship);
		}
	}*/


}
