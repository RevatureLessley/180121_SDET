package com.revature.day4.designpatterns;

/*
 * The factory design pattern aims to abstract the creation of a specific object
 * type to class instead of the user itself. This is useful for complex class types
 * that require a bit of configuration that can instead be taken from the user and
 * handled by a factory class.
 * This aims to make the creation and usage of complex classes easier for a user.
 */

public class ShapeFactory {
	public static Shape getShape(String shape){
		switch(shape.toLowerCase()){
		case "circle":
			return new Circle();
		case "triangle":
			return new Triangle();
		default:
			return null;
		}
	}
}
