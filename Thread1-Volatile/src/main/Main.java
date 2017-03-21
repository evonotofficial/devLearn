package main;

import java.util.Scanner;

public class Main {
	
	public static void main (String[] args){
		
		Processor pr = new Processor();
		pr.start();

		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		
		pr.setShutdown(true);
	}
	
}

class Processor extends Thread {
	
	private volatile boolean shutdown;
	
	@Override
	public void run() {
		while(!shutdown){
			
			System.out.println("Hello!");
			
		}
	}

	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}
	
}