package com.revature.day5.comparingobjects;

public final class Coffee implements Comparable<Coffee>{
	private String brand;
	private String taste;
	private int price;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Coffee [brand=" + brand + ", taste=" + taste + ", price=" + price + "]";
	}
	public Coffee(String brand, String taste, int price) {
		super();
		this.brand = brand;
		this.taste = taste;
		this.price = price;
	}
	public Coffee() {

	}
	@Override
	public int compareTo(Coffee coffee) {
		if(this.price > coffee.price)
			return 1;
		if(this.price < coffee.price)
			return -1;
		return 0;
	}
	/*
	 * Comparable defines natural ordering for objects.
	 * Therefore you need to implement the compareTo() method in the object's 
	 * class itself.
	 */
	
}
