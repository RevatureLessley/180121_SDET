package com.revature.beans;

public class Food {
	private Integer id;
	private String name;
	private String type;
	private Integer price;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + "]";
	}
	public Food(Integer id, String name, String type, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	public Food(String name, String type, Integer price) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
	}
	public Food() {
		super();
	}
	
}
