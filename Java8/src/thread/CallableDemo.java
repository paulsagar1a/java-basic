package thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("Sumit ask Manik what is the vote percentage.");
		ExecutorService executor  = Executors.newSingleThreadExecutor();
		Future<Integer> result = executor.submit(new CountVotes());
		System.out.println("Manik responded with "+result.get());
		executor.shutdown();
	}

}

class  CountVotes implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("Manik is counting the votes percentage...");
		Thread.sleep(5000);
		return (new Random().nextInt(1000)*100)/1000;
	}
	
}