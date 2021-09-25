package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDemo {

	public static void main(String[] args) {
		BlockingQueue servingLine = new ArrayBlockingQueue<String>(5);
		
		new SoupConsumer(servingLine).start();
		new SoupConsumer(servingLine).start();
		new SoupProducer(servingLine).start();

	}

}


class SoupProducer extends Thread {
	private BlockingQueue servingLine;
	
	public SoupProducer(BlockingQueue servingLine) {
		this.servingLine = servingLine;
	}
	
	@Override
	public void run() {
		for(int i=0; i<20; i++) { //serve 20 bowls of soup
			try {
				servingLine.add("Bowl #"+i);
				System.out.format("Served bowl #%d - remaining capacity: %d\n",i, servingLine.remainingCapacity());
				Thread.sleep(200); //time to serve a bowl of soup
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		servingLine.add("No soup for you");
		servingLine.add("No soup for you");
	}
}

class SoupConsumer extends Thread {
	private BlockingQueue servingLine;
	
	public SoupConsumer(BlockingQueue servingLine) {
		this.servingLine = servingLine;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String bowl =  (String) servingLine.take();
				if(bowl == "No soup for you")  {
					break;
				}
				System.out.format("Ate %s\n",bowl);
				Thread.sleep(300); //time to eat the bowl of soup
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}