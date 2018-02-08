package com.revature.day2.pillars;

public class Husky extends Dog implements Mammalable{
	public String secret = "Husky";
	/*
	 * NOTE: when you override a method, you may not change the return type.
	 * However, you MAY change the access modifier only if you are making it a more accessible
	 * one.
	 */
	@Override
	public boolean isDomesticated() {
		return true;
	}
	
	@Override
	public String getSpecies() {
		return "Husky";
	}
	
	@Override
	public String whatAmI() {
		return "Dude, im a husky...";
	}

	@Override
	public String speak() {
		return "Im a husky!";
	}

	@Override
	public String eat() {
		return "Eatin husky food";
	}

}
