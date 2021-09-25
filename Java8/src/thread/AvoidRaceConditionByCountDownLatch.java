package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AvoidRaceConditionByCountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		//create 10 shoppers: Bali-0...4 and Pali-0...4
		Shopper3[]  shoppers  =  new Shopper3[10];
		for(int i=0;  i<shoppers.length/2; i++) {
			shoppers[2*i] = new Shopper3("Bali-"+i);
			shoppers[2*i+1] = new Shopper3("Pali-"+i);
		}
		
		for(Shopper3 s : shoppers) {
			s.start();
		}
		
		for(Shopper3 s: shoppers) {
			s.join();
		}
		
		System.out.println("\nWe need to buy "+Shopper3.bagsOfChips+" bags of  chips.");

	}

}

class Shopper3 extends Thread {
	public static int bagsOfChips  =  1;
	private static Lock pencil = new ReentrantLock();
	private static CountDownLatch fistBump = new CountDownLatch(5);
	
	public Shopper3(String name) {
		this.setName(name);
	}
	
	//here though we used locker  mechanism,  still unable to prevent the race condition
	public   void run() {
		if(this.getName().contains("Bali")) {
			pencil.lock();
			try {
				bagsOfChips = bagsOfChips+3;
				System.out.println(this.getName()+" added three bags of chips");
			} finally {
				pencil.unlock();
			}
			
			//wait rest of the execution until count down value  becomes   0
			fistBump.countDown();
		} else  {
			//wait for the execution of code line 64 to 71 for Pali-0...4 until count down value becomes 0
			try {
				fistBump.await();
			} catch(InterruptedException e)  {
				e.printStackTrace();
			}
			
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