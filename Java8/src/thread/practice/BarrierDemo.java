package thread.practice;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ManWoman extends Thread {
	static int count = 0;
	static CyclicBarrier barrier = new CyclicBarrier(10);
	static Lock lock = new ReentrantLock();
	//static Object obj  =  new Object();
	ManWoman(String name) {
		this.setName(name);
	}
	
	public void run() {
		if(Thread.currentThread().getName().contains("Woman")) {
			lock.lock();
			System.out.println(Thread.currentThread().getName()+" "+count);
			count++;

			lock.unlock();
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.lock();
			System.out.println(Thread.currentThread().getName()+" "+count);
			count++;
			lock.unlock();
		}
	}
}


public class BarrierDemo {

	public static void main(String[] args) {
		for(int i=0;  i<10; i++) {
			if(i<5) {
				new ManWoman("Man"+i).start();
			} else {
				new ManWoman("Woman"+i).start();
			}
		}
	}

}
