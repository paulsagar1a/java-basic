package thread;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Starvation {

	public static void main(String[] args) {
		Lock chopStickA = new ReentrantLock();
		Lock chopStickB = new ReentrantLock();
		Lock chopStickC = new ReentrantLock();
		
		for(int  i=0; i<500;  i++) {
			new Philosopher("Ram", chopStickA, chopStickB).start();
			new Philosopher("Sham", chopStickA, chopStickB).start();
			new Philosopher("Jadu", chopStickA, chopStickB).start();
		}
	}

}

class Philosopher extends Thread {
	static int sushiCount = 1000;
	Lock firstChopStick, secondChopStick;
	String name;
	int sushiEaten =  0;
	
	public Philosopher(String name, Lock firstChopStick, Lock secondChopStick) {
		this.name = name;
		this.firstChopStick = firstChopStick;
		this.secondChopStick = secondChopStick;
	}
	
	
	@Override
	public void run() {
		while(sushiCount > 0) {
			try {
				firstChopStick.lock();
				secondChopStick.lock();
				
				if(sushiCount > 0) {
					sushiCount--;
					sushiEaten++;
					System.out.println(name+" took a sushi! Remaining: "+sushiCount);
				}
			
			}finally {
				firstChopStick.unlock();
				secondChopStick.unlock();
			}
		}
		System.out.println(name+" took "+sushiEaten);
	}
}
