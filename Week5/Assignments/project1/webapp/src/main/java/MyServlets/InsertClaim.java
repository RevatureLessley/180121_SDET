package MyServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertClaim  extends HttpServlet{

	private static final long serialVersionUID = 1272196330744127235L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter out =res.getWriter();
		res.setContentType("text/html");        
		String claim_employee_id=req.getParameter("claim_employee_id");    
		String claim_grade_id=req.getParameter("claim_grade_id"); 
		String date_of_event=req.getParameter("date_of_event");    
		String time_of_event=req.getParameter("time_of_event"); 
		String location=req.getParameter("location");    
		String description=req.getParameter("description"); 
		String cost=req.getParameter("cost");    
		String event_type=req.getParameter("event_type"); 
		String attachement=req.getParameter("attachement");    
		String missed_days=req.getParameter("missed_days"); 
		try
		{
			String generatedColumns[] = { "employee_id" };
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","badpassword123");
			String sql = "INSERT INTO CLAIM (claim_employee_id, claim_grade_id, "
    				+ "date_of_event, time_of_event, location, description, cost, "
    				+ "event_type, attachement, missed_days) VALUES "+ 
    				" (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql, generatedColumns);
			
			ps.setInt(1, Integer.parseInt(claim_employee_id));
            ps.setInt(2, Integer.parseInt(claim_grade_id));
            ps.setString(3, date_of_event); 
            ps.setInt(4, Integer.parseInt(time_of_event));
            ps.setString(5, description);
            ps.setString(6, cost);
            ps.setFloat(7, Float.parseFloat(cost));
            ps.setString(8, event_type);
            ps.setString(7, attachement);
            ps.setInt(8, Integer.parseInt(missed_days));
			System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");
			con.close();
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
