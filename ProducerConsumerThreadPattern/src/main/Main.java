package main;

import java.util.Vector;

public class Main {

	public static void main(String... args){
		
		Vector<Integer> sharedQueue = new Vector<Integer>();
		
		Thread pt = new Thread(new Producer(sharedQueue), "Produced");
		Thread ct = new Thread(new Consumer(sharedQueue), "Consumer");

		System.out.println("Thread '" + pt.getName() + "' has status: " + pt.getState().toString());
		System.out.println("Thread '" + ct.getName() + "' has status: " + ct.getState().toString());
		
		pt.start();
		ct.start();
	}
	
}
