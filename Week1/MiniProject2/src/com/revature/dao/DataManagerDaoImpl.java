package com.revature.dao;

import com.revature.backend.*;
import com.revature.communication.*;
import com.revature.dao.*;
import com.revature.Users.*;

import static com.revature.communication.CloseStreams.close;

import java.net.StandardSocketOptions;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DataManagerDaoImpl implements DataMangerDao {

	
	
	public DataManagerDaoImpl(){ getAllUsers();};
	
	public static List<User> userList = new ArrayList<>();
	/* *This variable is used to to store the last index of a search through the arraylist.
	 *When a user tries to log in, the array list is traversed, searching for a match for the username,
	 *followed by a check for the correct password, and if the user has been validated by an admin.
	 *If all these checks are true, then a user is allowed to login. However, rather than searching again for
	 *the user in the userList arraylist when the user commits a withdrawl and deposit, the index of the user's postion
	 *in the list is saved so that the it can be accessed in 0(1) time.*/
	private static int lastIndex = 0;
	//This function flushes lastIndex if any of the checks fail. becasue they will not be able to login at that point so their is no
	//need to save the index.
	public static void flushLastIndex () {lastIndex = 0;}
	//This is used to set lastIndex when a user is found.
	protected static void setLastIndex (int a) {lastIndex = a;}
	//This function will return the lastIndex when it is needed to access the arraylist.
	protected static int getLastIndex () {return lastIndex;}
	
	public static int currentUserBits () { return userList.get(getLastIndex()).getBits(); } 
	public static String currentUserName() {return userList.get(getLastIndex()).getUsername();};
	
	@Override
	public boolean checkEmpty() {
		if(totallUser() == 0) {return true;}
		else {return false;}
	}
	
	@Override
	public boolean checkUniqueUser(String u) {

		Statement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT USERNAME FROM USER_LIST";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				if(u.equals( rs.getString(1))) {return true;}
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return false;
	}

	@Override
	public void addUser(String u, String p) {
		
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "INSERT INTO USER_LIST VALUES(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,u);
			ps.setString(2, p);
			ps.setString(3,"NOP");
			ps.setInt(4, 0);
			ps.executeUpdate();
		}catch(SQLException e){e.printStackTrace();}
		finally{userList.add(new User(u,p,false,0));
		close(ps);}
		
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int valUser(String user, String pass) {
		if(totallUser() == 0){return 0;} //No users created yet.
		if(!adminPass(pass)) {return 1;} //Wrong admin password
		if(!checkUniqueUser(user)) {return 2;} //username not found

		ListIterator<User> li = userList.listIterator(0);
		
		
		
			while(li.hasNext()) {
				if(li.next().getUsername().equals(user)) {
					if(!li.previous().getVal()) {
					
					li.next().setVal(true);
					
					PreparedStatement ps = null;
					
					try(Connection conn = Bridge.connect()){			
						String sql = "UPDATE USER_LIST SET VAL = ?" 
								+ "WHERE USERNAME = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, "YEP");
						ps.setString(2, user);
						ps.execute();
						return 3; 
					}catch(SQLException e){e.printStackTrace();}
					finally{close(ps);}
				}else {return 4;}
				}
			}
			
		return -1;
	}

	@Override
	public int checkCredentials(String u, String p) {
		if(totallUser() == 0) {return 0;} //No users in list
		if(adminUser(u) && adminPass(p)) {return 5;} //Admin login confirmed
		if(!checkUniqueUser(u)) {return 1;} //username not found
		
		ListIterator<User> li = userList.listIterator(0);
		
		
		while(li.hasNext()) {
			if(li.next().getUsername().equals(u)) {
				if(!li.previous().getPassword().equals(p)) {flushLastIndex(); return 2;} //Wrong Password
					if(li.next().getVal()) {setLastIndex(li.previousIndex());  return 4;} //SUCCESS
					flushLastIndex();return 3; // Not Validated 
			}
		}
		flushLastIndex();
		return -1;
	}

	@Override
	public List<User> getAllUsers() {
		Statement stmt = null;
		ResultSet rs = null;
		String y = "YEP";
		boolean v = true;
		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM USER_LIST";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()){
				
				if(	y.equals(rs.getString(3))){ v = true;}else {v = false;}
				userList.add(new User(
							rs.getString(1),
							rs.getString(2),
							v
							,
							rs.getInt(4)));
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		
		return userList;
	}

	@Override
	public int totallUser() {
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT COUNT(USERNAME) FROM USER_LIST";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {count = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return count;
	}

	@Override
	public boolean adminUser(String username) {
		Statement stmt = null;
		ResultSet rs = null;
		String check = new String();
		
		try(Connection conn = Bridge.connect()){
			String sql = "SELECT ADMIN_USERNAME FROM ADMIN_TABLE";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {check = rs.getString(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return check.equals(username);
	}

	@Override
	public boolean adminPass(String password) {
		Statement stmt = null;
		ResultSet rs = null;
		String check = new String();
		
		try(Connection conn = Bridge.connect()){
			String sql = "SELECT ADMIN_PASSWORD FROM ADMIN_TABLE";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {check = rs.getString(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return check.equals(password);
	}
	@Override
	public boolean userDeposit(int d) {
		
		boolean v = userList.get(getLastIndex()).makeDeposit(d);
		if(v) {
		PreparedStatement ps = null;
		
		int tranId = 0;
		int newBal = userList.get(getLastIndex()).getBits();
		
		try(Connection conn = Bridge.connect()){			
			String sql = "UPDATE USER_LIST SET BITS = ?" 
					+ "WHERE USERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, newBal);
			ps.setString(2, userList.get(getLastIndex()).getUsername());
			ps.executeUpdate();
			ps = null;
			
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}

		int tran = getTransId();
		updateTran(userList.get(getLastIndex()).getUsername(),tran,d,"D");
		
		return true;}
		else {return false;}
	}
	
	@Override
	public boolean userWithdrawl(int w) {
		boolean v = userList.get(getLastIndex()).makeWithdrawl(w);
		if(v) {
		PreparedStatement ps = null;
		int newBal = userList.get(getLastIndex()).getBits();
		try(Connection conn = Bridge.connect()){			
			String sql = "UPDATE USER_LIST SET BITS = ?" 
					+ "WHERE USERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, newBal);
			ps.setString(2, userList.get(getLastIndex()).getUsername());
			ps.execute();
			
			
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
		int tran = getTransId();
		updateTran(userList.get(getLastIndex()).getUsername(),tran,w,"W");
		
		return true;}
		else {return false;}
	}
	@Override
	public int getTransId() {
		ResultSet rs = null;
		Statement stmt = null;
		int tranId = 0;
		
		try(Connection conn = Bridge.connect()){			
			
			String sql = "SELECT COUNT(TRANS_ID) FROM TRANSACTION_RECORDS";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {tranId = rs.getInt(1);}
			
		}catch(SQLException e){e.printStackTrace();}
		finally{close(rs);}
		return tranId;
	}
	@Override
	public void updateTran(String u, int i, int a, String t) {
		PreparedStatement ps = null;
		try(Connection conn = Bridge.connect()){			
			String sql = "INSERT INTO TRANSACTION_RECORDS VALUES(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ps.setString(2, userList.get(getLastIndex()).getUsername());
			ps.setInt(3, a);
			ps.setString(4, t);
			ps.execute();
			
			
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
	}
	@Override
	public void userHistory (String u) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT * FROM TRANSACTION_RECORDS "
					+ " WHERE USERNAME = ? ";
		
			ps = conn.prepareStatement(sql);
			ps.setString(1, u);
			rs = ps.executeQuery(); 
			
			System.out.println("===============LIST OF ALL TRANSACTIONS FOR " + u +  " ================");
			while(rs.next()){
			System.out.println(	
				
				"TRANSCATION ID: " + rs.getInt(1) + " | " +
				"TRANSACTION USER: " + rs.getString(2) + " | " +
				"TRANSACTION OF: " + rs.getInt(3) + " BITS | " +
				"TRANSACTION TYPE OF: " + rs.getString(4) + " | "
					);
				
				}
			System.out.println("===============LIST OF ALL TRANSACTIONS FOR " + u +  " ================");
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
	}




}
