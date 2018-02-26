package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	//SessionFactory is configured as a singleton.
	//We call the configure method of the Configuration component which
	//by default will use the "configure.cfg.xml" file to configure our factory.
	//If named differently, you will have to supply the custom name as a parameter for the
	//configure method.
	private static SessionFactory sessionFactory =
			new Configuration().configure().
			buildSessionFactory();
	
	public static Session getSession(){
		
		//Returns a new session instance from the session factory. (A new connection)
		return sessionFactory.openSession();
		
	}
}
