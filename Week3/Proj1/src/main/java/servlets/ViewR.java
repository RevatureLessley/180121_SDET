package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String username = request.getParameter("username");
		ReimbursementDao rDao = new ReimbursementDaoImpl();
		EmployeeDao eDao = new EmployeeDaoImpl();
		Reimbursement r = rDao.getR(id);
		int level = eDao.getLevel(username);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String printTable = "<table border = \"2px\"><tr>"
				+ "<th>Employee Name</th>"
				+ "<th>Submit Date</th>"
				+ "<th>Start Date</th>"
				+ "<th>Covered Amount</th>"
				+ "</tr><tr>"
				+ "<td>"
				+ r.getUsername()
				+ "</td>"
				+ "<td>"
				+ r.getSubmitDate()
				+ "</td>"
				+ "<td>"
				+ r.getStartDate()
				+ "</td>"
				+ "<td>"
				+ r.getAmount()
				+ "</td>"
				+ "</table>";
		
		String approveButton = "<form action=\"ApproveR\" method=\"POST\">"
						+ "<input type = \"hidden\" name=\"username\" value =\"" + username + "\"></input>"
						+ "<input type = \"hidden\" name=\"id\" value =\"" + Integer.toString(id) + "\"></input>"
						+ "Provide a reason: <input type=\"text\" name=\"reason\" required></input>"
						+ "<input type=\"submit\" value=\"Approve\"></input>"
						+ "</form>";
		
		String rejectButton = "<form action=\"RejectR\" method=\"POST\">"
				+ "<input type = \"hidden\" name=\"level\" value =\"" + eDao.getLevel(username) + "\"></input>"
				+ "<input type = \"hidden\" name=\"id\" value =\"" + Integer.toString(id) + "\"></input>"
				+ "<input type=\"submit\" value=\"Reject\"></input>"
				+ "Provide a reason: <input type=\"text\" name=\"reason\" required></input>"
				+ "</form>";
		
		out.println(printTable);
		out.println(approveButton);
		out.println(rejectButton);
		
		if(level == 0) {
			String uploadFile = "<form action=\"\" method=\"POST\">"
					+ "<input name=\"file\" type=\"file\"></input></form>";
		}
		

	}

}
