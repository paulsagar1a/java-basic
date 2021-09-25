package thread;
import java.util.concurrent.locks.ReentrantLock;
public class TryLockImpl {

	public static void main(String[] args) {
		Thread t1 = new Party();
		Thread t2 = new Party();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		
		System.out.println(Party.t0gwr +" "+Party.t1gwr);
		System.out.println("Party Finished");
	}

}


class Party extends Thread {
	static ReentrantLock lock = new ReentrantLock();
	static int t0gwr = 0;
	static int t1gwr = 0;
	@Override
	public void run() {
		for(int i=0; i<10;  i++) {
			if(lock.tryLock()) {
				if(Thread.currentThread().getName().equalsIgnoreCase("Thread-0")) {
					t0gwr++;
				} else  {
					t1gwr++;
				}
				System.out.println(Thread.currentThread().getName()+" in washroom");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("InterruptedException");
				} finally {
					lock.unlock();
				}
			}
			System.out.println(Thread.currentThread().getName()+" Eating & Dancing");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
			}
		}
	}
}