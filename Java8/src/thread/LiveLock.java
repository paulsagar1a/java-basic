package thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

	public static void main(String[] args) {
		Lock chopStickA = new  ReentrantLock();
		Lock chopStickB = new  ReentrantLock();
		Lock chopStickC = new  ReentrantLock();
		
		new MrPhilosopher("Tina", chopStickA, chopStickB).start();
		new MrPhilosopher("Bina", chopStickB, chopStickC).start();
		new MrPhilosopher("Mina", chopStickC, chopStickA).start();
	}

}

class MrPhilosopher extends Thread {
	static int sushiCount = 1000;
	Lock firstChopStick, secondChopStick;
	String name;
	Random rps = new Random();//rock paper scissors
	
	public MrPhilosopher(String name, Lock firstChopStick, Lock secondChopStick) {
		this.name = name;
		this.firstChopStick = firstChopStick;
		this.secondChopStick = secondChopStick;
	}
	
	@Override
	public  void  run() {
		while(sushiCount > 0) {
			
			firstChopStick.lock();
			if(!secondChopStick.tryLock()) {
				System.out.println(name+" released firstChopStick");
				firstChopStick.unlock();
				try {
					Thread.sleep(rps.nextInt(3));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}  else  {
				try {
					if(sushiCount > 0) {
						sushiCount--;
						System.out.println(name+" took one piece! Remaining:   "+sushiCount);
					}
				} catch(Exception e) {
					System.out.println("Exception "+e.getMessage());
				} finally {
					secondChopStick.unlock();
					firstChopStick.unlock();
				}
			}
		}
	}
}
