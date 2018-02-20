package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.userend.AssociateAccount;
import com.util.Connections;

public class AssociateAccountDaoImp implements AssociateAccountDao {

	public int insertAssociateAccount(AssociateAccount user) throws SQLException {
	
		PreparedStatement ps = null;

		Connection conn = Connections.getConnection();

			String sql = "UPDATE Associates SET SupervisorRef = ?, UserName = ?, PW = ?, Phone = ?, Email = ?"
						+ "WHERE AssociateId = ? AND LastName = ? AND FirstName = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getSupervisorRef());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getAssociateID());
			ps.setString(7, user.getLastName());
			ps.setString(8, user.getFirstName());
		
			// INSERT INTO Associates (SupervisorRef, LastName, FirstName, UserName, PW, Phone, Email) VALUES(?,?,?,?,?,?,?)
			int result = ps.executeUpdate();
			close(ps);
			close(conn);
			return result;
	
	}
	
	// add supervisorRef to this get method (and Department ID eventually)
	
		public AssociateAccount getUN_PW(AssociateAccount user) throws SQLException {
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection conn = Connections.getConnection(); 
				
				String sql = "SELECT UserName, PW, AssociateId, Balance_Available, SupervisorRef FROM Associates WHERE UserName = ? AND PW = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getPassword());
				rs = ps.executeQuery();
				
				while(rs.next()) {
				AssociateAccount Acc = new AssociateAccount(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
				
				close(ps);
				close(rs);
				close(conn);
				
				return Acc;
				}
			
			return null;
		}	
		
	

}
