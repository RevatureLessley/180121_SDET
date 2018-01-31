package com.revature.day3.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.activation.MimetypesFileTypeMap;
import javax.sound.sampled.spi.MixerProvider;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		//System.out.println("Input anything at all ... \n");
		//System.out.println(in.nextLine());
		int i;
	    while(true){
	    	String input = in.nextLine();
	    	if(input.equals("exit")){
	    		System.out.println("goodbye");
	    		break;
	    	}else{
	    		System.out.println(input);
	    	}
	    	i=0;
	    	System.out.println("Start Point");
	    	try {
	    	i = in.nextInt();
	    	}catch (InputMismatchException e){
	    		e.printStackTrace();
	    	}
	    	//parse out the first instance of the #
	    	if(i!=0){
	    		System.out.println("I Inputed the number: " + i);
	    	}
	    	if (i == -1){
	    		break;
	    	}
	    }
		
	}

}
