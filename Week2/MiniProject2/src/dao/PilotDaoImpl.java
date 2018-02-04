package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

			/*
			 * Try-With-Resources will close any streams you create within the
			 * parenthesis' of the try block, once the try-catch is finished.
			 */
			try{
				//First step in using the Statement object, is create a SQL query to 
				//use for the database;
				Connection conn = Connections.getConnection();
				String sql = "SELECT * FROM USERS";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //Executing queries, brings back resultsets
				Pilot pilot = null;
				while(rs.next()){
					
					pilot.setName(rs.getString("name"));
					//pilot.setPilot_id( (int) rs.getDouble("pilot_id"));
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
	public boolean logInPilot() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int updatePilotByName(Pilot ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pilot selectPilotByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}






}
