package main;

import java.util.Vector;

public class Producer implements Runnable {

	private final Vector<Integer> sharedQueue;
	private final int size = 4;

	public Producer(Vector<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 12; i++) {
			try {
				preduce(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private void preduce(int i) throws InterruptedException {

		while (sharedQueue.size() == size) {
			synchronized (sharedQueue) {
				System.out.println("No space to produce. Producer is wainting...");
				//System.out.println("Thread '" + Thread.currentThread().getName() + "' before wait has status: " + Thread.currentThread().getState().toString());
				sharedQueue.wait();
				//System.out.println("Thread '" + Thread.currentThread().getName() + "' after wait has status: " + Thread.currentThread().getState().toString());
			}
		}
		
		synchronized (sharedQueue) {
			sharedQueue.add(i);
			System.out.println("Produced " + i);
			//System.out.println("Thread '" + Thread.currentThread().getName() + "' before notify has status: " + Thread.currentThread().getState().toString());
			sharedQueue.notifyAll();
			//System.out.println("Thread '" + Thread.currentThread().getName() + "' after notify has status: " + Thread.currentThread().getState().toString());
		}
	}

}
