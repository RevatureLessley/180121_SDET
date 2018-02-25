package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

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
			String sql = "INSERT INTO ATTACHMENTS VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			in = new FileInputStream(file);
			
			ps.setInt(1, totatlAttachments() + 1); //attachment_id
			ps.setInt(2, a.getRei_id()); //rei_id
			ps.setBinaryStream(3,in,(int)file.length());
			ps.setString(4, file.getName());
			ps.setString(5, FilenameUtils.getExtension(file.getName()) );
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


	@Override
	public int getAid(int rei_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int a_id = 0;
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT A_ID FROM ATTACHMENTS WHERE REI_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rei_id);
			rs = ps.executeQuery();
			while(rs.next()) {a_id = rs.getInt(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		return a_id;
	}


	@Override
	public File getFile(int a_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String fileName = getFileName(a_id);
		 File file = new File(fileName);
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT ATTACHMENT FROM ATTACHMENTS WHERE A_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, a_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Blob blob = rs.getBlob(1);
				InputStream in = blob.getBinaryStream();
				OutputStream out = new FileOutputStream(file);
				byte[] buff = new byte[4096];
				int len = 0;
				
				while((len = in.read(buff)) != -1) {
					out.write(buff,0,len);
					}
				in.close();
				out.flush();
				out.close();
				}
			}
		catch(SQLException e){e.printStackTrace();}
		catch(FileNotFoundException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
		 
		
		finally{close(ps);close(rs);}
		return file;
	}

	@Override
	public String getFileName(int a_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String filename = "";
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT A_NAME FROM ATTACHMENTS WHERE A_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, a_id);
			rs = ps.executeQuery();
			while(rs.next()) {filename = rs.getString(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		return filename;
	}
	
	@Override
	public String getFileType(int a_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String filetype = "";
		try(Connection conn = Bridge.connect()){
			
			String sql = "SELECT A_TYPE FROM ATTACHMENTS WHERE A_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, a_id);
			rs = ps.executeQuery();
			while(rs.next()) {filetype = rs.getString(1);}
			}
		catch(SQLException e){e.printStackTrace();}
		finally{close(ps);close(rs);}
		return filetype;
	}
	
}
