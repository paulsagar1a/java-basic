package thread.practice;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher2 extends Thread {
	Lock chopStick1;
	Lock chopStick2;
	static int count =1000;
	Random rps = new Random();//rock paper scissors
	public Philosopher2(String name, Lock lock1, Lock lock2) {
		chopStick1 = lock1;
		chopStick2 = lock2;
		this.setName(name);
	}
	
	public void run() {
		while(count>0) {
			chopStick1.lock();
			if(!chopStick2.tryLock()) {
				System.out.println(Thread.currentThread().getName()+" release 1st lock");
				chopStick1.unlock();
				try {
					Thread.sleep(rps.nextInt(3));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					if(count>0) {
						System.out.println(Thread.currentThread().getName()+" had "+count);
						count--;
					}
				} finally {
					chopStick2.unlock();
					chopStick1.unlock();
				}
			}
		}
	}
}

public class LiveLockDemo {

	public static void main(String[] args) {
		Lock chopStickA = new ReentrantLock();
		Lock chopStickB = new ReentrantLock();
		Lock chopStickC = new ReentrantLock();
		
		new Philosopher2("Arun", chopStickA, chopStickB).start();
		new Philosopher2("Arun", chopStickB, chopStickC).start();
		new Philosopher2("Arun", chopStickC, chopStickA).start();
	}

}
