package examples.spring.controllers;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

@Controller
public class ViewBookDetailsController {
	
	private static final Logger logger = LogManager.getLogger(BookPublishingController.class);

	@RequestMapping(value = "ViewBookController", method = RequestMethod.GET)
	public ModelAndView viewBookDetails(@RequestParam Map<String,String> allParams) {
		
		String isbn = allParams.get("isbn13");
		
		logger.debug("Entered View Book Controller");
		ModelAndView model = new ModelAndView("bookDetails");
		
		BookDAO dao = DAOUtilities.getBookDAO();
		Book book = dao.getBookByISBN(isbn);
				
		model.addObject("book", book);
		return model;
	}

}
