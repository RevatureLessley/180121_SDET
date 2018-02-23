package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.utilities.HibernateUtilities;

import org.hibernate.query.Query; //Current since 5.2
import org.hibernate.Session;

/**
 * Implementation for the BookDAO, responsible for querying the database for Book objects.
 */
public class BookDAOImpl implements BookDAO {

	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public List<Book> getAllBooks() {
		Session session = HibernateUtilities.openSession();
		
		Query results = session.createQuery("FROM Book");
		return (ArrayList<Book>) results.list();
		
	}
	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public List<Book> getBooksByTitle(String title) {

		Session session = HibernateUtilities.openSession();
		
		Query results = session.createQuery("FROM Book WHERE title = :title");
		results.setParameter("title", title);		
		return (ArrayList<Book>) results.list();
		
/*		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Books WHERE title LIKE ?";	// Note the ? in the query
			stmt = connection.prepareStatement(sql);
			
			// This command populate the 1st '?' with the title and wildcards, between ' '
			stmt.setString(1, "%" + title + "%");	
			
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
		
		return books;*/
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public List<Book> getBooksByAuthor(String author) {

		Session session = HibernateUtilities.openSession();
		
		Query results = session.createQuery("FROM Book WHERE author LIKE :author");
		
		results.setParameter("author", "%" + author + "%");
		
		return (ArrayList<Book>) results.list();
		
		/*		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Books WHERE author LIKE ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, "%" + author + "%");
			
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
		
		return books;*/
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public List<Book> getBooksLessThanPrice(double price) {
		
		Session session = HibernateUtilities.openSession();
		Query results = session.createQuery("FROM Book WHERE price < :price");
		results.setParameter("price", price);
		
		return (ArrayList<Book>) results.list();
		
		/*		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Books WHERE price < ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setDouble(1, price);;
			
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
		
		return books;*/
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public Book getBookByISBN(String isbn) {

		Session session = HibernateUtilities.openSession();
		Query results = session.createQuery("FROM Book WHERE isbn13 = :isbn13");
		results.setParameter("isbn13", isbn);
		return (Book) results.uniqueResult();

	}


	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean addBook(Book book) {
	
		try {
			Session session = HibernateUtilities.openSession();
			Book b = new Book(book.getIsbn13(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getContent());
			session.save(b);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e) {
			return false;
		}

		
/*		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?)"; // Were using a lot of ?'s here...
			stmt = connection.prepareStatement(sql);
			
			// But that's okay, we can set them all before we execute
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setDate(4, Date.valueOf(book.getPublishDate()));
			stmt.setDouble(5, book.getPrice());
			
			stmt.setBytes(6, book.getContent());
			
			// If we were able to add our book to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}*/
}
	
	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean updateBook(Book book) {
		
		try {
			Session session = HibernateUtilities.openSession();
			Query query = session.createQuery("FROM Book WHERE isbn13 = :isbn13");
			query.setParameter("isbn13", book.getIsbn13());
			
			Book b = (Book) query.uniqueResult();
			b.setAuthor(book.getAuthor());
			b.setTitle(book.getTitle());
			b.setPrice(book.getPrice());
			
			session.save(b);
			session.getTransaction().commit();	
			return true;
		}
		catch (Exception e){
			return false;
		}
	
/*		Query query = session.createQuery("UPDATE Book SET author = :author, title = :title, price = :price WHERE isbn13 = :isbn13");
		query.setParameter("author", book.getAuthor());
		query.setParameter("title", book.getTitle());
		query.setParameter("price", book.getPrice());
		query.setParameter("isbn13", book.getIsbn13());
		int result = query.executeUpdate();
		session.getTransaction().commit();		
		
		return (result > 0);*/

/*		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE Books SET title=?, author=?, price=? WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setDouble(3, book.getPrice());
			stmt.setString(4, book.getIsbn13());
			
			System.out.println(stmt);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}*/
		
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean deleteBookByISBN(String isbn) {
		
		try {
			Session session = HibernateUtilities.openSession();
			Query query = session.createQuery("Delete from Book WHERE isbn13 = :isbn13");
			query.setParameter("isbn13", isbn);

			query.executeUpdate();
			session.getTransaction().commit();
			return true;			
		}
		catch(Exception e) {
			return false;
		}

				
/*		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE Books WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}*/
	}

	
	/*------------------------------------------------------------------------------------------------*/

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
