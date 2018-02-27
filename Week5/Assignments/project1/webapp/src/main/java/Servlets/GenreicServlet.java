package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenreicServlet extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2302737366509618117L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter pw=res.getWriter();
        res.setContentType("text/html");        
        String tb=req.getParameter("table");    
 
        try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","badpassword123");
             Statement st=con.createStatement();
             System.out.println("connection established successfully...!!");     
 
             ResultSet rs=st.executeQuery("Select * from "+tb);
 
             pw.println("<table border=1>");
                 while(rs.next())
                 {
                     pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td>"+
                                      "<td>"+rs.getString(3)+"</td></tr>");
                 }
             pw.println("</table>");
             pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
 
    }
}
