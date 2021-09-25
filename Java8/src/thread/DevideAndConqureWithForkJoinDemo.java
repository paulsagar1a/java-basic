package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DevideAndConqureWithForkJoinDemo {

	public static void main(String[] args) {
		ForkJoinPool  pool = ForkJoinPool.commonPool();
		Long result =  pool.invoke(new RecursiveSum(0, 1000000000));
		pool.shutdown();
		System.out.println("Total sum = "+result);
	}

}

class RecursiveSum extends RecursiveTask<Long>{
	private long   lo, hi;
	
	public RecursiveSum(long lo, long hi)   {
		this.lo = lo;
		this.hi = hi;
	}

	@Override
	protected Long compute() {
		if(hi-lo <=  100000) {//base case threshold
			long total =0;
			for(long i=lo; i<=hi; i++) {
				total += i;
			}
			return total;
		} else {
			long mid = (lo+hi)/2; //middle index for split
			RecursiveSum left = new RecursiveSum(lo, mid);
			RecursiveSum right = new RecursiveSum(mid+1, hi);
			left.fork();//forked thread computes left half
			return right.compute() + left.join(); //current thread computes right half
		}
	}
	
}