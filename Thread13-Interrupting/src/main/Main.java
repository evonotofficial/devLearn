package main;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				for (int i = 0; i < 1000000; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Thread was interrupted.");
						break;
					}
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//					}
				}
				
			}

		});

		t1.start();

		Thread.sleep(10);

		t1.interrupt();

		t1.join();
		
//=================================================================
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Future<?> future = exec.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				for (int i = 0; i < 1000000; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Thread Callable<> was interrupted.");
						break;
					}
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//					}
				}
				return null;
			}
			
		});
		
		exec.shutdown();
//		exec.shutdownNow();
		future.cancel(true);
		
		System.out.println("Finished.");
	}

}
