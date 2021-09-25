package thread;

class PrintNumbers extends Thread {
	final static int max = 10;
	static int count = 0;
	int reminder;
	static Object obj = new Object();
	PrintNumbers(String name, int reminder) {
		this.setName(name);
		this.reminder = reminder;
	}
	
	@Override
	public void run() {
		while(count<max) {
			synchronized(obj) {
				try {
					while(count%3 != reminder) {
						try {
							obj.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(count<max) {
						System.out.println(Thread.currentThread().getName()+" - "+count);
					}
					count++;
				} finally {
					obj.notifyAll();
				}
			}
		}
	}
}

public class PrintNumbersImpl {

	public static void main(String[] args) {
		Thread t1 = new PrintNumbers("Thread-1", 1);
		Thread t2 = new PrintNumbers("Thread-2", 2);
		Thread t3 = new PrintNumbers("Thread-3", 0);
		
		t1.start();
		t2.start();
		t3.start();
	}

}
