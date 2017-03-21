package main;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		
		Processor pr = new Processor();
		
		Thread t1 = new Thread(() -> {
			try {
				pr.firstThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(() -> {
			try {
				pr.secondThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pr.finially();
	}

}

class Processor {
	
	private int value;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment(){
		for (int i = 0; i < 10000; i++) {
			value++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		lock.lock();
		System.out.println("In first.");
		cond.await();
		System.out.println("Continue first thread...");
		try {
			increment();
		} finally {
			lock.unlock();
		}
		
	}
	
	public void secondThread() throws InterruptedException{
		System.out.println("In second.");
		Thread.sleep(1000);
		System.out.println("In second before lock.");
		lock.lock();
		
		System.out.println("Press enter...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		
		cond.signal();
		System.out.println("Continue second thread...");
		try {
			increment();
		} finally {
			lock.unlock();
		}
	}
	
	public void finially(){
		System.out.println("Value is " + value);
	}
}