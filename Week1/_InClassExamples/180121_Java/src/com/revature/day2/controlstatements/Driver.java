package com.revature.day2.controlstatements;

import org.omg.Messaging.SyncScopeHelper;

import com.revature.day2.pillars.Mammalable;

public class Driver {

	public static void main(String[] args) {
		/*
		 * Control statements are any blocks of code that can control
		 * the flow of the application. IE, different branches, loops, etc.
		 */
		
		//Examples
		//For loop
		//for(incrementor; condition; increment block)
		
		for(int i = 5; i >0 ;i--){ //i-- i shorthand for i = i - 1; i -= 1;
			System.out.println(i);
		}

		/*
		 * (postfix)i++ says to use the variable i, then add on to it
		 * (prefix)++i says to add on to it, then use it;
		 * 
		 */
		for(int i = 5; i > 0; --i){ 
			System.out.println(i);
		}
		int j = 5;
		System.out.println(j++ + j++);
		System.out.println(j);
		
/*		for(;;){ //infinite loop
			System.out.println(j);
		}*/
		
		//Enhanced for loop, also known foreach loop.
		/*
		 * Note: Arrays are a collection of same typed values.
		 * Arrays are denoted by using square brackets anywhere in the reference.
		 * Long as it is after the datatype.
		 */
		int ints[] = new int[5];
		int ints2[] = {1,2,3,4,5};
		
		System.out.println("===ENHANCED FOR LOOP===");
		for(int item : ints2){
			System.out.println(item);
		}
		/*
		 * A while loop continues looping until the condition is not longer met
		 */
		System.out.println("===While loop===");
		int k = 0;
		while(k < 20){
			System.out.println(k++);
		}
		
		/*
		 * Do while loop
		 * 
		 * The difference between a Do while loop and a While loops is simply that
		 * the action will occur before the condition check.
		 */
		System.out.println("======DO WHILE=====");
		k = 0;
		do{
			System.out.println(k++);
		}while(k < 20 );
		
		
		/*
		 * Instead of using a ton of if/else statements, it usually is better to use
		 * a switch statement to cover multiple cases where different outcomes occur for 
		 * multiple different values.
		 */
		
		int option = 2;
		switch(option){
		case 1:
			System.out.println("case 1");
			break; //A break statement breaks out of any control statement entirely
		case 2:
			System.out.println("case 2");
			break;
		case 3:
			System.out.println("case 3");
			break;
		default:
			System.out.println("no matches");
			break;
		case 4:
			System.out.println("case 4");
			break;
		}
		
		System.out.println("===Continue vs break===");
		//break vs continue
		for(int l = 0; l < 100; l++){
			if(l==50){
				break; //Break, breaks out of the loop entirely
			}
			if(l%2==0){
				continue; //Continue, skips the current loop
			}
			System.out.println(l);
		}
		
		
	}

}
