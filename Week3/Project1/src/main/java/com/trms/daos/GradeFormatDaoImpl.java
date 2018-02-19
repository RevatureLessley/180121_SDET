package com.trms.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.trms.beans.GradeFormat;
import com.trms.util.Connections;
import static com.trms.util.CloseStreams.close;

public class GradeFormatDaoImpl implements GradeFormatDao {
	private final static Logger logger = Logger.getLogger(GradeFormatDaoImpl.class);
	
	@Override
	public List<GradeFormat> getGradeFormats() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<GradeFormat> lgf = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM gradingformats";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				lgf.add(new GradeFormat(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		return lgf;
	}

}
