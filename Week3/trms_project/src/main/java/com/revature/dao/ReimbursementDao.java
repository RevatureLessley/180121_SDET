package com.revature.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
          
import com.revature.beans.Reimbursements;
import com.revature.util.ConnectionUtil;

public class ReimbursementDao {
	//return all reimbursements in the system
	public List<Reimbursements> getAllReimbursements(){
		PreparedStatement prepstate;
		List<Reimbursements> reimburse = new ArrayList<>();
		String sql = "select * from reimbursements r join employees e on e.eid=r.eid";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			ResultSet rs = prepstate.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("rid");
				int eid = rs.getInt("eid");
				double amount = rs.getDouble("reimbursement");
				double cost = rs.getDouble("cost");
				String status = rs.getString("status");
				String rtype = rs.getString("rtype");
				String description = rs.getString("description");                        
				String firstname = rs.getString("firstname");
				firstname += " "+ rs.getString("lastname");
				Reimbursements r = new Reimbursements(rid, eid, cost, amount, rtype, description, status, firstname);
				reimburse.add(r);
			}
			rs.close();
			prepstate.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
		return reimburse;
	}
	//returns employee specific reimbursements
	public List<Reimbursements> getMyReimbursements(int eid){
		PreparedStatement prepstate; 
		List<Reimbursements> reimburse = new ArrayList<>();
		String sql = "select * from reimbursements where eid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1, eid);
			ResultSet rs = prepstate.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("rid");
				double amount = rs.getDouble("Reimbursement");
				double cost = rs.getDouble("cost");
				String status = rs.getString("status");
				String rtype = rs.getString("rtype");
				String description = rs.getString("description");                        
				
				Reimbursements r = new Reimbursements(rid, eid, cost, amount, rtype, description, status);
				reimburse.add(r);
			}
			rs.close();
			prepstate.close();
		} catch (Exception ex){
			ex.printStackTrace();
			ex.getMessage();
		}
		return reimburse;
	}
	//return reimbursements of people who report to them
	public List<Reimbursements> getSupervisorReimbursements(int eid){
		PreparedStatement prepstate;
		List<Reimbursements> reimburse = new ArrayList<>();
		String sql = "select r.rid, r.reimbursement, r.cost, r.status, r.rtype, r.description, r.eid, e.firstname, e.lastname from reimbursements r join employees e on e.eid = r.eid join employees emp on emp.eid = e.supervisor where e.supervisor=?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  eid);
			ResultSet rs = prepstate.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("rid");
				double amount = rs.getDouble("reimbursement");
				double cost = rs.getDouble("cost");
				String status = rs.getString("status");
				String rtype = rs.getString("rtype");
				String description = rs.getString("description"); 
				int reid = rs.getInt("eid");
				String firstname = rs.getString("firstname");
				firstname += " " +rs.getString("lastname");
				Reimbursements r = new Reimbursements(rid, reid, cost, amount, rtype, description, status, firstname);
				reimburse.add(r);
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
		return reimburse;
	}
	public List<Reimbursements> getDeptHeadReimbursements(int eid){
		PreparedStatement prepstate;
		List<Reimbursements> reimburse = new ArrayList<>();
		String sql = "select r.rid, r.reimbursement, r.cost, r.status, r.rtype, r.description, r.eid, e.firstname, e.lastname from reimbursements r join employees e on e.eid = r.eid join employees emp on emp.eid = e.departmenthead where e.departmenthead=?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  eid);
			ResultSet rs = prepstate.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("rid");
				double amount = rs.getDouble("reimbursement");
				double cost = rs.getDouble("cost");
				String status = rs.getString("status");
				String rtype = rs.getString("rtype");
				String description = rs.getString("description"); 
				int reid = rs.getInt("eid");
				String firstname = rs.getString("firstname");
				firstname += " " +rs.getString("lastname");
				Reimbursements r = new Reimbursements(rid, reid, cost, amount, rtype, description, status, firstname);
				reimburse.add(r);
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
		return reimburse;
	}
	//Creating reimbursement input from user should be cost type and description 
	// rid status and reimbursement generated by system
	public void createReimbursement(int eid, double cost, String rtype, String description, String location, String grade) {
		PreparedStatement prepstate;
		double costMultiplier = 1.0;
		switch (rtype) {
		case "University Course":
			costMultiplier = 0.8;
			break;
		case "Seminar":
			costMultiplier = 0.6;
			break;
		case "Certification":
			costMultiplier = 1.0;
			break;
		case "Technical Training":
			costMultiplier = 0.9;
			break;
		case "Other":
			costMultiplier = 0.3;
			break;
		}
		double reimbursement = cost*costMultiplier;
		String sql = "insert into reimbursements(eid, cost, reimbursement, rtype, description, rlocation, gradingformat, status) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  eid);
			prepstate.setDouble(2, cost);
			prepstate.setDouble(3, reimbursement);
			prepstate.setString(4, rtype);
			prepstate.setString(5,  description);
			prepstate.setString(6, location);
			prepstate.setString(7, grade);
			prepstate.setString(8,  "Pending");
			prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	//Called when employee wishes to see stage of reimbursement
	public String[] getDetailStatus(int rid) {
		PreparedStatement prepstate;
		String sql = "select * from rstatus where rid = ?";
		String ret[] = new String[3];
		try(Connection conn =  ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  rid);
			ResultSet rs = prepstate.executeQuery();
			while(rs.next()) {
				String supStatus = rs.getString("dsup");
				String deptStatus = rs.getString("depthead");
				String bencoStatus = rs.getString("benco");
				ret[0] = supStatus;
				ret[1] = deptStatus;
				ret[2] = bencoStatus;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	//If reimbursement is overlooked for more than a week it is auto approved by 
	//supervisor and dept head but not benco
	public void autoApprove(int rid) {
		CallableStatement cs;
		String sql =  "autoapprove(?)";
		try(Connection conn = ConnectionUtil.getConnection()){
			cs = conn.prepareCall(sql);
			cs.setInt(1, rid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public int getMostRecentEntry() {
		PreparedStatement ps;
		int rid = 0;
		String sql = "select max(rid) from reimbursements";
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				rid = rs.getInt("max(rid)");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} return rid;
	}
	public void deleteReimb(int rid) {
		PreparedStatement ps;
		String sql = "delete from reimbursements where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rid);
			ResultSet rs = ps.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void updateStatus(String status, int rid) {
		PreparedStatement ps;
		String sql = "update reimbursements set status = ? where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1,  status);
			ps.setInt(2, rid);
			ResultSet rs = ps.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}