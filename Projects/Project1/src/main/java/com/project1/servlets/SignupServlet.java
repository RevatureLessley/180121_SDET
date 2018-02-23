package com.project1.servlets;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.project1.services.NewAccount;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		System.out.println("signup servlet reached");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String accountType = request.getParameter("radio");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		
		if (!password.equals(confirm) || NewAccount.uniqueEmail(email)) {
			System.out.print("unmatching passwords/email exists");
			RequestDispatcher rd = request.getRequestDispatcher("signuperror.html");
			rd.forward(request, response);
		} else {
			NewAccount.insertNewAccount(firstname, lastname, address, accountType, 
					email, password, confirm);
		}
	}

}

/*
String username = request.getParameter("username");
String password = request.getParameter("password");
System.out.println(username + " " + password);
response.setContentType("text/html");
PrintWriter out = response.getWriter();

HttpSession session = request.getSession();
if(!session.isNew()){
	out.println("<h1>Stop being weird.</h1>");
}
else if(ValidateLogin.validate(username, password)){
	session.setAttribute("username", username);
	RequestDispatcher rd = request.getRequestDispatcher("user/userhome.html");
	rd.forward(request, response);
}else{
	session.invalidate();
	
	out.print("<h1>INVALID CREDENTIALS</h1>");
	HtmlTemplates.goBackButton(out);
	
}
*/