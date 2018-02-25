package com.revature.main;

import com.revature.beans.Food;
import com.revature.dao.BearDao;
import com.revature.dao.FoodDao;

public class Driver {

	public static void main(String[] args) {
		FoodDao fd = new FoodDao();
		fd.insertFood(new Food("Apple", "Fruit", 250));
		fd.insertFood(new Food("Carrot", "Vegetable", 400));
		Integer id = fd.insertFood(new Food("Bobbert", "Entity", 100));
		
		System.out.println(
				fd.getFoodByIdViaCriteria(
						fd.insertFood(new Food("Bobbert", "Entity", 100))));
		
		System.out.println("=========");
		System.out.println(fd.getFoodById(id));
		System.out.println("=========");
		System.out.println(fd.getFoodById(id));
		
		
		//fd.getVsLoad();
		
		fd.HQLExample();
		fd.projections();
		
		BearDao bd = new BearDao();
		bd.insertBears();
		
		fd.executeNameQueries();
		System.out.println("Application ends.");
		System.exit(0);
	}

}
