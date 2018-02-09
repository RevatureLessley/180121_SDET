package examples.spring.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.utilities.HibernateUtilities;

@Controller
public class BookPublishingController  {
	
	private static final Logger logger = LogManager.getLogger(BookPublishingController.class);

	@RequestMapping(value = "BookPublishing", method = RequestMethod.GET)
	public ModelAndView viewAllBooks() {
		
		logger.debug("Entered Book Publishing Controller");
		ModelAndView model = new ModelAndView("bookPublishingHome");
		
		BookDAO dao = HibernateUtilities.getBookDAO();
		List<Book> bookList = dao.getAllBooks();
				
		model.addObject("books", bookList);
		return model;
	}
	
}
