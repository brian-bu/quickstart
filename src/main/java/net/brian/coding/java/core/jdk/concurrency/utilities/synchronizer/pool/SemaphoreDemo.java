package net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer.pool;

//: concurrency/SemaphoreDemo.java
import java.util.concurrent.*;

import net.brian.coding.java.core.jdk.concurrency.utilities.ExpensiveObjectCreating;

import java.util.*;

// A task to check a resource out of a pool:
class CheckoutTask<T> implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private PoolBySemaphore<T> pool;

	public CheckoutTask(PoolBySemaphore<T> pool) {
		this.pool = pool;
	}

	public void run() {
		try {
			T item = pool.checkOut();
			System.out.println(this + "checked out " + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(this + "checking in " + item);
			pool.checkIn(item);
		} catch (InterruptedException e) {
			// Acceptable way to terminate
		}
	}

	public String toString() {
		return "CheckoutTask " + id + " ";
	}
}

public class SemaphoreDemo {
	final static int SIZE = 25;

	public static void main(String[] args) throws Exception {
		final PoolBySemaphore<ExpensiveObjectCreating> pool = new PoolBySemaphore<ExpensiveObjectCreating>(ExpensiveObjectCreating.class, SIZE);
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++)
			exec.execute(new CheckoutTask<ExpensiveObjectCreating>(pool));
		System.out.println("All CheckoutTasks created");
		List<ExpensiveObjectCreating> list = new ArrayList<ExpensiveObjectCreating>();
		for (int i = 0; i < SIZE; i++) {
			ExpensiveObjectCreating f = pool.checkOut();
			System.out.println(i + ": main() thread checked out ");
			f.operation();
			list.add(f);
		}
		Future<?> blocked = exec.submit(new Runnable() {
			public void run() {
				try {
					// Semaphore prevents additional checkout,
					// so call is blocked:
					pool.checkOut();
				} catch (InterruptedException e) {
					System.out.println("checkOut() Interrupted");
				}
			}
		});
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true); // Break out of blocked call
		System.out.println("Checking in objects in " + list);
		for (ExpensiveObjectCreating f : list)
			pool.checkIn(f);
		for (ExpensiveObjectCreating f : list)
			pool.checkIn(f); // Second checkIn ignored
		exec.shutdown();
	}
}