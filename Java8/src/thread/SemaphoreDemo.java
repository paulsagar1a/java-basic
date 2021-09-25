package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class SemaphoreDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0; i<10;i++)   {
			new CellPhone("Phone-"+i).start();
		}
	}

}


class  CellPhone extends Thread {
	private static Semaphore charger = new Semaphore(3);
	
	public CellPhone(String name) {
		this.setName(name);
	}
	
	public void run() {
		try {
			charger.acquire();
			System.out.println(this.getName()+"  ischarging...");
			Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
		} catch(InterruptedException e)   {
			e.printStackTrace();
		} finally {
			System.out.println(this.getName()+" is done charging");
			charger.release();
		}
	}
}