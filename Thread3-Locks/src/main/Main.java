package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	//Separate lock objects - best practice
	Object lock1 = new Object();
	Object lock2 = new Object();
	
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	private Random random = new Random();

	public static void main(String[] args) {

		Main main = new Main();

		long time = System.currentTimeMillis();
		System.out.println("Starting process...");

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				main.process();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				main.process();
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

		time = System.currentTimeMillis() - time;
		System.out
				.println("Time: " + time + "; List1: " + main.getList1().size() + "; List2: " + main.getList2().size());

	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stage1();
			stage2();
		}
	}

//	private synchronized void stage1() { //aquire lock of the main object - not parallel
	private void stage1() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}

//	private synchronized void stage2() { //aquire lock of the main object - not parallel
	private void stage2() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));

		}
	}

	public List<Integer> getList1() {
		return list1;
	}

	public List<Integer> getList2() {
		return list2;
	}

}
