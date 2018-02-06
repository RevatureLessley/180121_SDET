package com.revaturebank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.revaturebank.systemclass.Admin;
import com.revaturebank.systemclass.UserAccount;
import com.revaturebank.util.CloseStreams;
import com.revaturebank.util.Connections;

public class AdminDAOClass implements AdminDAO {

		

	@Override
	public Admin authenticate(String username, String password) throws SQLException {
		Statement stmt=null;
		ResultSet rs =null;
		try(Connection conn= Connections.getConnection()){ 
			String sql="SELECT * FROM ADMINS WHERE USERNAME = '"+ username +"' AND PASSWORD ='" + password+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
		  if (rs.next()) {
           Admin ad= new Admin();
           ad.setSsNumber(rs.getString(1));
           ad.setFirsName(rs.getString(2));
           ad.setLastName(rs.getString(3));
           ad.setEmail(rs.getString(4));
           ad.setPhone(rs.getString(5));
           ad.setAdminId(rs.getString(6));
           ad.setUsername(rs.getString(7));
           ad.setPassword(rs.getString(8));
           
           
           return ad;
		  }else return null;
        
       }        
       finally {
    	   CloseStreams.close(stmt);
			   CloseStreams.close(rs);
       }
	}

	@Override
	public boolean deleteAdmin(String adminId, String username) {
		PreparedStatement ps=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			String sql="DELETE admin WHERE ACCOUNT_NUM=? AND USERNAME=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, adminId);
			ps.setString(2, username);
			bool=ps.executeUpdate();
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool !=1);
	}

	@Override
	public boolean insertAdmin(String adminId, String username, String password,String ssn,String firstName, String lastName,
			String email, String phone) {
		PreparedStatement ps=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			String sql="INSERT INTO ADMIN(USERNAME,PASSWORD, SSN,FIRST_NAME,LAST_NAME,EMAIL,PHONE,ADMIN_ID) VALUES (?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, ssn);
	    	ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, email);
			ps.setString(7, phone);
			ps.setString(8, adminId);
			bool=ps.executeUpdate();
			
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool !=1);
		}

	@Override
	public boolean updateAdmin(String username, String password) {
		PreparedStatement ps=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			String sql="UPDATE ADMIN SET USERNAME=?, PASSWORD=?"
					+ "WHERE USERNAME=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, username);
			bool=ps.executeUpdate();
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool !=1);
	}

	@Override
	public UserAccount search(String username) throws SQLException {
		Statement stmt=null;
		ResultSet rs =null;
		try(Connection conn= Connections.getConnection()){ 
			String sql="SELECT SSN,FIRST_NAME,LAST_NAME,EMAIL,PHONE,USERNAME,PASSWORD "
					+ " FROM USERACCOUNT_TEMP"
					+ " WHERE USERNAME = '"+username+"'";
			
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if (rs.next()) {
           UserAccount ua= new UserAccount();
           ua.setSsNumber(rs.getString(1));
           ua.setFirsName(rs.getString(2));
           ua.setLastName(rs.getString(3));
           ua.setEmail(rs.getString(4));
           ua.setPhone(rs.getString(5));
           ua.setUsername(rs.getString(6));
           ua.setPassword(rs.getString(7));
           
           
           return ua;
			}else return null;
        
       }        
       finally {
    	   CloseStreams.close(stmt);
			   CloseStreams.close(rs);
       }
	}
	@Override
	public boolean deleteUserAccount(String accountNumber, String username) {
		PreparedStatement ps=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			String sql="DELETE USERACCOUNT WHERE ACCOUNT_NUM=? AND USERNAME=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, accountNumber);
			ps.setString(2, username);
			bool=ps.executeUpdate();
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool !=1);
		
	}

	@Override
	public ArrayList<UserAccount> allAccountRequest() throws SQLException {
		Statement stmt=null;
		ResultSet rs =null;
		
		try(Connection conn= Connections.getConnection()){ 
			String sql="SELECT SSN,FIRST_NAME,LAST_NAME,EMAIL,PHONE,USERNAME,PASSWORD "
					+ " FROM USERACCOUNT_TEMP";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			ArrayList<UserAccount> users =new ArrayList<>();
           while(rs.next()){
           
           UserAccount ua= new UserAccount(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4),rs.getString(6),rs.getString(7));
           
           users.add(ua);
           }
        return users;
		}
       finally {
    	   CloseStreams.close(stmt);
			   CloseStreams.close(rs);
       }
	}
	@Override
	public boolean insertUserAccount(UserAccount ua) {
		PreparedStatement ps=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			String sql="INSERT INTO USERACCOUNT(USERNAME,PASSWORD, SSN,FIRST_NAME,LAST_NAME,EMAIL,PHONE,ACCOUNT_NUM)"
					+ " VALUES (?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, ua.getUsername());
			ps.setString(2, ua.getPassword());
			ps.setString(3, ua.getSsNumber());
	    	ps.setString(4, ua.getFirsName());
			ps.setString(5, ua.getLastName());
			ps.setString(6, ua.getEmail());
			ps.setString(7, ua.getPhone());
			ps.setString(8, ua.getAccountNumber());
			bool=ps.executeUpdate();
			
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool !=1);
	}

}
