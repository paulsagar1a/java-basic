package thread.practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher  extends Thread{
	private Lock  chopStick1;
	private Lock  chopStick2;
	static int count =  1000;
	
	Philosopher(String name, Lock  chopStick1,    Lock  chopStick2) {
		this.chopStick1 = chopStick1;
		this.chopStick2 = chopStick2;
		this.setName(name);
	}
	
	public void run() {
		while(count>0) {
			chopStick1.lock();
			chopStick2.lock();
			try {
				if(count>0) {
					System.out.println(Thread.currentThread().getName()+" having food..."+count);
					count--;
				}
			} finally {
				chopStick2.unlock();
				chopStick1.unlock();
			}
		}
	}
}
public class DiningPhilosopherImpl {

	public static void main(String[] args) {
		 Lock  chopStickA = new ReentrantLock();
		 Lock  chopStickB = new ReentrantLock();
		 Lock  chopStickC = new ReentrantLock();
		 
		new Philosopher("Pluto",  chopStickA,  chopStickB).start();
		new Philosopher("Vivekananda", chopStickB, chopStickC).start();
		new Philosopher("Kante", chopStickA,chopStickC).start();

	}

}
