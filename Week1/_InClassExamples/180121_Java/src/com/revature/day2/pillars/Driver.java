package com.revature.day2.pillars;

public class Driver {

	public static void main(String[] args) {
		System.out.println(Mammalable.i);
		
		
		Husky h1 = new Husky();
		System.out.println(h1.secret);
		System.out.println(h1.ear(2, 4.5f));
		System.out.println(h1.eat());
		System.out.println(h1.whatAmI());
		
		Animal h2 = new Husky();
		//Husky h3 = new Animal(); Not allowed, cast exception
		/*
		 * When you use covariance like above, you enforce a few rules on the instance.
		 * In this case, h2 will have ONLY the methods animal has.
		 * However, it will have Husky's implementation of them.
		 */
		System.out.println(h2.secret); //We use Animal's instance variable secret
//		System.out.println(h2.ear(2, 4.5f)); these methods do not exist in Animal itself
//		System.out.println(h2.eat());
		System.out.println(h2.whatAmI()); //We use Husky's implementation of whatAmI();
	}
	

}
