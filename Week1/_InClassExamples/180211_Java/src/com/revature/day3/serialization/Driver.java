package com.revature.day3.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Driver {

	public static void main(String[] args) throws IOException{
		Person p1 = new Person("Bobbert","Bobson",1,"bbobby");
		System.out.println(p1);
		ObjectOutputStream oos = null;
		/*
		 * By default, objects when converted into a string, either using the
		 * toString() method or simply printing it to console, will print the object
		 * location in memory. Typically it goes:
		 * locationOfClass@locationInMemory.
		 * 
		 * Information like this is not very useful to the developer, so it behooves
		 * us to override any custom object's toString() method to give us better
		 * details.
		 */
		
		try{
			oos = new ObjectOutputStream(new FileOutputStream("person.ser"));
			oos.writeObject(p1);
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(oos!=null){
				oos.close();
			}
		}
		
		Driver d = new Driver();
		d.deserialize();
	}
	
	public void deserialize() throws IOException{
		ObjectInputStream ois = null;
		try{
			ois = new ObjectInputStream(new FileInputStream("person.ser"));
			Person p1 = (Person)ois.readObject();
			
			System.out.println(p1);
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			if(ois!=null){
				ois.close();
			}
		}
	}

}