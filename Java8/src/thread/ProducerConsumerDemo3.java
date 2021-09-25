package thread;

import java.util.LinkedList;

public class ProducerConsumerDemo3 {

	public static void main(String[] args) throws InterruptedException {
		PanipuriShop panipuriShop = new PanipuriShop(10);
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					panipuriShop.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		Thread consumer  = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					panipuriShop.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		producer.start();
		consumer.start();
		
		producer.join();
		consumer.join();
		
		System.out.println("Consumer Pays Rs. 20.");
	}

}


class PanipuriShop {
	private LinkedList<Integer> list;
	private int plateCapacity;
	private int panipuriServed, panipuriAte;
	
	public PanipuriShop(int totalPanipuri)  {
		list = new LinkedList<>();
		plateCapacity = 2;
		this.panipuriServed =  this.panipuriAte = totalPanipuri;
	}
	
	public synchronized void producer() throws InterruptedException {
		while(panipuriServed>0) {
			if(list.size()==plateCapacity) {
				System.out.printf("Producer is waiting with the %d-th panipuri\n",panipuriServed);
				wait();
			}
			
			list.add(panipuriServed);
			panipuriServed--;
			System.out.printf("Producer served one panipuri, remaining: %d\n",panipuriServed);
			notify();
			
			//to make output more understandable
			Thread.sleep(2000);
		}
	}
	
	public synchronized void consumer() throws InterruptedException {
		while(panipuriAte>0) {
			if(list.size()==0) {
				System.out.printf("Consumer is waiting with the %d-th panipuri\n",panipuriAte);
				wait();
			}
			
			list.removeFirst();
			panipuriAte--;
			System.out.printf("Consumer ate one panipuri, remaining: %d\n",panipuriAte);
			notify();
			
			//to make output more understandable
			Thread.sleep(2000);
		}
	}
	
}