public class RegisterEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doPost(request, response);}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		int w_id = Integer.parseInt(request.getParameter("w_id"));
		int sup_id = Integer.parseInt(request.getParameter("superv"));
		double r_aval = 1000;
		int admin_status = request.getParameter("admin_status");
		int dep_id = request.getParameter("dep_id");
		
		SASDataService.insertWorker(fName, String lName, String uName, String pWord, String email, w_id, superv, r_aval, depId, admin_stat);
	}

}