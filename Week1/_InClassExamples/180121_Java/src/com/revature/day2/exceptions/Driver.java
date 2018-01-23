package com.revature.day2.exceptions;

import java.io.IOException;

import javax.crypto.ExemptionMechanismException;

public class Driver {

	public static void main(String[] args){
		Driver d = new Driver();
		//d.ohBoy();
		d.ohYay();
		//d.anotherException();
		//System.out.println(d.weirdStuff());

		try{
			d.propogate1();

		}catch(Exception e){
			System.out.println("Why did you throw so far!?");
		}

		
		System.out.println("End program!");
	}
	
	/*
	 * This method will throw an ArithmeticException,
	 * since we divide by zero.
	 * 
	 * An Exception can safely be described as an incident where something
	 * unexpected occurs.
	 * -When an application behaves in a manner it should not
	 * 
	 * An exception is NOT an error.
	 * Both are two separate classes.
	 * An Error can be described as:
	 * -An incident that cannot be reasonably recovered from.
	 * -Think: OutOfMemoryError and StackOverflowError
	 */
	public void ohBoy(){
		System.out.println(1/0);
		System.out.println("Made it to the end of the application!");
	}
	/*
	 * We can recover from exceptions.
	 * This is done in one of two ways:
	 * -try/catching the exception
	 * -ducking/propagating the exception
	 */
	
	//Catching the exception
	public void ohYay(){
		/*
		 * Place any code that you feel holds the risk of an exception withing
		 * the 'try' block.
		 */
		try{
			//If any line of code triggers an exception,
			//the flow of the application will immediatly reroute to
			//the catch block, if available
			System.out.println(1/0);
			System.out.println("I made it this far!");
		}catch(ArithmeticException e){
			System.err.println("Woops!");
			e.printStackTrace();
		}catch(NullPointerException e){
			
		}catch(RuntimeException e){
			
		}catch(Exception e){
			
		}
		/*
		 * The finally block will ALWAYS execute, no matter what.
		 * There are only two incidents that can occur where the finally block
		 * does NOT execute.
		 * -If an Error occurs
		 * -If you call System.exit(0)
		 */
		finally{ 
			System.out.println("FIIINALLY!");
		}
		System.out.println("Made it to the end of the application!");
		
	}
	
	public void anotherException(){
		try{
			//You can use 'throw' to invoke the exception of your choice.
			//This can be merely for testing purposes.
			throw new NullPointerException();
		}finally{
			/*
			 * You can have 0 catches, but you better have finally block in that case.
			 */
			System.out.println("FIIINALLY AGAIN");
		}
		//System.out.println("I will not make it to this point due to unhandled exception.");
	}
	
	public String weirdStuff(){
		try{
			throw new ExemptionMechanismException();
		}catch(ExemptionMechanismException e){
			e.printStackTrace();
			return "catch";
		}finally{
			System.out.println("Did I make it to this block?");
			return "finally";
		}
	}
	
	/*
	 * If you don't want to bother handling an exception, you can choose to 'duck' or more
	 * formally, propagate it.
	 * What this means, is that you are letting whatever method that called your risky code
	 * choose to handle the exception instead.
	 * 
	 * Why would we do this?
	 * We do this to tell java to calm down with checked exceptions.
	 * A checked exception, is any exception that is looked for at compile time and demands
	 * you code a handler for it before actually running the application.
	 */
	public void propogate1() throws IOException{
		propagate2();
	}
	
	public void propagate2() throws IOException{
		propagate3();
	}
	
	public void propagate3() throws IOException{
		throw new IOException();
	}

}
