package examples.pubhub.utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.query.Query; //Current since 5.2

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookDAOImpl;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.ArrayList;

public class HibernateUtilities {
	static SessionFactory sessionFactory = null;
	static Session session = null;
	
	public static synchronized Session openSession() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		
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
		
		session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		return session;
	}
	
	public static synchronized void closeSession() {
		session.flush();
		session.close();
		sessionFactory.close();
		
	}
	
	public static BookDAO getBookDAO() {
		return new BookDAOImpl();
	}
	
	public static TagDAO getTagDAO() {
		return new TagDAOImpl();
	}

}
