package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TuitionReimbursementFormServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3788079111081547745L;

	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
		PrintWriter pw=res.getWriter();
        res.setContentType("text/html");        
        String tb=req.getParameter("submit");    
 
		 try
	        {
	             Class.forName("oracle.jdbc.driver.OracleDriver");
	             Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","badpassword123");
	             String sql = "INSERT INTO EMPLOYEE (username, password, claim_max) VALUES "+ 
		     				" (?,?,?)";
	             PreparedStatement ps = con.prepareStatement(sql);
	     		
	             ps.setString(1, "benny");
	             ps.setString(2, "badpassword");
	             ps.setFloat(3, 100); 

	             System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");
	             con.commit();
	             con.close();
	        }
	        catch (Exception e)
		 {
	            e.printStackTrace();
		 }
		 
    }
}
