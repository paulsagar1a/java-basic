package thread.practice;

class  MyDeamonThread extends Thread {
	public void run() {
		while(true)    {
			System.out.println("MydeamonThread is running...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class MyDeamonImpl {

	public static void main(String[] args) throws InterruptedException {
		MyDeamonThread thread = new MyDeamonThread();
		thread.setDaemon(true);
		System.out.println(thread.isDaemon());
		thread.start();
		//thread.join();
		
		System.out.println("Main Thread complets.");

	}

}
