package exweek5;

import com.revature.beans.Food;
import com.revature.dao.FoodDao;

public class Driver {

	public static void main(String[] args) {
		FoodDao fd = new FoodDao();
		fd.insertFood(new Food("Apple", "Fruit", 250));
		fd.insertFood(new Food("Carrot", "Vegetable", 400));
		fd.insertFood(new Food("Bobbert", "Entity", 100));
		Integer id = fd.insertFood(new Food("Bobbert", "Entity", 100));
		System.out.println(fd.insertFood(new Food("Bobbert", "Entity", 100)));
		System.out.println(fd.getFoodByIdViaCriteria(fd.insertFood(new Food("Bobbert", "Entity", 100))));
		
		System.out.println("===========");
		System.out.println(fd.getFoodById(id));
		
		System.out.println("Application ends.");
		System.exit(0);
	}

}
