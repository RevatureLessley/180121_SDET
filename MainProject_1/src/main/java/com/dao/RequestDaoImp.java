package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	@Override
	public Integer getBalance(Integer price, Integer employeeID) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer balanceAvail = null;

		
		try (Connection conn = Connections.getConnection()) {
			

			String sql = "SELECT Balance_Available FROM Associates"
					+ " WHERE AssociateID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeID);
			rs = ps.executeQuery(); 

			while (rs.next()) {
				balanceAvail = rs.getInt(1);
			}
			
			return balanceAvail - price;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		
		return null;
	}

	@Override
	public Integer setReimbursment(Integer employeeID) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer balanceAvail = null;

		
		try (Connection conn = Connections.getConnection()) {
			

			String sql = "SELECT Balance_Available FROM Associates"
					+ " WHERE AssociateID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeID);
			rs = ps.executeQuery(); 

			while (rs.next()) {
				balanceAvail = rs.getInt(1);
			}
			
			return balanceAvail;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		
		return null;
		
	}

	@Override
	public void adjustBalance(Integer amount_Requested, Integer employeeId) {
		
		PreparedStatement ps = null;
		
		try (Connection conn = Connections.getConnection()) {
		
			String sql = "UPDATE Associates SET Balance_Available = Balance_Available - ? WHERE AssociateId = ?";
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, amount_Requested);
			ps.setInt(2, employeeId);
			ps.executeUpdate(); 

	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		
	}

	@Override
	public List<RequestTR> getAllAssociateRequests(Integer accountID) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RequestTR> req = new ArrayList<>();

		
		try (Connection conn = Connections.getConnection()) {
			

			String sql = "SELECT RequestId, Amount_Requested, Event_Type FROM ReimbursmentRequest WHERE EmployeeId = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, accountID);
			rs = ps.executeQuery(); 

			while (rs.next()) {
				req.add(new RequestTR(rs.getInt(1), rs.getInt(2), rs.getString(3)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		
		return req;
	}


	@Override
	public String checkRequestStatus(Integer requestId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RequestTR> req = new ArrayList<>();
		
		Integer super_Approval = null;
		Integer dept_Approval = null;
		Integer benCo_Approval = null;
		Integer grade_provided = null;
		
		try (Connection conn = Connections.getConnection()) {
			

			String sql = "SELECT Supervisor_Approval, Department_Head_Approval, BenCo_Approval, Passing_Grade_Provided FROM ReimbursmentRequest WHERE RequestId = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, requestId);
			rs = ps.executeQuery(); 

			while (rs.next()) {
				super_Approval = rs.getInt(1);
				dept_Approval = rs.getInt(2);
				benCo_Approval = rs.getInt(3);
				grade_provided = rs.getInt(4);
				
			if(super_Approval == 1 && dept_Approval == 1 && benCo_Approval == 1 && grade_provided == 1) 
					return "Approved";
			
			else if (super_Approval != -1 && dept_Approval != -1 && benCo_Approval != -1 && grade_provided != -1) {
				
				if (grade_provided == 0) 
					return "Grade Needed";
				else 
					return "Pending";
			}
			
			else if (super_Approval != -1 || dept_Approval != -1 || benCo_Approval != -1 || grade_provided != -1) 
				return "Declined";
		}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		
		return null;
	}
	

	@Override
	public String getReqDoc(Integer requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertLetterGrade(Integer requestId, String letterGradeFormat) {
		
		PreparedStatement ps = null;
		
		try (Connection conn = Connections.getConnection()) {
		
			String sql = "UPDATE ReimbursmentRequest SET LetterGrade = ? "
					+ "WHERE requestId = ? AND Grading_Format LIKE '%Letter%'";
					
			ps = conn.prepareStatement(sql);
			ps.setString(1, letterGradeFormat);
			ps.setInt(2, requestId);
			
			//System.out.println(ps.executeUpdate());
			if (ps.executeUpdate() == 1) {
				checkPassingGrade(requestId);
				return true;
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return false;
	}

	@Override
	public boolean insertNumberGrade(Integer requestId, Integer numberGradeFormat) {
		
		PreparedStatement ps = null;
		
		try (Connection conn = Connections.getConnection()) {
		
			String sql = "UPDATE ReimbursmentRequest SET NumberGrade = ? "
					+ "WHERE requestId = ? AND Grading_Format LIKE '%Percentage%'";
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, numberGradeFormat);
			ps.setInt(2, requestId);
			
			if (ps.executeUpdate() == 1) {
			
				checkPassingGrade(requestId);
				return true;
			
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return false;
	}

	private void checkPassingGrade(Integer requestId) {

		PreparedStatement ps = null;
		
		try (Connection conn = Connections.getConnection()) {
		
			String sql = "UPDATE ReimbursmentRequest SET Passing_Grade_Provided = 1 "
					+ "WHERE requestId = ? "
					+ "AND lower(LetterGrade) LIKE '%a%' OR "
					+ "lower(LetterGrade) LIKE '%b%' OR "
					+ "lower(LetterGrade) LIKE '%c%' OR "
					+ "lower(LetterGrade) LIKE '%d%' OR "
					+ "NumberGrade BETWEEN 65 AND 100";
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, requestId);
			
			ps.executeUpdate();
			checkFailingGrade(requestId);
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

	}

	private void checkFailingGrade(Integer requestId) {
		
		PreparedStatement ps = null;
		
		try (Connection conn = Connections.getConnection()) {
		
			String sql = "UPDATE ReimbursmentRequest SET Passing_Grade_Provided = -1 "
					+ "WHERE requestId = ? "
					+ "AND lower(LetterGrade) LIKE '%f%' OR NumberGrade BETWEEN 0 AND 64";
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, requestId);
			
			ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
	}
		
	
	

}
