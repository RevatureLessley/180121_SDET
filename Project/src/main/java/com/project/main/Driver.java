package com.project.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.project.beans.Tuition;
import com.project.dao.ReimbursementDao;
import com.project.dao.ReimbursementDaoImp;
import com.project.dao.TuitionDao;
import com.project.dao.TuitionDaoImp;
import com.project.services.ReimbursementServices;
import com.project.services.TuitionServices;
import com.project.util.Connections;

public class Driver {

	public static void main(String[] args) {
//		TuitionDao dao = new TuitionDaoImp();
//		System.out.println(dao.deleteTuitonByUsername("HARISH"));
//		show_all_tuition();
//		System.out.println(TuitionServices.getTuitionByApprover("RICHARD"));
		
		}
	
	public static void show_all_tuition() {

		TuitionDao dao = new TuitionDaoImp();
		List<Tuition> tuitions =  dao.getAllTuition();
		for(Tuition t: tuitions) {
			System.out.println(t);
		}
		
	}
	public void addfile() {
		
		Connection conn = null;
		String sql = "UPDATE tuition SET attachment = ? WHERE e_id='1020'";
		PreparedStatement stmt = null;
		InputStream is = null;
	
		File tempFile = new File("Blinkist.msg");
		System.out.println(tempFile.exists()?"The sample file exists":"The sample file does not exist! Did you delete \"ws.pdf\"?");
		
		try {
			is = new FileInputStream(tempFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			conn = Connections.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setBinaryStream(1, is, tempFile.length());
			System.out.println("Executing SQL Statement: " + sql);
			System.out.println("Connection is valid: " + conn.isValid(5));
			System.out.println("Rows updated: " + stmt.executeUpdate());
			System.out.println("Success!");
		} catch (SQLException e) {
			System.out.println("Failure!");
			e.printStackTrace();
		}
		
	}
	
}
