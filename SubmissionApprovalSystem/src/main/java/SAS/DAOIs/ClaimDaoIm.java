package SAS.DAOIs;

import java.io.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

import com.oracle.tools.packager.IOUtils;

import SAS.DAOs.ClaimDao;
import SAS.Utils.SASConnect;

public class ClaimDaoIm implements ClaimDao{
	Logger = new log4j.Logger();
	
public List<File> downloadFile(int rid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<File> laf = new ArrayList<>();

			try(Connection conn = SASConnect.getConnection()) {
				String sql = "SELECT * FROM uploaded WHERE up_reim_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rid);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					InputStream is = null;
			        OutputStream os = null;
			        File f = new File(rs.getString(4));
					is = rs.getBlob(3).getBinaryStream();
					os = new FileOutputStream(f);
					IOUtils.copy(is, os);
					
					laf.add(new AttachedFile(rs.getInt(1), rs.getInt(2), f, rs.getString(4), rs.getString(5)));
				}
		
			
			return laf;
		}


	public int insertClaim(Claim c);

	// attachments
	public int insertAttachment(File f, int c_id);


	public static List<ByteArrayInputStream> getAttachmentsByid(int cId) {

		return dao.getAttachmentsBy(cId);
	}

	public int uploadFile(File f, int rid) {
		PreparedStatement ps = null;
		FileInputStream in = null;

		Connection conn = SASConnect.getConnection(); 
			String sql = "INSERT into uploaded (up_reim_id, upload_file, upfilename) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			in = new FileInputStream(f);
			ps.setInt(1, rid);
			ps.setBinaryStream(2, in, (int) f.length());
			ps.setString(3, f.getName());
			logger.info("insert then update");
			ps.executeUpdate();
	
		return 0;
	}
}