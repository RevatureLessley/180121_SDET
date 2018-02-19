package com.trms.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.trms.daos.EventTypeDao;
import com.trms.util.Connections;

public class EventTypeDaoImpl implements EventTypeDao {
	private final static Logger logger = Logger.getLogger(EventTypeDaoImpl.class);

	@Override
	public List<EventType> getEventTypes() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EventType> let = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM eventtypes";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				let.add(new EventType(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return let;
	}

}
