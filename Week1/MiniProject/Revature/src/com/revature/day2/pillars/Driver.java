package com.revature.day2.pillars;

public class Driver {

	public static void main(String[] args){
		
		System.out.println(Mammals.i);
		
		Husky h1 = new Husky();
		System.out.println(h1.secret);
		System.out.println(h1.ear(2));
		System.out.println(h1.eat());
		
		Animals h2 = new Husky();
		/*
		 * Husky h3 = new Animal(); Not allowed, cast exception
		 * 
		 * When you u use covriance like above, you enforce a few  rules on the instance 
		 * In this case, h2 will have only the methods animal has.
		 * However, it wil have husky implementation of them
		 * 
		 */
		System.out.println(h2.secret); // we use animals instance variable secret
//		System.out.println(h1.ear(2));
//		System.out.println(h1.eat());
		System.out.println(h2.whatAmI()); // we use huskys implementation of whatAmI();
		
	}
}
