package thread;

class OddEven {
	final int max;
	int count;
	OddEven(int max) {
		this.max = max;
		count = 0;
	}
	
	void printOdd() {
		while(count<max) {
			synchronized(this) {
				while(count%2==0) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(count<max)
					System.out.println(Thread.currentThread().getName()+" - "+count);
				count++;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.notify();
			}
		}
	}
	
	void printEven() {
		while(count<max) {
			synchronized(this) {
				while(count%2 != 0) {
					try {
						this.wait();
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(count<max)
					System.out.println(Thread.currentThread().getName()+" - "+count);
				count++;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notify();
			}
		}
	}
}

public class OddEvenImpl {

	public static void main(String[] args) {
		OddEven oe = new OddEven(21);
		Thread odd = new Thread(() -> {oe.printOdd();});
		Thread even = new Thread(() -> {oe.printEven();});
		odd.start();
		even.start();
	}

}
