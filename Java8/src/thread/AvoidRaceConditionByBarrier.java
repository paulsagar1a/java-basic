package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AvoidRaceConditionByBarrier {

	public static void main(String[] args) throws InterruptedException {
		//create 10 shoppers: Ram-0...4 and Sham-0...4
		Shopper2[]  shoppers  =  new Shopper2[10];
		for(int i=0;  i<shoppers.length/2; i++) {
			shoppers[2*i] = new Shopper2("Ram-"+i);
			shoppers[2*i+1] = new Shopper2("Sham-"+i);
		}
		
		for(Shopper2 s : shoppers) {
			s.start();
		}
		
		for(Shopper2 s: shoppers) {
			s.join();
		}
		
		System.out.println("\nWe need to buy "+Shopper2.bagsOfChips+" bags of  chips.");
	}

}


class Shopper2 extends Thread {
	public static int bagsOfChips  =  1;
	private static Lock pencil = new ReentrantLock();
	private static CyclicBarrier fistBump = new CyclicBarrier(10);
	
	public Shopper2(String name) {
		this.setName(name);
	}
	
	//here though we used locker  mechanism,  still unable to prevent the race condition
	public   void run() {
		if(this.getName().contains("Ram")) {
			pencil.lock();
			try {
				bagsOfChips = bagsOfChips+3;
				System.out.println(this.getName()+" added three bags of chips");
			} finally {
				pencil.unlock();
			}
			
			//wait rest of the execution until barrier value  becomes   0
			try {
				fistBump.await();
			} catch(InterruptedException | BrokenBarrierException e)  {
				e.printStackTrace();
			}
		} else  {
			//wait for the execution of code line 64 to 71 for Sham-0...4 until barrier value becomes 0
			try {
				fistBump.await();
			} catch(InterruptedException | BrokenBarrierException e)  {
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