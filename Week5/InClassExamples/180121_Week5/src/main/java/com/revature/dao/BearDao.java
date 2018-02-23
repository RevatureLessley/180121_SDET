package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Bear;
import com.revature.beans.Cave;
import com.revature.beans.HoneyPot;
import com.revature.util.HibernateUtil;

public class BearDao {
	public void insertBears(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Bear parent = new Bear(
					null,
					new HoneyPot(100,100),
					"brown",
					"grizzley",
					30,
					280
				);
		session.save(parent.getHoneyPot());
		
		//Child bear!
		Bear child = new Bear(null,
								new HoneyPot(30,30),
								"blue",
								"aquatic",
								3,
								200000);
		
		parent.getCubs().add(child);
		session.save(child.getHoneyPot());
		
		Cave newHome = new Cave("Apple Cave", 215);
		parent.setDwelling(newHome);
		child.setDwelling(newHome);
		
		session.save(newHome);
		session.save(parent);
		session.save(child);
		tx.commit();
		session.close();
		
		session = HibernateUtil.getSession();
		System.out.println(((Bear)session.get(Bear.class, 50)).getHeight());
	}
	
	public void getVsLoad(){
		/*
		 * GET vs LOAD
		 * The key difference between thes3e two retrieval methods is that one
		 * can be considered lazy, while the other can be considered eager.
		 * GET: This is our eager retrieval example. GET will hit the database
		 * immediately and populate all the data on the spot for you. If no data exists
		 * it can provide 'null' in its place.
		 * Typically you want to use this if you are not sure that the object exists in the DB.
		 * 
		 * LOAD: This is considered our lazy retrieval. It provides back to us a proxy of the
		 * object.
		 * -A proxy is jsut a simple implementation of the object structure that is
		 * provided for us to use. This is why we have access to the object's getters and
		 * setters.
		 * The database is only hit when you need access to the actual data of the object.
		 * Since up to that point, hibernate assumed the object existed, and when it finds out
		 * that the data isn't actually there, we are greeted with the ObjectNotFound Exception.
		 */
		Session session = HibernateUtil.getSession();
		System.out.println("GETTING BEAR");
		Bear bear = (Bear)session.get(Bear.class, 50);
		System.out.println("GOT BEAR");
		
		System.out.println("=====GET NOT NULL======");
		System.out.println("PRINTING BEAR");
		System.out.println(bear);
		session = HibernateUtil.getSession();
		System.out.println("LOADING BEAR");
		bear = (Bear)session.load(Bear.class, 50);
		System.out.println("LOADED BEAR");
		System.out.println("=====Load NOT NULL======");
		System.out.println("PRINTING BEAR");
		System.out.println(bear);
		
		bear = (Bear)session.get(Bear.class, 500);
		System.out.println("=====GET NULL======");
		System.out.println(bear);
		bear = (Bear)session.load(Bear.class, 500);
		System.out.println("=====LOAD NULL======");
		System.out.println(bear);
	}
	
	public void saveVsPersist(){
		Bear bear = new Bear();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("BEAR ID BEFORE SAVE: " + bear.getBearId());
		session.save(bear);
		System.out.println("BEAR ID AFTER SAVE: " + bear.getBearId());
		
		bear = new Bear();
		System.out.println("BEAR ID BEFORE PERSIST: " + bear.getBearId());
		session.persist(bear);
		System.out.println("BEAR ID AFTER PERSIST: " + bear.getBearId());
		
		bear = new Bear();
		session.save(bear);
		bear = new Bear();
		session.persist(bear);
		Integer persistID = bear.getBearId();
		bear = new Bear();
		session.save(bear);
		bear = new Bear();
		Integer saveID = (Integer)session.save(bear);
		
		System.out.println("persistID: " + persistID);
		System.out.println("saveID: " + saveID);
		
		
		
		tx.commit();
		
		/*
		 * SAVE vs PERSIST
		 * -They are neither lazy or eager, but could be compared to it.
		 * -When you can save on an object, you persist it with the database.
		 * -Therefore, when calling save, you are returned a number that represents
		 * its unique record in the database.
		 * -HOWEVER, with persist it is not insterted into the database right away.
		 * A position is saved for the insert, it just doesn't have an ID yet.
		 * The ID is assigned to the new record 'eventually', just not right away.
		 * -The only gaurantee you get is that the data will  be completely inserted
		 * before flush time at the latest. (Flush time means the point where the
		 * database and persistent object are the same.)
		 */
	}
	
	public void updateVsMerge(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Bear bear1 = new Bear();
		bear1.setType("Bobbert");
		//At this point the persistent object is in the TRANSIENT state
		
		session.save(bear1);
		//At this point the persistent object is in the PERSISTENT state
		
		tx.commit();
		session.close();
		//At this point the persistent object is in the DETACHED state
		
		System.out.println(bear1.getBearId());
		session = HibernateUtil.getSession(); //NEW SESSION
		tx = session.beginTransaction();
		Bear bear2 = new Bear();
		bear2 = (Bear)session.get(Bear.class, bear1.getBearId());
		//bear2 now represents the specific spot in the database
		//bear1 is still detached.
		System.out.println(bear2.getType());
		bear1.setType("polar");
		//session.update(bear1); //Throws NonUniqueObjectException
		
		session.merge(bear1);
		System.out.println(bear2.getType()); //claims polar
		
		
		tx.commit();
		session.close();
		
		bear2.setType("Final change");
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		session.update(bear2);
		tx.commit();
		session.close();
		
		Integer bearid = bear2.getBearId();
		Bear bear3 = new Bear();
		bear3.setBearId(bearid);
		System.out.println("============");
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		session.update(bear3);
		tx.commit();
		System.out.println(((Bear)session.get(Bear.class, bearid)).getType());
		session.close();
	
		
		/*
		 * Merge vs Update
		 * -Update and merge will re-attach a detached object to a persistent state.
		 * -Update throws an exception if you try to invoke it on an object
		 * when the session already has a persistent object for that record.
		 * -Merge will follow through with the update by taking the contents of the object
		 * you are trying to update with and overwriting the contents of the current
		 * persistent object with its own.
		 * 
		 */
	}
}
