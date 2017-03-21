package main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(5);
		
		ExecutorService exec = Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < 5; i++) {
			exec.submit(new Processor(latch));	
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("Completed. Lathc size " + latch.getCount());
	}

}

class Processor implements Runnable {
	
	CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		System.out.println("Started...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
	}
}