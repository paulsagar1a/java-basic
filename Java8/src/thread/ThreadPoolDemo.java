package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
	static ExecutorService pool = null;
	public static void main(String[] args) {
		int numProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println(numProcessors);
		pool =  Executors.newFixedThreadPool(1);
		
		for(int i=0; i<10;i++)   {
			//submit the threads into the pool
			pool.submit(new VegetableChopper());
		}
		
		pool.shutdown();

	}
}

class VegetableChopper  extends Thread {
	public void run()  {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" chopped a vegetable!");
	}
}