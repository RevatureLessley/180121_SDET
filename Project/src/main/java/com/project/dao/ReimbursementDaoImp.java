package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.beans.Reimbursement;
import com.project.util.CloseStreams;
import com.project.util.Connections;

public class ReimbursementDaoImp implements ReimbursementDao {
	
	/**
	 * Getting all Reimbursement information from database
	 */
	@Override
	public List<Reimbursement> getAllReimbursement() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM ALLREIMBURSEMENT";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
		
			while (rs.next()) {
				reimbursements.add(new Reimbursement(rs.getString(1),
						rs.getDouble(2),
						rs.getDouble(3)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(stmt);
			CloseStreams.close(rs);
		}

		return reimbursements;
	}
	
	/**
	 * Updating reimbursement data in database
	 */

	@Override
	public boolean UpdateReimbursement(Reimbursement r) {
		CallableStatement stmt = null;
		try (Connection conn = Connections.getConnection()) {
			stmt = conn.prepareCall("{ call UPDATE_REIMBURSEMENT(?,?,?) }");
			stmt.setDouble(2, r.getPending());
			stmt.setDouble(3, r.getAwarded());
			stmt.setString(1, r.getUsername());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			CloseStreams.close(stmt);
		}

		return true;
	}

}
