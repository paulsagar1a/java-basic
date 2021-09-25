package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteLockImpl {
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private ReadLock readLock = (ReadLock) readWriteLock.readLock();
	private WriteLock writeLock = (WriteLock) readWriteLock.writeLock();
	private final List<Integer> list = new ArrayList<>();

	public void set(int item) {
		writeLock.lock();
		try {
			list.add(item);
			System.out.println(item+" Added by the thread "+Thread.currentThread().getName());
		} finally {
			writeLock.unlock();
		}
	}
	
	public int get(int index) {
		readLock.lock();
		try {
			System.out.println("Printing by the thread "+Thread.currentThread().getName());
			return list.get(index);
		} finally {
			readLock.lock();
		}
	}
	
	public static void main(String[] args) {
		ReadWriteLockImpl threadSafeList = new ReadWriteLockImpl();
		threadSafeList.set(1);
		threadSafeList.set(2);
		threadSafeList.set(3);
		
		System.out.println("Printing the 1st element "+threadSafeList.get(1));
		
	}

}