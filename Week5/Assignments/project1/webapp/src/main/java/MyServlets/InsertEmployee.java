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

public class InsertEmployee extends HttpServlet {

	private static final long serialVersionUID = 1272196330744127235L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter out =res.getWriter();
		res.setContentType("text/html");        
		String username=req.getParameter("username");    
		String password=req.getParameter("password");   

		System.out.println(username);
		System.out.println(password);

		try
		{
			String generatedColumns[] = { "employee_id" };
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","badpassword123");
			String sql = "INSERT INTO EMPLOYEE (username, password, claim_max) VALUES "+ 
					" (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql, generatedColumns);

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setFloat(3, 100); 
			System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");
			con.close();
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
