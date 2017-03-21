package main;

import java.util.LinkedList;

public class App {

	public static void main(String[] args) {

		Processor pr = new Processor();
		
		Thread t1 = new Thread(() -> {
			try {
				pr.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(() -> {
			try {
				pr.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}
}


class Processor {
	
	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException{
		int value = 0;
		while(true){
			synchronized (lock) {
				while(list.size() >= LIMIT){
					lock.wait();
				}

				list.add(value++);
				lock.notify();
			}
		}
	}
	
	public void consume() throws InterruptedException{
		while(true){
			synchronized (lock) {
				while(list.size() == 0){
					lock.wait();
				}
				System.out.print("List size is " + list.size());
				int value = list.removeFirst();
				System.out.println("; Consumed value is " + value);	
				lock.notify();
			}
			
			Thread.sleep(30);
		}
	}
}