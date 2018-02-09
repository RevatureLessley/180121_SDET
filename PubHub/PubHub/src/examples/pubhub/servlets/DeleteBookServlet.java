package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.dao.TagDAO;

import examples.pubhub.dao.BookDAO;

@WebServlet("/DeleteBook")
public class DeleteBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String isbn13 = req.getParameter("isbn13");

		BookDAO database = DAOUtilities.getBookDAO();
		boolean isSuccess = database.deleteBookByISBN(isbn13);
		
		if (isSuccess) {
			req.getSession().setAttribute("message", "Book successfully deleted");
			req.getSession().setAttribute("messageClass", "alert-success");

			// We use a redirect here instead of a forward, because we don't
			// want request data to be saved. Otherwise, when
			// a user clicks "refresh", their browser would send the data
			// again!
			// This would be bad data management, and it
			// could result in duplicate rows in a database.
			resp.sendRedirect(req.getContextPath() + "/BookPublishing");
		} else {
			req.getSession().setAttribute("message", "There was a problem deleting the book");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("BookPublishing").forward(req, resp);
		}
	}
}
