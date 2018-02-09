package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/*
 * This servlet will take you to the homepage for the Book Publishing module (level 100)
 */
@WebServlet("/BookPublishingServlet")
public class BookPublishingServlet extends HttpServlet {
		
	private static final Logger logger = LogManager.getLogger(BookPublishingServlet.class);
		
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("Entering Book Publishing Servlet");
//		logger.catching(Level.ALL , new Exception());
		// Grab the list of Books from the Database
		BookDAO dao = DAOUtilities.getBookDAO();
		List<Book> bookList = dao.getAllBooks();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("books", bookList);
		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);

	}
}
