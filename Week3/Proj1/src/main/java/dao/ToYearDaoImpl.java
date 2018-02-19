package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ToYear;
import util.Connections;

public class ToYearDaoImpl implements ToYearDao {

	@Override
	public ToYear getRecord(String username) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ToYear ty = new ToYear();

		try{
			Connection conn = Connections.getConnection();
			String sql = "SELECT * FROM TOYEAR "
					+ "WHERE username = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery(); //Executing queries, brings back resultsets
		
			if (rs == null) {
				return null;
			}
			
			rs.next();
			
			ty.setMaximum(rs.getInt("maximum"));
			ty.setPending(rs.getInt("pending"));
			ty.setAwarded(rs.getInt("awarded"));
			ty.setUsername(username);
			
			return ty;
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
				return null;
			}finally{
				close(stmt);
				close(rs);
			}

	}

	@Override
	public int getMaxClaim(ToYear ty, int amount) {
		int available = ty.getMaximum() - ty.getAwarded() - ty.getPending();
		
		return amount > available ? available : amount;
	}



}
