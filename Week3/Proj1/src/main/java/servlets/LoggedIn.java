package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Employee;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import util.HtmlTemplates;

//import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class LoggedIn
 */
public class LoggedIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoggedIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int level = Integer.parseInt(request.getParameter("level"));
		if(request.getParameter("createOrLogin").equals("create")) {
			out.println("<h3>Chose to create</h3>");		
			EmployeeDao eDao = new EmployeeDaoImpl();
			eDao.createEmployee((String) request.getParameter("username"),(String) request.getParameter("password"), level );
			setSessionAttributes(request, response, session);
			if( ((String) request.getParameter("level")).equals("0")  ) {
				request.getRequestDispatcher("reimbursement.html").forward(request, response);					
			}
			else {
				request.getRequestDispatcher("supervisor.html").forward(request, response);											
			}
		}
		
		else if(request.getParameter("createOrLogin").equals("login")) {
	
			EmployeeDao eDao = new EmployeeDaoImpl();
			Employee emp = null;
			emp = eDao.logIn((String) request.getParameter("username"),(String) request.getParameter("password"));
			if(emp != null) {
				out.println("<h3>" + emp.getUsername() + "  " + emp.getPassword() + " </h3>");	
				setSessionAttributes(request, response, session);
				if(eDao.getLevel((String) request.getParameter("username")) == 0) {
					request.getRequestDispatcher("reimbursement.html").forward(request, response);		
					
				}
				else {
					request.getRequestDispatcher("supervisor.html").forward(request, response);							
				}
			}
			else {
				out.println("<h3>No user exists. Try creating a new user</h3>");	
				HtmlTemplates.goBackButton(out);
			}
		}	
	}
	
	private void setSessionAttributes(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		
		session.setAttribute("username", request.getParameter("username"));
		session.setAttribute("password", request.getParameter("password"));
		//session.setMaxInactiveInterval(5);			

	}

}
