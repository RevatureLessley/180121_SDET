package com.revature.day4.designpattern;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonObject so1 =  SingletonObject.getSingleton();
		SingletonObject so2 =  SingletonObject.getSingleton();
		
		System.out.println(so1);
		System.out.println(so2);

	}

}
