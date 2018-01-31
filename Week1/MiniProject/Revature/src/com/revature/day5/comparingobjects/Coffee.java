package com.revature.day5.comparingobjects;

public class Coffee implements Comparable<Coffee>{

	private int price;
	private String brand;
	private String tast;
	
	public Coffee(String string, String string2, int i) {
		// TODO Auto-generated constructor stub
	}
	public Coffee(){
		
	}
	@Override
	public String toString() {
		return "Coffee [price=" + price + ", brand=" + brand + ", tast=" + tast + "]";
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTast() {
		return tast;
	}
	public void setTast(String tast) {
		this.tast = tast;
	}
	@Override
	public int compareTo(Coffee coffee) {

		if(this.price > coffee.price){
			return 1;
		}
		if(this.price< coffee.price)
			return -1;
		return 0;
	}
	
	
}
