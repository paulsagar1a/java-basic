package thread;

public class DataRace {
	public static void main(String[] args) {
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("count = "+MyThread.count);
	}
}


class MyThread extends Thread {
	static int count = 0;
	
	@Override
	public void run()   {
		for(int i=0; i<100000; i++) {
			count++;
		}
	}
}