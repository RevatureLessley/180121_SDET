package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
	
	public List<Food> getAllFood(){
		List<Food> foods = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			/*
			 * For DQL, we have two options to use for accessing items.
			 * We can use HQL (Hibernate Query language) which is a semi abstracted
			 * SQL language that can be used to write DQL queries.
			 * HQL is notable for not need the 'select *' phrase since it is implied you will
			 * be grabbing all data to begin with, since you will be populating an object 
			 * representation anyway.
			 */
			foods = session.createQuery("FROM Food").list();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return foods;
	}
	
	public Food getFoodByIdViaCriteria(Integer id){
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			/*
			 * In addition to HQL, you can use a more object oriented approach, Criteria.
			 */
			
			food = (Food)session.createCriteria(Food.class)
					.add(Restrictions.idEq(id)).uniqueResult();
			/*
			 * With criteria, we pull data from a table dependent on which class we are
			 * populating. In this case, we are populating a food.class object, so we will pull
			 * from the table that food.class represents.
			 * In addition to this, we use .add() to apply a where condition to our criteria,
			 * in this case we apply '=' using the method ilike(), which in this case applies
			 * a LIKE between the colum f_id and the 'id' variable.
			 * NOTE: there is 'like' and'ilike'. 'ilike' is not case sensitive.
			 */
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return food;
	}

	public Food getFoodById(Integer id){
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			System.out.println("Grabbing the object for the first time...");
			food = (Food)session.get(Food.class, id);
			System.out.println("Grabbing the object for the second time...");
			food = (Food)session.get(Food.class, id);
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return food;
	}
	
	public Food deleteFoodById(Integer id){
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			food = (Food)session.get(Food.class, id);
			if(food!=null){
				session.delete(food);
				tx.commit();
			}
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return food;
	}
	
	public Food updateFoodPrice(Integer id, Integer newPrice){
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			food = (Food)session.get(Food.class, id);
			if(food!=null){
				food.setPrice(newPrice);
				session.update(food);
				tx.commit();
			}
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return food;
	}

	public void getVsLoad(){
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			
			
			tx = session.beginTransaction();
			System.out.println("========GET VS LOAD======");
			System.out.println("====GET====");
			food = (Food)session.get(Food.class, 1);
			System.out.println(food);
			System.out.println("====LOAD====");
			food = (Food)session.load(Food.class, 1);
			System.out.println(food);
			System.out.println("========GET VS LOAD=====NOT FOUND=======");
			System.out.println("====GET====");
			food = (Food)session.get(Food.class, 7000);
			System.out.println(food);
			System.out.println("====LOAD====");
			food = (Food)session.load(Food.class, 7000);
			System.out.println(food);
			System.out.println("========END GET VS LOAD======");
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	
}
