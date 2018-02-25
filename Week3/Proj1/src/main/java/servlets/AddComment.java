package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import util.HtmlTemplates;

/**
 * Servlet implementation class AddComment
 */
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComment() {
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
		String comment = request.getParameter("comment");
		int level = Integer.parseInt(request.getParameter("level"));
		int id = Integer.parseInt(request.getParameter("id"));
		ReimbursementDao rDao = new ReimbursementDaoImpl();
		
		rDao.addComment(comment, level, id);
		
		if(level == 0) {
			request.getRequestDispatcher("reimbursement.html").forward(request, response);											
			
		}
		else {
			request.getRequestDispatcher("supervisor.html").forward(request, response);											
		}
	}

}
