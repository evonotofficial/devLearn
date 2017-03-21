package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args){
		
		System.out.println(Runtime.getRuntime().availableProcessors());
		ExecutorService exec = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i < 5; i++){
			exec.submit(new Processor(i));
		}
		
		exec.shutdown();
		
		try {
			exec.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished!");
	}
	
}


class Processor implements Runnable {
	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Starting " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed " + id);
	}
}