package com.revature.day3.serialization;

import java.io.Serializable;

/*
 * Design Patter is a tried and true development style that was created to efficiently accomplish different tasks
 * 
 * An example of on pattern would be the POJO(Plain old java object). 
 * Characteristics of a POJO are simply a java object that just as fields and getters and setters.
 * The object isnt used to accomplish any task, but simply to host data.
 * 
 * 
 * There is another, more used design pattern called Beans.
 * A bean is just like a pojo in the fact that they are typically objects that have fields getters and setters.
 * In case of beans they are most restrictive in naming conventions. 
 * -getters and setters must be named getVarnme/setVarname. The camel casing must be accurate
 * as well. Beans also should have proper constructors, including the no args constructor. 
 */

/*
 * Serialization: 
 * 
 * In java we can serialize objects which essentially converts them to a byte code format for compressing them as files. 
 * This aids in sending them across servers to other applications or users where, assuming they the proper deserialization method,
 * can convert it back into and object for use. 
 * 
 * In short, we can convert java objects into files and convert said files back into objects mainitaining state. 
 * 
 * How do we serialize an object? 
 * -First step is to a have a class be candidate for serialization, by default objects can NOT be serialized. We need to have our 
 * candidates implement the MARKER INTERFACE serializable.
 * 
 *  -MARKER INTERFACE: 
 *   -- A marker interface is simply an interface with no methods whatsoever. 
 *   -- Serves only to mark a class as capable of something, in this case, serialization. 
 */
public class Person implements Serializable{
	
	

	private String fname;
	private String lname;
	private transient int id; // The transient keyword means this field will not be serialized instead a default val is given. 
	private String username;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Person(String fname, String lname, int id, String username) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.id = id;
		this.username = username;
	}
	public Person() {
		super();
	}
	@Override
	public String toString() {
		return "Person [fname=" + fname + ", lname=" + lname + ", id=" + id + ", username=" + username + "]";
	} 
	
	
	
}
