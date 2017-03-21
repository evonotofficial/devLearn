package main;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Processor pr = new Processor();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pr.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pr.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
	}

}

class Processor {
	
	public void produce() throws InterruptedException{
		synchronized(this){
			System.out.println("Produced.");
			wait();
			System.out.println("Produce resumed after consuming.");
		}
	}
	
	public void consume() throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Consuming in 2 sec...");
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("Consumed. Return key needed...");
			sc.nextLine();
			System.out.println("Consumer key pressed.");
			sc.close();
			notify();
			System.out.println("Consumer after notify.");
			Thread.sleep(4000);
		}
	}
	
}
