package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Implementation for the TagDAO, responsible for querying the database for Tag and Book objects.
 */

public class TagDAOImpl implements TagDAO{
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	@Override
	public boolean addTag(String isbn, String tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (? , ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			stmt.setString(2, tag);

			if (stmt.executeUpdate() != 0) 
				return true;				

			else 
				return false;

			
		} catch (SQLException e) {	
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}	
	}
	
	
	@Override
	public boolean deleteTag(String isbn, String tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE isbn_13=? AND tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			stmt.setString(2, tag);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}	
	}
	
	@Override
	public List<Tag> getTagsByBook(String isbn){
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();
				
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tag_name"));				
				
				tags.add(tag);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;		
		
	}
	
	@Override
	public List<Book> getBooksByTag(String tag){
		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM books INNER JOIN book_tags ON book_tags.tag_name LIKE ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;			

	}	
	
	public List<Tag> getAllTags(){
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM book_tags";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			while (rs.next()) {
				Tag tag = new Tag();

				// Each variable in our Tag object maps to a column in a row from our results.
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tag_name"));
				
				tags.add(tag);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			// Close resources to avoid a memory leak
			closeResources();
		}
		
		// return the list of Tag objects populated by the DB.
		return tags;
		
	}
	
	
	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
			
	
}
