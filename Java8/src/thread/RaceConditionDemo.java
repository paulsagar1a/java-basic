package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceConditionDemo {

	public static void main(String[] args) throws InterruptedException {
		//create 10 shoppers: Jadu-0...4 and Madhu-0...4
		Shopper[]  shoppers  =  new Shopper[10];
		for(int i=0;  i<shoppers.length/2; i++) {
			shoppers[2*i] = new Shopper("Jadu-"+i);
			shoppers[2*i+1] = new Shopper("Madhu-"+i);
		}
		
		for(Shopper s : shoppers) {
			s.start();
		}
		
		for(Shopper s: shoppers) {
			s.join();
		}
		
		System.out.println("\nWe need to buy "+Shopper.bagsOfChips+" bags of  chips.");
	}

}

class Shopper extends Thread {
	public static int bagsOfChips  =  1;
	private static Lock pencil = new ReentrantLock();
	
	public Shopper(String name) {
		this.setName(name);
	}
	
	//here though we used locker  mechanism,  still unable to prevent the race condition
	public   void run() {
		if(this.getName().contains("Jadu")) {
			pencil.lock();
			try {
				bagsOfChips = bagsOfChips+3;
				System.out.println(this.getName()+" added three bags of chips");
			} finally {
				pencil.unlock();
			}
		} else  {
			pencil.lock();
			try {
				bagsOfChips = bagsOfChips*2;
				System.out.println(this.getName()+" doubled the bags of chips");
			} finally {
				pencil.unlock();
			}
		}
	}
}