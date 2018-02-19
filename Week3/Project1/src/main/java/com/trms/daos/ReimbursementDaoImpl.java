package com.trms.daos;

import static com.trms.util.CloseStreams.close;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		return 0;
	}
	
	public int insertAttachment(File f, int r_id) {
		PreparedStatement ps = null;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "INSERT into reimburseattachments (at_reimburse_id, reimburse_attach) VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			FileInputStream in = new FileInputStream(f);
			
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} catch(FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

}
