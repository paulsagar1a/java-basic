package thread.practice;

import java.util.LinkedList;
class PanipuriShop {
	LinkedList<Integer> list;
	int size = 3;
	int served,consumed;
	
	PanipuriShop(int n) {
		list = new LinkedList<>();
		served = consumed = n;
	}
	
	public void produce() {
		while(served>0) {
			synchronized(this) {
				while(list.size() == size) {
					System.out.println(Thread.currentThread().getName()+" waiting to produce...");
					try {
						wait();
					} catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
				list.add(served);
				System.out.printf("Panipuri %d is served.\n",served);
				served--;
				
				notify();
			}
		}
	}
	
	public void consume() {
		while(consumed>0) {
			synchronized(this) {
				while(list.size() == 0) {
					System.out.println(Thread.currentThread().getName()+" waiting to consume...");
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				list.removeFirst();
				System.out.printf("Panipuri  %d is consumed\n",consumed);
				consumed--;
				
				notify();
			}
		}
	}
	
}
public class ProducerConsumerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PanipuriShop pshop = new  PanipuriShop(100);
		Thread produce = new Thread(new Runnable() {

			@Override
			public void run() {
				pshop.produce();
			}
			
		});
		Thread consume = new Thread(new Runnable() {

			@Override
			public void run() {
				pshop.consume();
			}
			
		});
		
		produce.start();
		consume.start();
	}

}
