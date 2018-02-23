package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ToYear;
import dao.ToYearDao;
import dao.ToYearDaoImpl;

/**
 * Servlet implementation class GetToYear
 */
public class GetToYear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetToYear() {
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
		HttpSession session = request.getSession(false);

		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		ToYearDao tyDao = new ToYearDaoImpl();
		String username = String.valueOf(session.getAttribute("username"));
		ToYear ty = tyDao.getRecord(username);
		
		if(ty!=null){
			String myXml = "<root>";
			myXml += "<toyear><maximum>" + ty.getMaximum() + "</maximum>"
							+ "<awarded>" + ty.getAwarded() + "</awarded>"
							+ "<pending>" + ty.getPending() + "</pending>"
							+ "</toyear>";

			myXml += "</root>";
			
			out.print(myXml);
		}else{
			out.print("<root><toyear><Username>No Records<Username></toyear></root>");
		}
	}

}
