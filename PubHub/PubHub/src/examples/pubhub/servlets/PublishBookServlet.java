package examples.pubhub.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

@MultipartConfig // This annotation tells the server that this servlet has
					// complex data other than forms
// Notice the lack of the @WebServlet annotation? This servlet is mapped the old
// fashioned way - Check the web.xml!
@WebServlet("PublishBook")
public class PublishBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("publishBook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String isbn13 = req.getParameter("isbn13");

		BookDAO database = DAOUtilities.getBookDAO();
		Book tempBook = database.getBookByISBN(isbn13);
		if (tempBook != null) {
			// ASSERT: book with isbn already exists

			req.getSession().setAttribute("message", "ISBN of " + isbn13 + " is already in use");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("publishBook.jsp").forward(req, resp);

		} else {

			Book book = new Book();
			book.setIsbn13(req.getParameter("isbn13"));
			book.setTitle(req.getParameter("title"));
			book.setAuthor(req.getParameter("author"));
			book.setPublishDate(LocalDate.now());
			book.setPrice(Double.parseDouble(req.getParameter("price")));

			// Uploading a file requires the data to be sent in "parts", because
			// one HTTP packet might not be big
			// enough anymore for all of the data. Here we get the part that has
			// the file data
			Part content = req.getPart("content");

			InputStream is = null;
			ByteArrayOutputStream os = null;

			try {
				is = content.getInputStream();
				os = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];

				while (is.read(buffer) != -1) {
					os.write(buffer);
				}
				
				book.setContent(os.toByteArray());

			} catch (IOException e) {
				System.out.println("Could not upload file!");
				e.printStackTrace();
			} finally {
				if (is != null)
					is.close();
				if (os != null)
					os.close();
			}

			boolean isSuccess = database.addBook(book);
			
			if(isSuccess){
				req.getSession().setAttribute("message", "Book successfully published");
				req.getSession().setAttribute("messageClass", "alert-success");

				// We use a redirect here instead of a forward, because we don't
				// want request data to be saved. Otherwise, when
				// a user clicks "refresh", their browser would send the data
				// again!
				// This would be bad data management, and it
				// could result in duplicate rows in a database.
				resp.sendRedirect(req.getContextPath() + "/BookPublishing");
			}else {
				req.getSession().setAttribute("message", "There was a problem publishing the book");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("publishBook.jsp").forward(req, resp);
				
			}
		}
	}

}
