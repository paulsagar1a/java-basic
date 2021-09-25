package thread.practice;
/*Process	 Time	dependent_process
P1		 	  2		_	
P2		 	  5		P3
P3		 	  3		P1
P4		 	  1		P5
P5		 	  6		_
P6		 	  1		_

Two threads can run asynchronously

P6 _
P1 _
P5 _
P2 P3
P3 P1
P4 P5
*/

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

public class CompleteDependentProcesses {
	private static int findMinExecutionTime(Map<String, String> independentMaps, Map<String, String> dependentMaps,
			Map<String, Integer> timeMap) throws InterruptedException, ExecutionException {
		long startTime = System.currentTimeMillis();
		int ans = 0;
		int totalProcess = independentMaps.size() + dependentMaps.size();
		FutureTask[] futureTask = new FutureTask[totalProcess];
		int i = 0;
		for(Map.Entry<String, String> independentEntry : independentMaps.entrySet()) {
			Callable<String> callWorker = new Worker(timeMap.get(independentEntry.getKey()), independentEntry.getKey());
			futureTask[i] = new FutureTask(callWorker);
			Thread t = new Thread(futureTask[i]);
			t.start();
			t.join();
		}
		for(Map.Entry<String, String> dependentEntry : independentMaps.entrySet()) {
			Callable<String> callWorker = new Worker(timeMap.get(dependentEntry.getKey()), dependentEntry.getKey());
			futureTask[i] = new FutureTask(callWorker);
			Thread t = new Thread(futureTask[i]);
			t.start();
			t.join();
		}
		
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Times take to complete all the processes is "+(elapsedTime/1000));
		return ans;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TreeMap<String, String> dependentMaps = new TreeMap<>();
		TreeMap<String, String> independentMaps = new TreeMap<>();
		independentMaps.put("P6", null);
		independentMaps.put("P1", null);
		independentMaps.put("P5", null);
		
		dependentMaps.put("P2", "P3");
		dependentMaps.put("P3", "P1");
		dependentMaps.put("P4", "P5");
		
		HashMap<String, Integer> timeMap = new HashMap<>();
		timeMap.put("P1", 2000);
		timeMap.put("P2", 5000);
		timeMap.put("P3", 3000);
		timeMap.put("P4", 1000);
		timeMap.put("P5", 6000);
		timeMap.put("P6", 1000);
		
		System.out.println(findMinExecutionTime(independentMaps, dependentMaps, timeMap));
	}

}

class Worker implements Callable<String> {
	private static Semaphore mutex = new Semaphore(2);
	private int completionTime;
	private String name;
	Worker(int time, String name) {
		completionTime = time;
		this.name = name;
	}
	@Override
	public String call() throws Exception {
		try {
			mutex.acquire();
			System.out.printf("\nProcess {%s} is running...",name);
			Thread.sleep(completionTime);
		} catch(InterruptedException e)   {
			e.printStackTrace();
		} finally {
			System.out.printf("\nProcess {%s} is completed",name);
			mutex.release();
		}
		return name;
	}
	
}
