package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class GetRequests
 */
public class GetRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRequests() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		/*
		 * In the case of a default AJAX request, we will return back to it,
		 * an xml file, so we need to set the response type to reflect that.
		 */
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		ReimbursementDao rDao = new ReimbursementDaoImpl();
		EmployeeDao eDao = new EmployeeDaoImpl();
		String username = String.valueOf(session.getAttribute("username"));
		List<Reimbursement> reims = rDao.getR( username, eDao.getLevel(username) );
		
		if(reims!=null){
			String myXml = "<root>";
			for(Reimbursement r : reims){
				String event = "";
				switch(r.getEvent()) {
					case 1: 
						event = "University Course";
						break;
					case 2:
						event = "Seminar";
						break;
					case 3: 
						event = "Certification Prep";
						break;
					case 4:
						event = "Certification";
						break;
					case 5: 
						event = "Technical Training";
						break;
					case 6:
						event = "Other";
						break;		
						
				}
				myXml += "<reimbursement><Username>" + r.getUsername() + "</Username>"
								+ "<Amount>" + r.getAmount() + "</Amount>"
								+ "<SubmitDate>" + r.getSubmitDate() + "</SubmitDate>"
								+ "<StartDate>" + r.getStartDate() + "</StartDate>"
								+ "<Event>" + event + "</Event>"
								+ "<level>" + eDao.getLevel(username) + "</level>"
								+ "<rId>" + r.getId() + "</rId>"
								+ "<username>" + username +"</username>"
								+ "</reimbursement>";

			}
			myXml += "</root>";
			
			out.print(myXml);
		}else{
			out.print("<root><reimbursement><Username>No Records<Username></reimbursement></root>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
