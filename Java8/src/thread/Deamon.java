package thread;


public class Deamon {

	public static void main(String[] args) throws InterruptedException {
		Runnable rDeamon =  ()  -> {
			while(true) {
				System.out.println("GC cleaning the data!");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread deamonThread = new Thread(rDeamon, "DEAMON-THREAD");
		//setDeamon true to avoid the infinite loop
		deamonThread.setDaemon(true);
		deamonThread.start();
		
		System.out.println("Main Thread Executed");
		
	}

}