package MyServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONML;

import com.google.gson.Gson;

import Connections.Employee;
import Connections.TuitionReimbursementConnection;

public class GetEmployee extends HttpServlet {

	private static final long serialVersionUID = -3586386868343581118L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req, res);
	}
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("application/json");      
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String username=req.getParameter("username");    
		String password=req.getParameter("password");
	
		
		TuitionReimbursementConnection t = new TuitionReimbursementConnection();
	
	
		
		Employee e  = t.getEmployee(username, password);
		System.out.println(username);
		System.out.println(password);
		Gson g = new Gson();
		g.toJson(e);
		out.print(g);
		out.flush();
	}
}
