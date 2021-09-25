package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ConditionVariableDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread coordinator = new Persion("Co-ordinator", "Ashok Chandra");
		Thread member1 = new Persion("Member", "Satyajit Roy");
		Thread member2 =    new Persion("Member", "Parimal Das");
		Thread astPresident = new Persion("Ast. President", "Chandrakanta Basu");
		Thread president = new Persion("Leader", "Mukul Majhi");
		
		coordinator.start();
		member1.start();
		member2.start();
		astPresident.start();
		
		//let the president/leader start his thread after some  time
		Thread.sleep(3000);
		president.start();
		
		coordinator.join();
		member1.join();
		member2.join();
		astPresident.join();
		president.join();
		
		System.out.println("Meeting Conpleted!");
	}

}


class  Persion extends Thread {
	private String persionType;
	private String name;
	
	static Lock lock = new ReentrantLock();
	static Condition condition = lock.newCondition();
	
	public Persion(String persionType,String name) {
		this.persionType =   persionType;
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			lock.lock();
			if(!persionType.equalsIgnoreCase("LEADER")) {
				System.out.printf("%s, who is %s, is waiting for the leader.\n",name,persionType);
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(persionType.equalsIgnoreCase("LEADER")) {
				System.out.printf("%s, who is %s, has arrivedr.\n",name,persionType);
				Thread.sleep(2000); //signals all after 1 sec
				condition.signalAll();
			}
			
			//start other activity
			System.out.printf("%s starts meeting\n",name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}