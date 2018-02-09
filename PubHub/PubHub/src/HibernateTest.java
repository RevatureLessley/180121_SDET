import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.ArrayList;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.HibernateUtilities;
import examples.pubhub.dao.*;


/*
 * Tasks to resolve before submitting:
 * Ensure good memory management
 * Implement Hibernate into Tag pages (maybe not)
 * 
 */
public class HibernateTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtilities.openSession();
		Book b = new Book();
		BookDAOImpl BookDAO = new BookDAOImpl();
		b = BookDAO.getBookByISBN("1111111111113");
		System.out.println(b);
		
		
//		session.save(b);
		session.getTransaction().commit();	
		HibernateUtilities.closeSession();			
/*		Session session = HibernateUtilities.openSession();
		
		Book b = new Book("1111111111120", "Sherry", "Sherry's Homecooking");
		
		Query query = session.createQuery(
			    "insert into Book (isbn13, author, title) " +
			    "select :isbn13, :author, :title " +
			    "from Book b ");
		query.setParameter("isbn13", b.getIsbn13());
		query.setParameter("author", b.getAuthor());
		query.setParameter("title", b.getTitle());
		
		int result = query.executeUpdate();		
		session.getTransaction().commit();
		session.close();
		HibernateUtilities.closeSession();*/
		
/*		for (Book b : books)
			System.out.println(b.toString());
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;
		
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			System.out.println("Could not create connection!");
			e.printStackTrace();
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		Query results = session.createQuery("FROM Book");
		query.setParameter("newAuthor", "Miranda Carey");
		query.setParameter("isbn", "1111111111113");

		String queryString = "INSERT INTO Tag (isbn13, tag) select ";
		Query query2 = session.createQuery("INSERT INTO Tag (isbn13, tag)");
		query2.setParameter("newTag", "Hibernate");
		query2.setParameter("isbn", "1111111111114");
		ArrayList<Book> books = (ArrayList<Book>) results.list();
		
		for (Book b : books) {
			System.out.println(b.getAuthor());
		}

		query.executeUpdate();
		
		session.getTransaction().commit();		
		session.close();
		
		query = session.createQuery("INSERT INTO SecurityClearance(clearanceID, name) SELECT employeeID, name FROM Employee WHERE name=:empName");
		query.setParameter("empName", "Ryan");
		
		query.executeUpdate();
*/		

	}

}
