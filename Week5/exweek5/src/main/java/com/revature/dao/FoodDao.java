package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Food;
import com.revature.util.HibernateUtil;

public class FoodDao {
	public Integer insertFood(Food food) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Integer fid = null;
		
		try {
			tx = session.beginTransaction();
			fid = (Integer)session.save(food);
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return fid;
	}

	public List<Food> getAllFood() {
		List<Food> foods = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			foods = session.createQuery("FROM Food").list();
		} catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return foods;
	}
	
	public Food getFoodByIdViaCriteria(Integer id) {
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			food = (Food)session.createCriteria(Food.class)
					.add(Restrictions.idEq(id)).uniqueResult();
		} catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return food;
	}
	
	public Food getFoodById(Integer id) {
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			System.out.println("Grabbing the object for the first time");
			food = (Food)session.get(Food.class, id);
			System.out.println("Grabbing the object for the second time");
			food = (Food)session.get(Food.class, id);
			
			
		} catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return food;
	}
	
	public Food deleteFoodById(Integer id) {
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			food = (Food)session.get(Food.class, id);
			if(food!=null) {
				session.delete(food);
				tx.commit();
			}
			
		} catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return food;
	}
	
	public Food updateFoodPrice(Integer id, Integer newPrice) {
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			food = (Food)session.get(Food.class, id);
			if(food!=null) {
				food.setPrice(newPrice);
				session.update(food);
				tx.commit();
			}
			
		} catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return food;
	}
	
	public void getVsLoad() {
		Food food = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			System.out.println("==========GET VS LOAD==========");
			System.out.println("===GET===");
			food = (Food)session.get(Food.class, 1);
			System.out.println("===LOAD===");
			food = (Food)session.get(Food.class, 1);
			
		} catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
