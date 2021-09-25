package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionVariable {

	public static void main(String[] args) {
		for(int i=0; i<5; i++) {
			new HungryPersion(i).start();
		}
	}

}


class HungryPersion extends Thread {
	static int serving = 11;
	private int persionID;
	private static Lock slowCookerLid = new ReentrantLock();
	private static Condition soupTaken = slowCookerLid.newCondition();
	
	public HungryPersion(int persionID) {
		this.persionID = persionID;
	}
	
	@Override
	public void run() {
		while(serving > 0) {
			slowCookerLid.lock();
			try {
				while((persionID != serving % 2) && serving > 0) { //check if it is not your turn
					System.out.format("Persion %d checked... then put the lid back\n", persionID);
					soupTaken.await();
				}
				if(serving > 0) {
					serving --; //it's your turn, take some soup
					System.out.format("Persion %d took some soup! Servings left: %d\n",persionID, serving);
					Thread.sleep(500);
					soupTaken.signalAll();
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} finally {
				slowCookerLid.unlock();
			}
		}
	}
}