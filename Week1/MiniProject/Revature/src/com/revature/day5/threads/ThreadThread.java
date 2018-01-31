package com.revature.day5.threads;

/*
 * -Extending the Thread class
 * Since thread also implements runnable, this calss will transitively do so as well.
 */
public class ThreadThread extends Thread {

	/*
	 * In either case for creating a thread, you must implement the run() method. 
	 * This method contains the task your sperate thread will run when sent off in its merry way.
	 */
	@Override
	public void run() {
		
		for(int i =9; i < 20; i++){
			System.out.println("\t\t" + Thread.currentThread().getName());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
