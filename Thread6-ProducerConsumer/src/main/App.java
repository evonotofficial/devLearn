package main;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void produce() throws InterruptedException{
		Random ran = new Random();
		while(true){
			queue.put(ran.nextInt(100));
		}
	}
	
	private static void consume() throws InterruptedException{
		Random ran = new Random();
		while(true){
			Thread.sleep(100);
			
			if(ran.nextInt(10) == 0){
				Integer value = queue.take();
				
				System.out.println("Removed " + value + "; Queue size is " + queue.size());
			}
		}
	}
}
