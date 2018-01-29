package com.miniproject.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.miniproject.users.User;
import com.miniproject.util.InputReader;

public class TypingGame {
	List<Integer> level01time = new ArrayList<>();
	List<String> level01str = new ArrayList<>();
	User player;
	int points;
	
	public TypingGame(User inPlayer) {
		this.player = inPlayer;
		this.points = 0;
	}
	
	public void playGame() {
		System.out.println("Select Level: ");
		int lvl = InputReader.readInt("Select Level: ");
		switch(lvl) {
		case 1:
			generatelvl01();
			typing(lvl);
			break;
		}
	}
	
	/*
	 * A game where you must type words within the time limit to get points
	 * These points would translate to money that would be added to the users account
	 * 1 point == 0.01
	 * This method doesn't work because of threading
	 */
	private void typing(int inLvl) {
		/*
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		for(String s : level01str) {
			Callable<String> aProgram = () -> {
				System.out.print("TYPE " + s);
				String ans = InputReader.readString();
				return ans;
			};
			
			final Future<String> handler = executor.submit(aProgram);
			executor.schedule(new Runnable() {
				public void run() {
					handler.cancel(true);
					if(handler.isCancelled()) {
						System.out.println("!--NEXT--!");
					} else {
						try {
							if(handler.get().equals(s)){
								points++;
							}
						} catch(ExecutionException | InterruptedException e) {
							e.printStackTrace();
						}
						
					}
					
				}
			}, level01time.get(level01str.indexOf(s)), TimeUnit.MILLISECONDS);
		}
		
		System.out.println("LEVEL END! --- GOOD JOB!");
		*/
	}
	
	private void generatelvl01() {
		level01str.add("Java");
		level01str.add("Console");
		level01str.add("Driver");
		level01str.add("Library");
		level01str.add("README");
		level01time.add(4000);
		level01time.add(4000);
		level01time.add(4000);
		level01time.add(4000);
		level01time.add(4000);
	}
}
