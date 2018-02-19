package loginui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.revature.trms.service.EmployeeService;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Authentication
 */
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out= response.getWriter();
		
		try {
			if (EmployeeService.authentication(username, password)!=null) {
			//	RequestDispatcher rd = request.getRequestDispatcher("EmployeeAccount.html");
			//	rd.forward(request, response);
				
				response.sendRedirect("EmployeeAccount.html");
				
			}
		} catch (SQLException e) {
			
			out.println("<p>"+e.getMessage()+"</p>");
		}
		
	}

}
