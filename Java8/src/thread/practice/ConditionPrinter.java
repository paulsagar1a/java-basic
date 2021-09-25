package thread.practice;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class Printer extends Thread {
	static int max = 20;
	static int count  = 1;
	static Lock lock =  new ReentrantLock();
	static Condition condition = lock.newCondition();
	int num;
	
	Printer(int num) {
		this.num = num;
	}
	
	public void run() {
		while(count<max) {
			lock.lock();
			if(count%4 != num) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if(count<max) {
					System.out.println(Thread.currentThread().getName()+" "+count);
					count++;
				}
				condition.signalAll();
			} finally {
				lock.unlock();
			}
			
		}
	}
}

public class ConditionPrinter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Printer(0).start();
		new Printer(1).start();
		new Printer(2).start();
		new Printer(3).start();
	}

}
