package com.revature.day5.threads;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread t1 = new Thread(new RunnableThread(), "Thread1");
		Thread t2 = new Thread(new ThreadThread(), "Thread2");
		
		System.out.println("Beginning Exe");
		t1.start();
		t2.start();
		System.out.println("Ending Exec");
	}

}
