package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Tag;
import examples.pubhub.model.Book;

/**
 * Interface for our Data Access Object to handle database queries related to Books.
 */
public interface TagDAO {
	
	//Retrieve list of results
	public List<Tag> getAllTags();
	public List<Tag> getTagsByBook(String isbn);					
	public List<Book> getBooksByTag(String tag);					
	
	//add complete tag to book in database
	public boolean addTag(String isbn, String tag);	

	//delete specified tag
	public boolean deleteTag(String isbn, String tag);

		
}
