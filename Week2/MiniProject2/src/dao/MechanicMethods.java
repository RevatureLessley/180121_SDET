package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import controller.Controller;
import util.Connections;

public class MechanicMethods {
	final static Logger logger = Logger.getLogger(MechanicMethods.class);
	
	public static void mechanicLogin(Scanner scan) {
		//String s;
		System.out.println("What is the mechanic username?");
		if (scan.nextLine().equals("mechanic")) {
			logger.debug("mechanic entered username successfully");
			System.out.println("What is the password?");
			if(scan.nextLine().equals("mechanic")) {
				logger.debug("mechanic entered password successfully");
				Controller.mechanicMenu(scan);
			}
			else {
				logger.debug("mechanic password was unsuccessful");
				Controller.mainMenu();
			}
		}
		else {
			logger.debug("mechanic username was incorrect");
			Controller.mainMenu();
		}
	}
	
	public static void showAllData() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Connection conn = Connections.getConnection();
			String sql = "SELECT * FROM ALLDATA";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String approved = rs.getInt("isApproved") == 1 ? "Approved" : "Not approved";
				System.out.println(rs.getString("PILOT_NAME") + " " + 
						rs.getString("Ship_NAME") + " " + 
						approved);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);	
			close(rs);
		}
		

	}
	
}
