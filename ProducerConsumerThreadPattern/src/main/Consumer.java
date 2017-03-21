package main;

import java.util.Vector;

public class Consumer  implements Runnable {

	private final Vector<Integer> sharedQueue;
	
	public Consumer(Vector<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {

		while(true){
			try {
				consume();
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void consume() throws InterruptedException {
		
		while(sharedQueue.isEmpty()){
			synchronized (sharedQueue) {
				System.out.println("No items to consume. Consumer is waiting...");
				//System.out.println("Thread '" + Thread.currentThread().getName() + "' before wait has status: " + Thread.currentThread().getState().toString());
				sharedQueue.wait();
				//System.out.println("Thread '" + Thread.currentThread().getName() + "' after wait has status: " + Thread.currentThread().getState().toString());
			}
		}
		
		synchronized (sharedQueue) {
			System.out.println("Consumed " + sharedQueue.remove(0));
			//System.out.println("Thread '" + Thread.currentThread().getName() + "' before notify has status: " + Thread.currentThread().getState().toString());
			sharedQueue.notifyAll();
			//System.out.println("Thread '" + Thread.currentThread().getName() + "' after notify has status: " + Thread.currentThread().getState().toString());
		
		}
		
	}
}
