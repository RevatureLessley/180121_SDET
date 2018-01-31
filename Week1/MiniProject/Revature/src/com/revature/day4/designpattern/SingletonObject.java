package com.revature.day4.designpattern;

public class SingletonObject {
	
	/*
	 * The main goal of a singleton design pattern is to provide a single 
	 * instance of a class that can be shared application wide. There should
	 * never be more then one instance of the class and there should never 
	 * be a scenario where the is replaced or recreated. 
	 */
	
	/*
	 * By making the constructor private we bar any user from explicitly 
	 * creating 'new' singleton objects themselves
	 */
	private static SingletonObject so;

	private SingletonObject(){
		System.out.println("Instance Created");
	}
	public static SingletonObject getSingleton(){
		if(so != null){
			so = new SingletonObject();
		}
		return so;
	}
}
