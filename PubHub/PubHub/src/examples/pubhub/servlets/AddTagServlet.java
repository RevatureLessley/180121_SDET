package examples.pubhub.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.utilities.DAOUtilities;
//This servlet populates the session with data needed to update the database
@WebServlet("/AddTag")
public class AddTagServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("publishBook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String isbn13 = req.getParameter("isbn13");
		String tag_name = req.getParameter("tag");

		TagDAO database = DAOUtilities.getTagDAO();
		boolean isSuccess = database.addTag(isbn13,tag_name);
		
		if (isSuccess) {
			req.getSession().setAttribute("message", "Tag successfully posted");
			req.getSession().setAttribute("messageClass", "alert-success");

			// We use a redirect here instead of a forward, because we don't
			// want request data to be saved. Otherwise, when
			// a user clicks "refresh", their browser would send the data
			// again!
			// This would be bad data management, and it
			// could result in duplicate rows in a database.
			resp.sendRedirect(req.getContextPath() + "/ViewTags");
		} else {
			req.getSession().setAttribute("message", "Couldn't add tag! Most likely the tag already exists or the book hasn't been added to the database yet!");
			req.getSession().setAttribute("messageClass", "alert-danger");
			resp.sendRedirect(req.getContextPath() + "/addTag.jsp");
		}
	}
	
}
