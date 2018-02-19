package com.trms.daos;

import static com.trms.util.CloseStreams.close;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.trms.beans.Reimbursement;
import com.trms.util.Connections;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private final static Logger logger = Logger.getLogger(ReimbursementDaoImpl.class);

	@Override
	public int insertReimbursement(Reimbursement r) {
		PreparedStatement ps = null;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "INSERT INTO reimbursements (reimburse_emp_id) VALUES (?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getEmpId());
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
			String sql = "INSERT into reimburseattachments (at_reimburse_id, reimburse_attach) VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			in = new FileInputStream(f);
			ps.setInt(1, r_id);
			ps.setBinaryStream(2, in, (int)f.length());
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
			String query = "SELECT reimburse_id FROM reimbursements WHERE reimburse_emp_id = ?";
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

}
