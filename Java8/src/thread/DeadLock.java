package thread;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

	public static void main(String[] args) throws InterruptedException {
		Lock chopStickA = new ReentrantLock();
		Lock chopStickB = new ReentrantLock();
		Lock chopStickC = new ReentrantLock();
		
		//deadlock example
		//new DiningPhilosopher("Arun", chopStickA, chopStickB).start();
		//new DiningPhilosopher("Barun", chopStickB, chopStickC).start();
		//new DiningPhilosopher("Tarun", chopStickC, chopStickA).start();
		
		//to avoid deadlock, prioritize the chopsticks properly
		new DiningPhilosopher("Arun", chopStickA, chopStickB).start();
		new DiningPhilosopher("Barun", chopStickB, chopStickC).start();
		new DiningPhilosopher("Tarun", chopStickA, chopStickC).start();
	}

}

class DiningPhilosopher extends Thread {
	private String name;
	private Lock firstChopStick, secondChopStick;
	private static int sushiCount = 50000;
	
	public  DiningPhilosopher(String name, Lock firstChopStick, Lock secondChopStick) {
		this.name = name;
		this.firstChopStick = firstChopStick;
		this.secondChopStick = secondChopStick;
	}
	
	public void run() {
		while(sushiCount > 0) { //eat sushi until it finish
			//pick up chopsticks
			firstChopStick.lock();
			secondChopStick.lock();
			
			//take  a piece of sushi
			if(sushiCount > 0) {
				sushiCount--;
				System.out.println(name+" took a piece! Remaining: "+sushiCount);
			}
			
			try {
				//abandoned lock scenario
				//intentionally making an exception so that deadlock can occur.
				//the reason is because of the exception, the working thread can not be able to release/unlock the locks
				//so other thread will be waiting for the chopsticks for infinite amount of time, which is a deadlock
				if(sushiCount == 10) {
					System.out.println(1/0);
				}
				
				//to avoid  this kind of scenario always use 'finally' upon the unlock method
				
				//put down chopsticks
			} catch(Exception e){
				System.out.println("Exception "+e.getMessage());
			}finally {
				secondChopStick.unlock();
				firstChopStick.unlock();
			}
		}
	}
}
