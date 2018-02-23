package test;

import static util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Connections;

public class Test {

	public static void main(String[] args) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		
/*		
		try{
			Connection conn = Connections.getConnection();
			String sql = "call appR(?,?,?)"; //id,level,comment
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, 1);
			stmt.setInt(2, 3);
			stmt.setString(3, "Work related justification");
			stmt.execute(); //Executing queries, brings back resultsets
			
			
			}catch(SQLException e){
//				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}finally{
				close(stmt);
				close(rs);
			}	*/

	}

}
