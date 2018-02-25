package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import com.revature.beans.Attachments;
import com.revature.util.Bridge;

public class AttachmentDaoImpl implements AttachmentDao {

	public int totatlAttachments() {
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT COUNT(*) FROM ATTACHMENTS";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {count = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(stmt);close(rs);}
		return count;
	}
	
	
	@Override
	public int insertAttachment(Attachments a) {
		PreparedStatement ps = null;
		FileInputStream in = null;
		
		List<File> list = a.getAttachments();
		Iterator<File> it = list.listIterator(0);
		
		while(it.hasNext()) {
			File file = (File)it.next();
		try(Connection conn = Bridge.connect()){
			String sql = "INSERT INTO ATTACHMENTS VALUES(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			in = new FileInputStream(file);
			
			ps.setInt(1, totatlAttachments()); //attachment_id
			ps.setInt(2, a.getRei_id()); //rei_id
			ps.setBinaryStream(3,in,(int)file.length());
			ps.setString(4, file.getName());
			ps.executeUpdate();
			
		}catch(FileNotFoundException e){e.printStackTrace();}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);}
		}
		return 1;
	}


	@Override
	public int getCountById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cost = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT COUNT(*) FROM ATTACHMENTS WHERE REI_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {cost = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		return cost;
	}


	
}
