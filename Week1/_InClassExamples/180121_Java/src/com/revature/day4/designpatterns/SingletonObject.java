package com.revature.day4.designpatterns;

/*
 * The main goal of a singleton design pattern is to provide a single
 * instance of a class that can be shared application wide.
 * There should never be more than one instance of the class and there should never
 * be a scenario where the instance is replaced or recreated.
 */
public class SingletonObject {
	private static SingletonObject so;
	
	/*
	 * By making the constructor private we bar any user from explicitly
	 * creating 'new' singleton objects themselves
	 */
	private SingletonObject(){
		System.out.println("Instance Created!");
	}

	/*
	 * By making the getter static, we can access it without creating instance and we 
	 * also get to leave the act of creating instance reserved within the class itself.
	 * This ensures that we will only ever have 1 singleton throughout the application.
	 */
	public static SingletonObject getSingleton(){
		if(so == null){
			so = new SingletonObject();
		}
		return so;
	}
}
