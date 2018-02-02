package com.miniproject.beverages;

public class BeverageImpl implements Beverage {
	private String brandName;
	private String beverageType;
	
	@Override
	public String getBrandName() {
		return brandName;
	}
	
	@Override
	public String getBeverageType() {
		return beverageType;
	}
	
	
}
