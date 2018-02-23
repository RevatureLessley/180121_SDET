package com.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.beans.Reimbursement;
import com.project.beans.Tuition;
import com.project.util.CloseStreams;
import com.project.util.Connections;

public class ReimbursementDaoImp implements ReimbursementDao {

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

	@Override
	public boolean UpdateReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return false;
	}

}
