package net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer.pool;

//: concurrency/Pool.java
// Using a Semaphore inside a Pool, to restrict
// the number of tasks that can use a resource.
import java.util.concurrent.*;
import java.util.*;

/**
 * 
 * 正常的锁（Lock和synchronized）在任何时刻都只允许一个任务访问一个资源 计数信号量允许n个任务同时访问这个资源
 * 
 * 本例以对象池为例，对象池管理着数量有限的对象，当要使用对象时可以签出它们，使用完毕签回
 *
 */
public class PoolBySemaphore<T> {
	private int size;
	private List<T> items = new ArrayList<T>();
	private volatile boolean[] checkedOut;
	private Semaphore available;

	public PoolBySemaphore(Class<T> classObject, int size) {
		this.size = size;
		checkedOut = new boolean[size];
		// 创建size个信号量，true表示的是这些信号量遵循先进先出
		available = new Semaphore(size, true);
		// Load pool with objects that can be checked out:
		for (int i = 0; i < size; ++i)
			try {
				// Assumes a default constructor:
				items.add(classObject.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	// 申请占用一个资源的方法封装
	public T checkOut() throws InterruptedException {
		// 占用一个资源
		available.acquire();
		return getItem();
	}

	// 申请释放一个资源的方法封装
	public void checkIn(T x) {
		if (releaseItem(x))
			// 释放一个资源
			available.release();
	}

	private synchronized T getItem() {
		for (int i = 0; i < size; ++i)
			if (!checkedOut[i]) {
				checkedOut[i] = true;
				return items.get(i);
			}
		return null; // Semaphore prevents reaching here
	}

	private synchronized boolean releaseItem(T item) {
		int index = items.indexOf(item);
		if (index == -1)
			return false; // Not in the list
		if (checkedOut[index]) {
			checkedOut[index] = false;
			return true;
		}
		return false; // Wasn't checked out
	}
} /// :~
