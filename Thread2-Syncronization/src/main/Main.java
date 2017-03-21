package main;

public class Main {

	private int count = 0;
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.doWork();
		
	}
	
	public void increment(){
		synchronized (this) { //quicker than synchronizing the method 
			count++;
		}
	}
	
	public void doWork(){

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100000; i++) {
					increment();
				}
			}
		});
		

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100000; i++) {
					increment();
				}
			}
		});		
		long time = System.currentTimeMillis();
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		time = System.currentTimeMillis() - time;
		System.out.println("Count " + count + "; Time " + time);
	}

}
