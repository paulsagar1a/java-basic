package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicMutualExclusion {
	public static void main(String[] args) {
		Thread t1 = new AtomicThread();
		Thread t2 = new AtomicThread();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			System.out.println("InterruptedException!");
		}
		
		System.out.println("Count = "+AtomicThread.count);
	}
}


class AtomicThread extends Thread {
	static AtomicInteger count= new AtomicInteger(0);
	
	@Override
	public void run() {
		for(int  i=0; i<100000;i++) {
			count.incrementAndGet();
		}
	}
}