package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Reimbursement;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;

/**
 * Servlet implementation class CheckReim
 */
public class ViewR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewR() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int id = Integer.parseInt(request.getParameter("rid"));
		HttpSession session = request.getSession(false);
		session.setAttribute("id", id);
		String username = request.getParameter("username");
		ReimbursementDao rDao = new ReimbursementDaoImpl();
		EmployeeDao eDao = new EmployeeDaoImpl();
		Reimbursement r = rDao.getR(id);
		int level = eDao.getLevel(username);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<!-- Every page is made up of the header and the body. Header stores meta data for browsers\r\n" + 
				"	to determine what properties are used by the page. -->\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"\r\n" + 
				"<title>Tuition Reimbursement Home</title>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<link rel=\"stylesheet\" type=\"text/css\"\r\n" + 
				"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
				"<!-- Bring Bootstrap 3 libraries -->\r\n" + 
				"\r\n" + 
				"<script\r\n" + 
				"			  src=\"http://code.jquery.com/jquery-3.3.1.min.js\"\r\n" + 
				"			  integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\"\r\n" + 
				"			  crossorigin=\"anonymous\"></script>\r\n" + 
				"\r\n" + 
				"<script\r\n" + 
				"	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
				"<!-- Bring in supplemental javascript for bootstrap -->\r\n" + 
				"\r\n" + 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"resource/css/main.css\">\r\n" + 
				"\r\n" + 
				"</head>	<body>" +
				"	<nav class=\"navbar navbar-inverse navbar-fixed-top\">\r\n" + 
				"		<div class=\"container-fluid\">\r\n" + 
				"			<div class=\"navbar-header\">\r\n" + 
				"				<a class=\"navbar-brand\" href=\"index.html\">HOME</a>\r\n" + 
				"			</div>\r\n" + 
				"			<ul class=\"nav navbar-nav navbar-right\">\r\n" + 
				"				<li>\r\n" + 
				"					<form method=\"GET\" action=\"LogOut\">\r\n" + 
				"						<button class=\"btn-danger\" type=\"submit\" formaction=\"LogOut\">LogOut\r\n" + 
				"						</button>\r\n" + 
				"					</form>\r\n" + 
				"				</li>\r\n" + 
				"			</ul>\r\n" + 
				"		</div>\r\n" + 
				"	</nav>" +
				"<div class=container-fluid style=\"margin-top:50px\">\r\n" + 
				"<div class=\"well\">");
		
		
		
		String supervisor = "";
		if(!r.getSupervisor().equals("Supervisor is Dept Head")) {
			supervisor = "<tr><td>Supervisor</td><td>"
					+ r.getSupervisor()
					+ "</td><td>"
					+ r.getSupcomment()
					+ "</td><td>"
					+ rDao.getApprovalString(r.getSuperapp())
					+ "</td></tr>";				
		}
		
		String printTable = "<table border = \"2px\">"
				+ "<tr><th>User</th><th>Name</th><th>Comment</th><th>Approval</th></tr>"
				+ "<tr><td>Employee</td><td>"
				+ r.getUsername()
				+ "</td><td>"
				+ r.getEmpcomment()
				+ "</td></tr>"
				+ supervisor
				+ "<tr><td>Dept Head</td><td>"
				+ r.getDept()
				+ "</td><td>"
				+ r.getDeptcomment()
				+ "</td><td>"
				+ rDao.getApprovalString(r.getDeptapp())
				+ "</td></tr>"
				+ "<tr><td>Benefits Coordinator</td><td>"
				+ r.getBenco()
				+ "</td><td>"
				+ r.getBencocomment()
				+ "</td><td>"
				+ rDao.getApprovalString(r.getBencoapp())
				+ "</td></tr>"
				+ "</table>";
		
		String addComment = "<form action=\"AddComment\" method=\"POST\">"
					+ "<input type = \"hidden\" name=\"level\" value =\"" + eDao.getLevel(username) + "\"></input>"
					+ "<input type = \"hidden\" name=\"id\" value =\"" + Integer.toString(id) + "\"></input>"
					+ "Add a comment:<input type=\"text\" name=\"comment\" required></input>"
					+ "<input type=\"submit\" value=\"Add\"></input>"
					+ "</form>";
		
		String approveButton = "<form action=\"ApproveR\" method=\"POST\">"
						+ "<input type = \"hidden\" name=\"level\" value =\"" + eDao.getLevel(username) + "\"></input>"
						+ "<input type = \"hidden\" name=\"id\" value =\"" + Integer.toString(id) + "\"></input>"
						+ "Provide a reason: <input type=\"text\" name=\"reason\" required></input>"
						+ "<input type=\"submit\" value=\"Approve\"></input>"
						+ "</form>";
		
		String rejectButton = "<form action=\"RejectR\" method=\"POST\">"
					+ "<input type = \"hidden\" name=\"level\" value =\"" + eDao.getLevel(username) + "\"></input>"
					+ "<input type = \"hidden\" name=\"id\" value =\"" + Integer.toString(id) + "\"></input>"
					+ "Provide a reason: <input type=\"text\" name=\"reason\" required></input>"
					+ "<input type=\"submit\" value=\"Reject\"></input>"
					+ "</form>";
		
		if(r.getFinalapp() == 1) {
			String approved = "<h3>This application has been approved</h3><br>";
			out.println(approved);
		}
		if(r.getRejected() == 1) {
			String rejected = "<h3>This application has been rejected</h3><br>";
			out.println(rejected);			
		}
		
		out.println(printTable);
		out.println(addComment);
		
		if(level != 0 && r.getFinalapp() == 0 && r.getRejected() == 0) {
			out.println(approveButton);
			out.println(rejectButton);			
		}

		
		if(level == 0) {
			String upload = "<form method=\"POST\" enctype=\"multipart/form-data\" action=\"UploadFile\">\r\n" + 
					"	<input type=\"file\" name=\"file\">\r\n" + 
					"	<input type=\"submit\" value=\"submit\">\r\n" + 
					"</form>";	
		
			out.println(upload);	
		}
		
		String download = "<form method=\"POST\" enctype=\"multipart/form-data\" action=\"DownloadFile\">\r\n" + 
				"	<input type=\"hidden\" name=\"file\">\r\n" + 
				"	<input type=\"submit\" value=\"Download File\">\r\n" + 
				"</form>";
		
		out.println(download);
		
		out.println("</div></div></body>");

	}

}
