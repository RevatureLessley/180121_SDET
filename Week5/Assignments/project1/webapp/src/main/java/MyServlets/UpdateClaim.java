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

public class UpdateClaim  extends HttpServlet {

	private static final long serialVersionUID = 1272196330744127235L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter out =res.getWriter();
		res.setContentType("text/html");        
		String cid=req.getParameter("claim_grade_id");    
		String cost=req.getParameter("cost"); 
		String eid=req.getParameter("claim_employee_id"); 
		
		try
		{
			String generatedColumns[] = { "employee_id" };
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","badpassword123");
			String sql = "UPDATE CLAIM SET claim_grade_id = ?, cost = ? WHERE claim_employee_id = ?";
			PreparedStatement ps = con.prepareStatement(sql, generatedColumns);
			ps.setInt(1, Integer.parseInt(cid));
            ps.setFloat(2, Integer.parseInt(cost));
            ps.setInt(2, Integer.parseInt(eid));

            System.out.println(ps.executeUpdate() + " RECORD(S) UPDATED!");
			

			con.close();
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
