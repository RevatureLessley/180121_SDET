package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;

/**
 * Servlet implementation class RejectR
 */
public class RejectR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RejectR() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		int level = Integer.parseInt(request.getParameter("level"));
		String reason = request.getParameter("reason");
		ReimbursementDao rDao = new ReimbursementDaoImpl();
		rDao.rejectR(id, level, reason);
		
		if(level == 0) {
			request.getRequestDispatcher("reimbursement.html").forward(request, response);											
			
		}
		else {
			request.getRequestDispatcher("supervisor.html").forward(request, response);											
		}
	}

}
