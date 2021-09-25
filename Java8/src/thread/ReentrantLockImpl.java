package thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockImpl {

	public static void main(String[] args) {
		Thread t1 = new ReentrantThread();
		Thread t2 = new ReentrantThread();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e)  {
			System.out.println("InterruptedException");
		}
		
		System.out.println("Count = "+ReentrantThread.count);
	}

}


class ReentrantThread extends Thread {
	static int count = 0;
	//Note- 'static' is important, else ReentrantLock will be part of  ReentrantThread instance, 
	//hence it wont be able to lock the syntax
	static ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void run() {
		lock.lock();
		for(int i=0; i<100000; i++) {
			count++;
		}
		lock.unlock();
	}
}