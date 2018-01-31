package com.revature.day2.pillars;

public class Husky extends Dog implements Mammals{

	public String secret = "Husky";
	/*
	 *When you override a method, you may not change the return type. 
	 *However you may change the access modifier only if you are making it more accessible. 
	 */
	@Override
	public boolean isDomesticated() {
		
		return true;
	}

	@Override
	public String getSpecies() {
		return "husky";
	}
	
	@Override
	public String whatAmI() {
		return "dog";
	}

	@Override
	public String speak() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String eat() {
		// TODO Auto-generated method stub
		return null;
	}
}
