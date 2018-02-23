package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Bear;
import com.revature.beans.Cave;
import com.revature.beans.HoneyPot;
import com.revature.util.HibernateUtil;

public class BearDao {
	public void insertBears() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Bear parent = new Bear(null,new HoneyPot(100,100), "brown", "grizzley", 30, 280);
		
		session.save(parent.getHoneyPot());
		
		// Child bear
		Bear child = new Bear(null, new HoneyPot(30,30), "blue", "aquatic", 3, 200000);
		
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
	}
}
