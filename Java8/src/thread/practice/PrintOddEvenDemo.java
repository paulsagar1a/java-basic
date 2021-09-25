package thread.practice;

class OddEven {
	int max, i;
	OddEven(int max) {
		this.max = max;
		i=0;
	}
	
	public void printOdd() {
		//print odd numbers
		synchronized(this) {
			while(i<max) {
				if(i%2 == 1) {
					System.out.println(Thread.currentThread().getName()+" prints "+i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					i++;
					notify();
				}
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void printEven() {
		//print even numbers
		synchronized(this) {
			while(i<max) {
				if(i%2 == 0) {
					System.out.println(Thread.currentThread().getName()+" prints "+i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					i++;
					notify();
				}
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
public class PrintOddEvenDemo {
	
	public static void main(String[] args) throws InterruptedException {
		//oddThread calls printOdd method
		//evenThread calls printEven method
		
		//run on the same object OddEven 
		OddEven oe = new OddEven(11);
		Thread odd = new Thread(new Runnable() {
			public void run() {
				oe.printOdd();
			}
		});
		Thread even = new Thread(new Runnable() {
			public void run() {
				oe.printEven();
			}
		});
		
		odd.start();
		even.start();
		
		odd.join();
		even.join();
		
	}

}
