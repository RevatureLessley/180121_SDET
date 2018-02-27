package com.revature.day4.designpatterns;

public class Driver {

	public static void main(String[] args) {
		
		
		SingletonObject so1 = SingletonObject.getSingleton();
		SingletonObject so2 = SingletonObject.getSingleton();
		System.out.println(so1);
		System.out.println(so2);
		
		System.out.println("=========");
		
		System.out.println(ShapeFactory.getShape("circle").draw());
		System.out.println(ShapeFactory.getShape("triangle").draw());
		System.out.println(ShapeFactory.getShape("square").draw());
		
	}

}
