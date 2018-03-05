package MyServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connections.Claim;
import Connections.Employee;
import Connections.TuitionReimbursementConnection;

public class GetClaim   extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(GetClaim.class.getName());
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter out =res.getWriter();
		res.setContentType("text/html");        
		String id=req.getParameter("employee_id");    

		System.out.println(id);

		try
		{
			String generatedColumns[] = { "employee_id" };
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","badpassword123");
			String sql = "SELECT * FROM CLAIM WHERE claim_employee_id = '" + id + "'";
			
			Statement statment = con.createStatement();
			PreparedStatement ps = con.prepareStatement(sql, generatedColumns);
			ResultSet rs = statment.executeQuery(sql);

			// result sets
            if(rs.next()) {
            	Claim claim;

            	claim = new Claim(
            			rs.getInt("claim_id"),
                        rs.getInt("claim_employee"),
                        rs.getInt("claim_grade_id"),
                        rs.getString("date_of_event"),
                        rs.getInt("time_of_event"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getFloat("cost"),
                        rs.getString("event_type"),
                        null,
                        rs.getInt("missed_days"));
            	
                out.write(claim.toString());
            }
           
                
                con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
}
