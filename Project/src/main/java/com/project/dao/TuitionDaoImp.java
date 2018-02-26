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
import com.project.util.CloseStreams;
import com.project.util.Connections;

public class TuitionDaoImp implements TuitionDao {
	
	
	/**
	 * Getting all Tuition form information form database
	 */
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
						rs.getDouble(12),
						rs.getString(13)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(stmt);
			CloseStreams.close(rs);
		}

		return tuitions;
	}

	/**
	 * Adding new Tuition form info to the database
	 */
	@Override
	public boolean addTuition(Tuition t) {
		
		CallableStatement stmt = null;
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call INSERT_TUITION(?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, t.getUsername());
			stmt.setDate(2, Date.valueOf(t.getStart_date()));
			stmt.setDate(3, Date.valueOf(t.getEnd_date()));
			stmt.setString(4, t.getLocation());
			stmt.setString(5, t.getDescription());
			stmt.setDouble(6, t.getCost());
			stmt.setString(7, t.getGrading_formate());
			stmt.setString(8, t.getEvent_type());
			stmt.setBytes(9, t.getAttachment());
			stmt.setInt(10, EmployeeServices.getInital_approval(t));
			stmt.setDouble(11, ReimbursementServices.getProject(t.getUsername(), t.getEvent_type(), t.getCost()));
			stmt.setString(12, t.getFile_type());
			stmt.execute();
		}catch(SQLException e1){
			e1.printStackTrace();
			return false;
		}finally{
			CloseStreams.close(stmt);
		}
		return true;
		
	}
	
	
	/**
	 * Deleting all Tuition form in single employee
	 */
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

	/**
	 * Deleting tuition info by tuition id
	 */

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
	
	/**
	 * Approve a tuition status to next step
	 */

	@Override
	public boolean approveTution(Tuition t) {
		CallableStatement stmt = null;
		try (Connection conn = Connections.getConnection()) {
			stmt = conn.prepareCall( "UPDATE TUITION SET APPROVAL = ? WHERE T_ID = ?");
			stmt.setInt(1,t.getApproval());
			stmt.setInt(2,t.getT_id());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			CloseStreams.close(stmt);
		}

		return true;
	}

	/**
	 * Change tuition state to MoreInfo to get more info about the request 
	 */
	@Override
	public boolean MoreInfo(Tuition t) {
		CallableStatement stmt = null;
		try (Connection conn = Connections.getConnection()) {
			stmt = conn.prepareCall( "UPDATE TUITION SET APPROVAL = ? , DESCRIPTION = ? WHERE T_ID = ?");
			stmt.setInt(1,t.getApproval());
			stmt.setString(2, t.getDescription());
			stmt.setInt(3,t.getT_id());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			CloseStreams.close(stmt);
		}

		return true;
	}
	
	
	/**
	 * Set the Tuition state to Reject after rejected by approver
	 */
	@Override
	public boolean reject(int t_id, String reason) {
		CallableStatement stmt = null;
		try (Connection conn = Connections.getConnection()) {
			stmt = conn.prepareCall( "UPDATE TUITION SET APPROVAL = -2 , DESCRIPTION = ? WHERE T_ID = ?");
			stmt.setString(1, reason);
			stmt.setInt(2,t_id);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			CloseStreams.close(stmt);
		}

		return true;
	}
	
	
	/**
	 * Upload a document to Tuition form info 
	 */
	@Override
	public boolean addFile(Tuition t) {
		CallableStatement stmt = null;
		try (Connection conn = Connections.getConnection()) {
			stmt = conn.prepareCall( "UPDATE TUITION SET FILE_TYPE = ? , ATTACHMENT = ? WHERE T_ID = ?");
			stmt.setString(1, t.getFile_type());
			stmt.setBytes(2, t.getAttachment());
			stmt.setInt(3,t.getT_id());
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
