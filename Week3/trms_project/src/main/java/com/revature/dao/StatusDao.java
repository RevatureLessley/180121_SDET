package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Reimbursements;
import com.revature.util.ConnectionUtil;

public class StatusDao {
	public void DeptHeadApprove(int rid) {
		PreparedStatement prepstate;
		String sql = "update rstatus set depthead = 'Approve' where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  rid);
			ResultSet rs = prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void BenCoApprove(int rid) {
		PreparedStatement prepstate;
		String sql = "update rstatus set BenCo = 'Approve' where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  rid);
			ResultSet rs = prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void SupApprove(int rid) {
		PreparedStatement prepstate;
		String sql = "update rstatus set DSUP = 'Approve' where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  rid);
			ResultSet rs = prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void DeptHeadDeny(int rid) {
		PreparedStatement prepstate;
		String sql = "update rstatus set depthead = 'Deny' where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  rid);
			ResultSet rs = prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void BenCoDeny(int rid) {
		PreparedStatement prepstate;
		String sql = "update rstatus set benco = 'Deny' where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  rid);
			ResultSet rs = prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void SupDeny(int rid) {
		PreparedStatement prepstate;
		String sql = "update rstatus set DSUP = 'Deny' where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  rid);
			ResultSet rs = prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void createStatus(int rid, int supervisor, int deptHead) {
		PreparedStatement prepstate;
		String sql;
		if(supervisor == 0) {
			sql = "insert into rstatus values(?, ?, 'Approve', 'Pending', 'Pending')";
		} else if(deptHead == 0) {
			sql = "insert into rstatus values(?, ?, 'Approve', 'Approve', 'Pending')";
		}
		else {
			sql = "insert into rstatus values(?, ?, 'Pending', 'Pending', 'Pending')";
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  rid);
			prepstate.setInt(2, rid);
			ResultSet rs = prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public String checkStatus(int rid) {
		PreparedStatement ps;
		String sql = "select * from rstatus where rid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String dsup = rs.getString("dsup");
				String benco = rs.getString("benco");
				String deptHead = rs.getString("depthead");
				if(dsup.equals("Approve") && benco.equals("Approve") && deptHead.equals("Approve")) {
					return "Approve";
				} else if(dsup.equals("Deny") || benco.equals("Deny") || deptHead.equals("Deny")) {
					return "Deny";
				} else {
					return "Pending";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Pending";
	}
}