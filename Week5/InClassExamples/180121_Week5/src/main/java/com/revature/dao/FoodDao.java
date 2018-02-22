package com.revature.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Food;
import com.revature.util.HibernateUtil;

public class FoodDao {
	public Integer insertFood(Food food){
		/*
		 * 	We use the session component to hold the connection to our database.
		 */
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Integer fid = null;
		
		try{
			//Open a transaction stream for our session.
			tx = session.beginTransaction();
			fid = (Integer)session.save(food);
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		return fid;
	}
}
