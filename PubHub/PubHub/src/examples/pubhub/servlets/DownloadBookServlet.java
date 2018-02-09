package examples.pubhub.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class DownloadBookServlet
 */
@WebServlet("/DownloadBook")
public class DownloadBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isbn13 = request.getParameter("isbn13");
		
		BookDAO dao = DAOUtilities.getBookDAO();
		Book book = dao.getBookByISBN(isbn13);
		
		// In order to download the PDF to the client, we need to add a data stream to the response.
		// First we set the content type to PDF, so the browser knows how to interpret the data it's receiving
		response.setContentType("application/pdf");
				
		// Then we set the filename to the book's title, so it's not a random string of characters
		response.setHeader("Content-Disposition", "attachment; filename=" + book.getTitle() + ".pdf");
				
		// Create the input stream (IN to the app FROM the book)
		InputStream is = new ByteArrayInputStream(book.getContent());
				
		// Create the output stream (OUT of the app TO the client)
		OutputStream os = response.getOutputStream();
				
		// We're going to read and write 1KB at a time
		byte[] buffer = new byte[1024];
				
		// Reading returns -1 when there's no more data left to read.
		while (is.read(buffer) != -1) {
			os.write(buffer);
		}
		
		// Always close your streams!
		os.flush();
		os.close();
		is.close();
	}

}
