package com.trms.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.trms.beans.LearningCenter;
import com.trms.util.Connections;

public class LearningCenterDaoImpl implements LearningCenterDao {
	private final static Logger logger = Logger.getLogger(LearningCenterDaoImpl.class);

	@Override
	public List<LearningCenter> getLearningCenters() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LearningCenter> llc = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM learningcenters";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				llc.add(new LearningCenter(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return llc;
	}

}
