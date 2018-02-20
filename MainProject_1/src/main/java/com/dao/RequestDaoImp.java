package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.request.RequestTR;
import com.util.Connections;

public class RequestDaoImp implements RequestDao{

	@Override
	public boolean addRequest(RequestTR tr){
		
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "INSERT INTO ReimbursmentRequest (EmployeeId, FirstName, LastName, Phone, Email, Amount_Requested, Balance_Available, Event_Type, Event_Justification, Grading_Format, Passing_Grade_Provided, Supervisor_Approval, Department_Head_Approval, BenCo_Approval, SupervisorRef) " +
					   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tr.getEmployeeId());
			ps.setString(2, tr.getFirstName());
			ps.setString(3, tr.getLastName());
			ps.setString(4, tr.getPhone());
			ps.setString(5, tr.getEmail());
			ps.setInt(6, tr.getAmount_Requested());
			ps.setInt(7, tr.getBalance_Available());
			ps.setString(8, tr.getEvent_Type());
			ps.setString(9, tr.getEvent_Justification());
			ps.setString(10, tr.getGrading_Format());
			ps.setInt(11, tr.getPassing_Grade_Provided());
			ps.setInt(12, tr.getSupervisor_Approval());
			ps.setInt(13, tr.getDepartment_Head_Approval());
			ps.setInt(14, tr.getBenCo_Approval());
			ps.setInt(15, tr.getSupervisorRef());


				if(ps.executeUpdate() == 1)
					return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return false;
	}

	@Override
	public List<RequestTR> getAllSuperVisorRequests(Integer SupervisorRef) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RequestTR> req = new ArrayList<>();

		
		try (Connection conn = Connections.getConnection()) {
			

			String sql = "SELECT RequestId, EmployeeId, FirstName, LastName, Phone, Email, Amount_Requested, Balance_Available, Event_Type, Event_Justification, Grading_Format FROM ReimbursmentRequest"
					+ " WHERE SupervisorRef = ? AND Supervisor_Approval = 0";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, SupervisorRef);
			rs = ps.executeQuery(); 

			while (rs.next()) {
				req.add(new RequestTR(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		System.out.println(req);
		return req;
	}

	@Override
	public boolean superApprove(Integer requestID) {

		PreparedStatement ps = null;
		
		try (Connection conn = Connections.getConnection()) {
		
			String sql = "UPDATE ReimbursmentRequest SET Supervisor_Approval = 1 WHERE RequestId = ?";
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, requestID);
			if(ps.executeUpdate() != 0) {
				close(ps);
				return true;
			}; 

			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return false;
		
	}

	@Override
	public void superDecline(Integer requestID) {
		
		PreparedStatement ps = null;
		
		try (Connection conn = Connections.getConnection()) {
		
			String sql = "UPDATE ReimbursmentRequest SET Supervisor_Approval = -1 WHERE RequestId = ?";
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, requestID);
			ps.executeUpdate(); 

	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
	}

	@Override
	public void superDocRequest(String docsNeeded, Integer requestID, Integer referenceID) {
		
		PreparedStatement ps = null;
		
		try (Connection conn = Connections.getConnection()) {
		
			String sql = "INSERT INTO RequestDocumentations(RequestId, Authorizer_Title, Autherizor_Reason) VALUES(?,?,?)";
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, requestID);
			ps.setString(2, "Supervisor");
			ps.setString(3, docsNeeded);
			
			ps.executeUpdate(); 

	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
	}		
	

}
