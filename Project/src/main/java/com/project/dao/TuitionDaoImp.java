package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.beans.Tuition;
import com.project.services.EmployeeServices;
import com.project.services.ReimbursementServices;
import com.project.services.TuitionServices;
import com.project.util.CloseStreams;
import com.project.util.Connections;

public class TuitionDaoImp implements TuitionDao {

	@Override
	public List<Tuition> getAllTuition() {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<Tuition> tuitions = new ArrayList<>();
		
		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM ALLTUITION";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
		
			while (rs.next()) {
				tuitions.add(new Tuition(rs.getInt(1),
						rs.getString(2),
						rs.getDate(3).toLocalDate(),
						rs.getDate(4).toLocalDate(),
						rs.getString(5),
						rs.getString(6),
						rs.getDouble(7),
						rs.getString(8),
						rs.getString(9),
						rs.getBytes(10),
						rs.getInt(11),
						rs.getDouble(12) ));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(stmt);
			CloseStreams.close(rs);
		}

		return tuitions;
	}

	@Override
	public boolean addTuition(Tuition t) {
		
		CallableStatement stmt = null;
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call INSERT_TUITION(?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, t.getUsername());
			stmt.setDate(2, Date.valueOf(t.getStart_date()));
			stmt.setDate(3, Date.valueOf(t.getEnd_date()));
			stmt.setString(4, t.getLocation());
			stmt.setString(5, t.getDescription());
			stmt.setDouble(6, t.getCost());
			stmt.setString(7, t.getGrading_formate());
			stmt.setString(8, t.getEvent_type());
			stmt.setBytes(9, t.getAttachment());
			stmt.setInt(10, EmployeeServices.getInital_approval(t.getUsername()));
			stmt.setDouble(11, ReimbursementServices.getProject(t.getUsername(), t.getEvent_type(), t.getCost()));
			stmt.execute();
		}catch(SQLException e1){
			e1.printStackTrace();
			return false;
		}finally{
			CloseStreams.close(stmt);
		}
		return true;
		
	}

	@Override
	public boolean deleteTuitonByUsername(String u) {
		CallableStatement stmt = null;
		try (Connection conn = Connections.getConnection()) {
			stmt = conn.prepareCall("{call DELETE_TUITION_USERNAME(?)}");
			stmt.setString(1,u);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			CloseStreams.close(stmt);
		}

		return true;
	}



	@Override
	public boolean deleteTuitonByTuitionId(int i) {
		CallableStatement stmt = null;
		try (Connection conn = Connections.getConnection()) {
			stmt = conn.prepareCall( "DELETE TUITION WHERE T_ID = ?");
			stmt.setInt(1,i);
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
