package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.request.RequestTR;
import com.util.Connections;

public class RequestDaoImp implements RequestDao{

	@Override
	public boolean addRequest(RequestTR tr){
		
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "INSERT INTO ReimbursmentRequest (EmployeeId, FirstName, LastName, Phone, Email, Amount_Requested, Balance_Available, Event_Type, Event_Justification, Grading_Format, Passing_Grade_Provided, Supervisor_Approval, Department_Head_Approval, BenCo_Approval) " +
					   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
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


				if(ps.executeUpdate() == 1)
					return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return false;
	}

}
