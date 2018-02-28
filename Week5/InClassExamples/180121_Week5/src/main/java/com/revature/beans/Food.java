package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity //Mark the class as a persistent class
@Table(name="FOOD") //Configuration details for the SQL table
public class Food {
	
	@Id //Marks the primary key
	@Column(name="f_id")
	@SequenceGenerator(sequenceName="MY_SEQ", name="JAVA_NAME")
	@GeneratedValue(generator="JAVA_NAME", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column
	private String name;
	@Column
	private String type;
	@Column
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
