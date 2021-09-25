package thread;

public class SynchronizedMutualExclusion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 =  new SynchronizedThread();
		Thread t2  = new SynchronizedThread();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e)  {
			System.out.println("InterruptedException!");
		}
		
		System.out.println("Count = "+SynchronizedThread.count);
	}

}


class SynchronizedThread extends Thread {
	static int count = 0;
	
	//method synchronized 
	//Note: the static key word in this method is must, 
	//else thread will execute the method in the Thread Instance level.
	//Hence it could not be able to avoid race condition
	static synchronized int increment() {
		return count++;
	}
	
	@Override
	public void run() {
		for(int i=0; i<100000; i++) {
			//increment();
			
			//implement synchronized block. Here we need to block the class using reflection API.
			//If we use 'this' keyword it wont work, as 'this' is a instance level implementation
			//synchronized(this) { //unable to avoid race condition
			synchronized(SynchronizedThread.class) {
				count++;
			}
		}
	}
}