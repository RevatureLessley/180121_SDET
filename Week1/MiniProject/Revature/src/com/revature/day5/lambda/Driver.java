package com.revature.day5.lambda;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PerformMath addition = (a,b) -> (a + b);
		PerformMath subtraction = (a,b) -> (a - b);
		

		
				
		System.out.println(addition.calculate(2, 5));
		System.out.println(subtraction.calculate(2, 5));
		
		
		
		
	}

}
