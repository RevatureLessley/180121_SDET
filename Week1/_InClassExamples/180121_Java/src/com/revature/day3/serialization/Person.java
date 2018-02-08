package com.revature.day3.serialization;

import java.io.Serializable;

/*
 * A design pattern is a tried and true development style that was created
 * to efficiently accomplish different tasks.
 * 
 * An example of one pattern would be the POJO (Plain old java object)
 * Characteristics of a POJO are simply a java object that just as fields
 * and getters and setters. The object isnt used to accomplish any task, but 
 * simply to host data.
 * 
 * On top of POJOs, there is another, more used design pattern call Beans.
 * A Bean is just like a POJO in the fact that they are typically objects that have 
 * fields and getters and setters. However, in the case of Beans, they are more restrictive
 * in naming conventions.
 * -getters and setters MUST be named getVarname/setVarname. The camel casing must be accurate
 * as well. Beans also should have proper constructors, including the no args constructor.
 */

/*
 * SERIALIZATION:
 * -In java we can serialize objects which essentially converts them to a byte code format 
 * compressing them as files. This aids in sending them across servers to other applications or
 * users where, assuming they have the proper de-serialization method, can convert it back into
 * and object for use.
 * -In short, we can convert java objects into files, and convert said files back into objects
 * maintaining state.
 * 
 * So how do we serialize an object?
 * -First step is to have a class be a candidate for serialization, by default objects can NOT be
 * serialized. We need to have our candidates implement the MARKER INTERFACE serializable.
 * 
 * -MARKER INTERFACE:
 * --A marker interface is simply an interface with no methods whatsoever.
 * --The interface serves ONLY to mark a class as capabable of something, in this case,
 * serialization.
 */
public class Person implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8539715653826427099L;
	private String fname;
	private String lname;
	//The transient keyword means this field will not be serialized.
	//Instead a default value is given in its place.
	private transient int id;
	private String username;
	
	public String getFname(){
		return fname;
	}
	public void setFname(String fname){
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (id != other.id)
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	//Disregard below
	
}
