package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.dao.TagDAO;


@WebServlet("/DeleteTag")
public class DeleteTagServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String isbn13 = req.getParameter("isbn13");
		String tag_name = req.getParameter("tag");

		TagDAO database = DAOUtilities.getTagDAO();
		boolean isSuccess = database.deleteTag(isbn13, tag_name);
		
		if (isSuccess) {
			req.getSession().setAttribute("message", "Tag successfully deleted");
			req.getSession().setAttribute("messageClass", "alert-success");

			// We use a redirect here instead of a forward, because we don't
			// want request data to be saved. Otherwise, when
			// a user clicks "refresh", their browser would send the data
			// again!
			// This would be bad data management, and it
			// could result in duplicate rows in a database.
			resp.sendRedirect(req.getContextPath() + "/ViewTags");
		} else {
			req.getSession().setAttribute("message", "There was a problem deleting the tag");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("ViewTags").forward(req, resp);
		}
	}
}
