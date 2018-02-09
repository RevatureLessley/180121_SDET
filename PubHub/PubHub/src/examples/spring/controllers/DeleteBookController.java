package examples.spring.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

@Controller
public class DeleteBookController {
	
	@RequestMapping("DeleteBookController")
	public ModelAndView deleteBookController(@RequestParam Map<String,String> allParams) {
		String isbn13 = allParams.get("isbn13");
		
		BookDAO database = DAOUtilities.getBookDAO();
		boolean isSuccess = database.deleteBookByISBN(isbn13);
		
		ModelAndView model = new ModelAndView("bookPublishingHome");

		List<Book> bookList = database.getAllBooks();
		
		model.addObject("books", bookList);
		
		return model;	
		
	}

}
