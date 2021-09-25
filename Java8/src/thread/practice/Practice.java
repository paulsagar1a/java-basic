package thread.practice;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
class SemaThread  extends Thread {
    static Semaphore mutex = new Semaphore(3);
    SemaThread(String name) {
        this.setName(name);
    }
    
    public void run() {
        try{
        mutex.acquire();
        System.out.println(this.getName()  +" is charging");
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
        	System.out.println(this.getName()  +" done charging");
        mutex.release();
        }
    }
}
public class Practice {
	public static void main(String[] args) {
		for(int i=0; i< 10; i++) {
            new SemaThread("Thread_"+i).start();
        }
	}
}