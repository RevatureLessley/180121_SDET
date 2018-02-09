package examples.spring.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.servlets.BookPublishingServlet;
import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.utilities.HibernateUtilities;

@Controller
//@Scope("session")
public class PublishBookController {
	
	private static final Logger logger = LogManager.getLogger(BookPublishingServlet.class);
	
	@RequestMapping(value = "PublishBookController", method = RequestMethod.GET)
	public ModelAndView showView() {
		ModelAndView model = new ModelAndView("publishBook", "Book", new Book());

		return model;
	}
	
	

	@RequestMapping(value = "PublishBookController2", method = RequestMethod.POST)	
	public void checkStuff(@ModelAttribute("Book")Book book, BindingResult result, Model model) {
		
		logger.debug("Running checkStuff method");
		String isbn13 = book.getIsbn13();
		String author = book.getAuthor();
		logger.debug("isbn13 during runtime: " + isbn13);
		
		
	}
	
	
	/*public ModelAndView viewBookDetails(@Valid @ModelAttribute("Book")Book book, BindingResult result, ModelMap model) {
		
		logger.debug("Running PublishBookController");
		logger.catching(new Exception());
		
		ModelAndView mav = new ModelAndView("publishBook");
		
		BookDAO dao = HibernateUtilities.getBookDAO();		
		
		String isbn13 = req.getParameter("isbn13");
		logger.debug("Isbn13 during runtime: " + isbn13);

		BookDAO database = DAOUtilities.getBookDAO();
		Book tempBook = database.getBookByISBN(isbn13);
		if (tempBook != null) {
			// ASSERT: book with isbn already exists

			return mav;

		} else {
			
			String title = req.getParameter("title");
			String author = req.getParameter("author");
			Double price = Double.parseDouble(req.getParameter("price"));

			Book book = new Book();
			book.setIsbn13(isbn13);
			book.setTitle(title);
			book.setAuthor(author);
			book.setPublishDate(LocalDate.now());
			book.setPrice(price);

			// Uploading a file requires the data to be sent in "parts", because
			// one HTTP packet might not be big
			// enough anymore for all of the data. Here we get the part that has
			// the file data
			try{
				Part content = req.getPart("content");
				
				InputStream is = null;
				ByteArrayOutputStream os = null;
				
				is = content.getInputStream();
				os = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];

				while (is.read(buffer) != -1) {
					os.write(buffer);

				}
				
				book.setContent(os.toByteArray());
			}	
			

			catch (Exception e) {
				System.out.println("Could not upload file!");
				e.printStackTrace();				
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
				return mav;
			}else {
				req.getSession().setAttribute("message", "There was a problem publishing the book");
				req.getSession().setAttribute("messageClass", "alert-danger");
				return mav;
				
			}
		}
	}*/
		
			
}
	
