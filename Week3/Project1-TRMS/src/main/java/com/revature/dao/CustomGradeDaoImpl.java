package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.CustomGrade;
import com.revature.beans.Reimbursement;
import com.revature.util.Bridge;

public class CustomGradeDaoImpl implements CustomGradeDao {

	@Override
	public void addCustomFormat(CustomGrade cust) {
		
		PreparedStatement ps = null;
		
		try(Connection conn = Bridge.connect()){
			String sql = "INSERT INTO CUSTOM_GRADING VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,cust.getReid()); //rei_id
			ps.setInt(2,cust.getEmpid()); //emp_id
			ps.setString(3,cust.getFname()); // First Name
			ps.setString(4, cust.getLname()); // Last Name
			ps.setString(5,cust.getCustomFormat()); // Date of Reimbursement
			ps.setString(6,cust.getFormatDesc()); // time of reimbursement
			ps.executeUpdate();
		}catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		
	}

	@Override
	public List<CustomGrade> getCustomFormatById(int reid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustomGrade> customGradeList = new ArrayList<CustomGrade>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = " SELECT * FROM CUSTOM_GRADING WHERE REI_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reid);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				customGradeList.add(new CustomGrade (
						rs.getInt(1), // rei_id
						rs.getInt(2), //emp_id
						rs.getString(3), // First name
						rs.getString(4), // Last name
						rs.getString(5), // Date Of Event 
						rs.getString(6) // Time Of event
						));
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return customGradeList;
	}

	@Override
	public List<CustomGrade> getAllCustomFormatById(int emp_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustomGrade> customGradeList = new ArrayList<CustomGrade>();
		
		try(Connection conn = Bridge.connect()){
			
			String sql = " SELECT * FROM CUSTOM_GRADING WHERE EMP_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp_id);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				customGradeList.add(new CustomGrade (
						rs.getInt(1), // rei_id
						rs.getInt(2), //emp_id
						rs.getString(3), // First name
						rs.getString(4), // Last name
						rs.getString(5), // Date Of Event 
						rs.getString(6) // Time Of event
						));
				
				
				
				}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		
		return customGradeList;
	}

	
}
