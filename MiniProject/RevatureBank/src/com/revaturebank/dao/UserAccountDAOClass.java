package com.revaturebank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.revaturebank.systemclass.Transaction;
import com.revaturebank.systemclass.UserAccount;
import com.revaturebank.util.CloseStreams;
import com.revaturebank.util.Connections;

public class UserAccountDAOClass implements UserAccountDAO {
	
	@Override
	public boolean requestUserAccount(String username, String password, String ssn, String firstName, String lastName,
			                                   String email, String phone) {
		PreparedStatement ps=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			String sql="INSERT INTO USERACCOUNT_TEMP(USERNAME,PASSWORD, SSN,FIRST_NAME,LAST_NAME,EMAIL,PHONE) VALUES (?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, ssn);
	    	ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, email);
			ps.setString(7, phone);
			
			bool=ps.executeUpdate();
			
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool !=1);
	}

	@Override
	public boolean updateUserAccountPI(String username ,String ssn, String firstName, String lastName, String email, String phone) {
		PreparedStatement ps=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			String sql="UPDATE USERACCOUNT SET SSN=? ,FIRSTNAME=?,LASTNAME=?,EMAIL=?,PHONE=?"
					+ "WHERE USERNAME=?";
			ps=conn.prepareStatement(sql);
			ps.setString(6 , username);
			ps.setString(1, ssn);
	    	ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, email);
			ps.setString(5, phone);
			bool=ps.executeUpdate();
			
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool ==1);	}

	@Override
	public boolean updateUserAccuntUP(String u, String username, String password) {
		PreparedStatement ps=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			String sql="UPDATE USERACCOUNT SET USERNAME=?, PASSWORD=?"
					+ "WHERE Account_num=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, u);
			bool=ps.executeUpdate();
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool ==1);	
		}

	@Override
	public UserAccount authenticate(String username, String password) throws SQLException{
		Statement stmt=null;
		ResultSet rs =null;
		try(Connection conn= Connections.getConnection()){ 
			String sql="SELECT * FROM USERS WHERE USERNAME = '"+username +"' AND PASSWORD ='" +password+"'";
			
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			if (rs.next()) {
           UserAccount ua= new UserAccount();
           ua.setSsNumber(rs.getString(1));
           ua.setFirsName(rs.getString(2));
           ua.setLastName(rs.getString(3));
           ua.setEmail(rs.getString(4));
           ua.setPhone(rs.getString(5));
           ua.setAccountNumber(rs.getString(6));
           ua.setUsername(rs.getString(7));
           ua.setPassword(rs.getString(8));
           ua.setBalanceAccount(rs.getDouble(9));
           
           return ua;
			}else return null;
        
       }        
       finally {
    	   CloseStreams.close(stmt);
			   CloseStreams.close(rs);
       }
	}
	@Override
	public boolean search(String username) throws SQLException {
		Statement stmt=null;
		ResultSet rs =null;
		try(Connection conn= Connections.getConnection()){ 
			String sql="SELECT * FROM USERS WHERE USERNAME = '"+username+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if (rs.next()) return true;
			else return false;
			}
	    finally {
 	          CloseStreams.close(stmt);
			   CloseStreams.close(rs);
               }
		
	}
	

	@Override
	public boolean balanceUpdate(String accountNum, Double amount,String descr, Double balance) {
		CallableStatement stmt=null;
		int bool=0;
		try(Connection conn= Connections.getConnection()){
			stmt = conn.prepareCall("{call transations_account(?,?,?,?)}");
			stmt.setString(1, accountNum);
			stmt.setDouble(2, amount);
			stmt.setString(3, descr);
			stmt.setDouble(4, balance);
			stmt.execute();
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return (bool ==1);
	}

	@Override
	public ArrayList<Transaction> getTransactionsAccount(String u_A) throws SQLException {
	    	
		Statement stmt=null;
		ResultSet rs =null;
		
		try(Connection conn= Connections.getConnection()){ 
			String sql="SELECT TRANS_NUM,TRANS_DATE, AMOUNT,DESCRIPTION "
					+ " FROM ACCOUNT_TRANS WHERE REF2='"+ u_A+"'";
			
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			ArrayList<Transaction> trans =new ArrayList<>();
           while(rs.next()){
           
           Transaction ua= new Transaction(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4));
           
          trans.add(ua);
           }
        return trans;
		}
       finally {
    	   CloseStreams.close(stmt);
			   CloseStreams.close(rs);
       }
	}
   	
		
		
	

	

}
