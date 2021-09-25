package thread.practice;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
public class SemaphoreImpl {
	protected static class CellPhone extends Thread{
		private static Semaphore charger = new Semaphore(3);
		CellPhone(String name) {
			this.setName(name);
		}
		public void run() {
			try {
				charger.acquire();
				System.out.println(this.getName()+"  ischarging...");
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
			} catch(InterruptedException e) {
				System.out.println(e.getMessage());
			} finally {
				charger.release();
			}
		}
		
	}

	public static void main(String[] args) {
		for(int i=1; i<=10; i++) {
			new CellPhone("Cell_Phone_"+i).start();
		}
	}

}
