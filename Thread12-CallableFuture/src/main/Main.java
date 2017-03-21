package main;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) {

		ExecutorService exec = Executors.newCachedThreadPool();
//		Runtime.getRuntime().availableProcessors();
		
		Future<?> f1 = exec.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				System.out.println("In thread one, sleeping for 3sec.");
				Thread.sleep(3000);
				throw new IOException("Do not like returning null");
			}
		});

		Future<Integer> f2 = exec.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("In thread 2, sleeping for 4sec.");
				Thread.sleep(4000);
				return 1024;
			}
		});

		Future<String> f3 = (Future<String>) exec.submit(() -> {});

		try {
			System.out.println("Thread 1 finished and result is " + f1.get());
			System.out.println("Thread 2 finished and result is " + f2.get());
			System.out.println("Thread 3 finished and result is " + f3.get());
		} catch (InterruptedException e){
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException ioe = (IOException) e.getCause();
			System.out.println(ioe.getMessage());
		}
	}

}
