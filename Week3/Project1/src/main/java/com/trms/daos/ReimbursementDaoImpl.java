package com.trms.daos;

import static com.trms.util.CloseStreams.close;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.trms.beans.AddedInfo;
import com.trms.beans.Reimbursement;
import com.trms.services.EmployeeService;
import com.trms.util.Connections;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private final static Logger logger = Logger.getLogger(ReimbursementDaoImpl.class);

	@Override
	public int insertReimbursement(Reimbursement r) {
		PreparedStatement ps = null;
		r.setNextApprovalId(EmployeeService.getReportsTo(r.getEmpId()));
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "INSERT INTO reimbursements (reimburse_emp_id, reimburse_cost, reimburse_projreimb, reimburse_desc, reimburse_passgrade, "
					+ "reimburse_workmissed, reimburse_datetime, reimburse_workjustify, reimburse_approvelvl, reimburse_urgent, reimburse_approveid,"
					+ "reimburse_event_id, reimburse_center_id, reimburse_format_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getEmpId());
			ps.setFloat(2, r.getCost());
			ps.setFloat(3, r.getProjectedReimb());
			ps.setString(4, r.getDescription());
			ps.setFloat(5, r.getPassGrade());
			ps.setInt(6, r.getWorkDaysMissed());
			ps.setDate(7, r.getDate());
			ps.setString(8, r.getWorkJustification());
			ps.setInt(9, r.getApprLvl());
			ps.setInt(10, r.getUrgent());
			ps.setInt(11, r.getNextApprovalId());
			ps.setInt(12, r.getEventId());
			ps.setInt(13, r.getCenterId());
			ps.setInt(14, r.getFormatId());
			ps.executeUpdate();	
			
			List<File> l = r.getAttachments();
			if(!l.isEmpty()) {
				logger.info("insertReimbursement() : list not empty");
				Iterator<File> i = l.iterator();
				int rID = getReimburseByEmpId(r.getEmpId());
				while(i.hasNext()) {
					insertAttachment((File)i.next(), rID);
				}
			}
			
			
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		return 0;
	}
	
	public int insertAttachment(File f, int r_id) {
		PreparedStatement ps = null;
		FileInputStream in = null;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "INSERT into reimburseattachments (at_reimburse_id, reimburse_attach, attach_name) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			in = new FileInputStream(f);
			ps.setInt(1, r_id);
			ps.setBinaryStream(2, in, (int)f.length());
			ps.setString(3, f.getName());
			logger.info("insertAttachment() : before executeUpdate");
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} catch(FileNotFoundException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(in);
		}
		return 0;
	}

	@Override
	public int getReimburseByEmpId(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int reimburseId = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String query = "SELECT reimburse_id FROM (SELECT * FROM reimbursements " + 
					"ORDER BY reimburse_timestamp DESC) WHERE reimburse_emp_id = ? AND ROWNUM = 1";
			ps = conn.prepareStatement(query);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				reimburseId = rs.getInt(1);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return reimburseId;
	}

	@Override
	public List<Reimbursement> getPersonalReimb(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> lr = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT reimburse_id, reimburse_datetime, event_name, center_name, format_type, " + 
					"reimburse_cost, reimburse_projreimb, reimburse_approved " + 
					"FROM reimbursements a, eventtypes b, gradingformats c, learningcenters d " + 
					"WHERE a.reimburse_emp_id = ? AND a.reimburse_event_id = b.event_id AND a.reimburse_format_id = c.format_id " + 
					"AND a.reimburse_center_id = d.center_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				lr.add(new Reimbursement(rs.getInt(1), rs.getString(3), rs.getString(5), rs.getString(4),
						rs.getFloat(6), rs.getFloat(7), rs.getDate(2), rs.getInt(8)));
				boolean f = getNumberAttachments(lr.get(lr.size()-1).getReimburseId()) > 0;
				lr.get(lr.size()-1).setFiles(f);
			}			
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		return lr;
	}

	@Override
	public int getNumberAttachments(int rId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int totalFiles = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT count(*) FROM reimburseattachments WHERE at_reimburse_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				totalFiles = rs.getInt(1);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return totalFiles;
	}

	@Override
	public List<Reimbursement> getReimburse(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> lr = new ArrayList<>();
		
		// TODO make it so that approved or denied reimbursements don't appear in approver views
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT reimburse_id, reimburse_datetime, event_name, center_name, format_type, " + 
					"reimburse_cost, reimburse_projreimb, reimburse_approved " + 
					"FROM reimbursements a, eventtypes b, gradingformats c, learningcenters d " + 
					"WHERE a.reimburse_approveid = ? AND a.reimburse_event_id = b.event_id AND a.reimburse_format_id = c.format_id " + 
					"AND a.reimburse_center_id = d.center_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				lr.add(new Reimbursement(rs.getInt(1), rs.getString(3), rs.getString(5), rs.getString(4),
						rs.getFloat(6), rs.getFloat(7), rs.getDate(2), rs.getInt(8)));
				boolean f = getNumberAttachments(lr.get(lr.size()-1).getReimburseId()) > 0;
				lr.get(lr.size()-1).setFiles(f);
			}			
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		return lr;
	}

	@Override
	public int getEmpIdByReimburse(int rId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int empId = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT reimburse_emp_id FROM reimbursements WHERE reimburse_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				empId = rs.getInt(1);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return empId;
	}

	@Override
	public int updateApproved(int rId, int response) {
		PreparedStatement ps = null;
		int result = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE reimbursements SET reimburse_approved = ? WHERE reimburse_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, response);
			ps.setInt(2, rId);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return result;
	}

	@Override
	public int setApproveId(int rId, int empId) {
		PreparedStatement ps = null;
		int result = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE reimbursements SET reimburse_approveid = ? WHERE reimburse_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			ps.setInt(2, rId);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return result;
	}

	@Override
	public int setApproveLvl(int rId, int newLvl) {
		PreparedStatement ps = null;
		int result = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE reimbursements SET reimburse_approvelvl = ? WHERE reimburse_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, newLvl);
			ps.setInt(2, rId);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		logger.debug("setApproveLvl() : result=" + result); 
		return result;
	}

	@Override
	public Reimbursement getReimbBy(int rId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reimbursement r = new Reimbursement();
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT b.event_name, d.format_type, c.center_name, a.reimburse_cost, a.reimburse_projreimb, "
					+ "a.reimburse_desc, a.reimburse_grade, a.reimburse_passgrade, a.reimburse_workmissed, "
					+ "a.reimburse_datetime, a.reimburse_workjustify, a.reimburse_inforeq, a.reimburse_emp_id, a.reimburse_approveid, a.reimburse_urgent "
					+ "FROM reimbursements a, eventtypes b, learningcenters c, gradingformats d "
					+ "WHERE a.reimburse_id = ? AND a.reimburse_event_id = b.event_id "
					+ "AND a.reimburse_center_id = c.center_id AND a.reimburse_format_id = d.format_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				r.setEventStr(rs.getString(1));
				r.setFormatStr(rs.getString(2));
				r.setCenterStr(rs.getString(3));
				r.setCost(rs.getFloat(4));
				r.setProjectedReimb(rs.getFloat(5));
				r.setDescription(rs.getString(6));
				r.setGrade(rs.getFloat(7));
				r.setPassGrade(rs.getFloat(8));
				r.setWorkDaysMissed(rs.getInt(9));
				r.setDate(rs.getDate(10));
				r.setWorkJustification(rs.getString(11));
				r.setNextInfoReq(rs.getInt(12));
				r.setEmpId(rs.getInt(13));
				r.setNextApprovalId(rs.getInt(14));
				r.setUrgent(rs.getInt(15));
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return r;
	}

	@Override
	public int updateReqInfo(int rId, int empId) {
		PreparedStatement ps = null;
		int result = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE reimbursements SET reimburse_inforeq = ? WHERE reimburse_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			ps.setInt(2, rId);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	public int insertAddedInfo(AddedInfo ai) {
		PreparedStatement ps = null;
		int result = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "INSERT INTO reimburseaddedinfo (in_reimburse_id, info_added_by_emp, addinfo_reimburse) VALUES (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ai.getInfoRid());
			ps.setInt(2, ai.getInfoEmpId());
			ps.setString(3, ai.getInfoMessage());
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<AddedInfo> getAddedInfoBy(int rId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AddedInfo> ls = new ArrayList<>(); //created an info bean
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT a.info_id, a.addinfo_reimburse, b.emp_fname, b.emp_lname FROM reimburseaddedinfo a, employees b " + 
					"WHERE a.in_reimburse_id = ? AND a.info_added_by_emp = b.emp_id ORDER BY a.info_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ls.add(new AddedInfo(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(2)));
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return ls;
	}

	@Override
	public int updateGrade(int rId, float grade) {
		PreparedStatement ps = null;
		int result = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE reimbursements SET reimburse_grade = ? WHERE reimburse_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setFloat(1, grade);
			ps.setInt(2, rId);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return result;
	}

	@Override
	public int updateProjReimb(int rId, float projReimb) {
		PreparedStatement ps = null;
		int result = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE reimbursements SET reimburse_projreimb = ? WHERE reimburse_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setFloat(1, projReimb);
			ps.setInt(2, rId);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return result;
	}
	
	

}
