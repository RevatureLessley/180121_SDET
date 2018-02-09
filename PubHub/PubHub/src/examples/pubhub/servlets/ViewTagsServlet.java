package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Tag;
import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;


@WebServlet("/ViewTags")
public class ViewTagsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the list of Tags from the Database
		TagDAO dao = DAOUtilities.getTagDAO();
		List<Tag> tagList = dao.getAllTags();
		
		BookDAO bDAO = DAOUtilities.getBookDAO();
		List<Book> bookList = bDAO.getAllBooks();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("tags", tagList);
		request.getSession().setAttribute("books", bookList);
		
		request.getRequestDispatcher("viewTags.jsp").forward(request, response);
	}
}
