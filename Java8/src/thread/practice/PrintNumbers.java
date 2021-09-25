package thread.practice;

class Print extends Thread {
	static Object object = new Object();
	final static int MAX_NUMBERS = 10;
	int reminder;
	static int count = 0;
	
	Print(int reminder) {
		this.reminder = reminder;
	}
	
	@Override
	public  void  run()   {
		while(count<MAX_NUMBERS) {
			synchronized(object) {
				while(count%3 != reminder) {
					try {
						object.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(count<MAX_NUMBERS) {
					System.out.println("Thread-"+reminder+" "+count);
				}
				count++;
				object.notifyAll();
			}
		}
	}
	
}

public class PrintNumbers {
	  public static void main(String[] args) throws InterruptedException {
		  Thread t1 = new Print(0);
		  Thread t2 = new Print(1);
		  Thread t3 = new Print(2);
		  t1.start();
		  t2.start();
		  t3.start();
	  }

}
