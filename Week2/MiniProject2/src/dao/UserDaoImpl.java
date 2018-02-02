package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import util.Connections;

public class UserDaoImpl implements UserDao{
	
	@Override
	public List<User> getAllUser() {
		Statement stmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
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
				
				while(rs.next()){

								String what = rs.getString("userName");
								String password = rs.getString("userPassword");
								Double d = rs.getDouble("bank_balance");
								System.out.println(what + " dgdfgd " + password + "dfgdfgdf  " + d);
				}

				System.out.println("sdfsdf");
				
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
		
		return users;
	}

//	@Override
	public static void insertUser(String userName, String userPassword, Double bank_balance) {
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try{

			Connection conn = Connections.getConnection();
			String sql = "INSERT INTO USERS (userName, userPassword, bank_balance)" + 
						"VALUES (?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, userPassword);
			stmt.setDouble(3, bank_balance);
			
			stmt.executeUpdate(); //Executing queries, brings back resultsets
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(stmt);
			close(rs);
		}
	}
}
