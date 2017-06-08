package net.brian.coding.java.core.jdk.concurrency.inpractice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
/**
 * 
 * 共享资源：无锁的并行计算
 * 本例通过控制变量法定义了一系列相同的条件：
 * 相同的时间统计方法System.currentTimeMillis和相同的共享变量
 * 以及两个线程内部类SyncThread，AtomicThread的不同实现：
 * 不同的run方法实现，一个用++count和同步方法组合进行自增，一个用AtomicInteger提供的机制自增
 * 从运行结果来看，同步的方法效率要远低于利用AtomicInteger
 *
 */
public class SyncVsAtomicInEfficiency {
	private static final int MAX_THREADS = 3;
	private static final int TASK_COUNT = 3;
	private static final int TARGET_COUNT = 100 * 10000;
	private AtomicInteger acount = new AtomicInteger(0);
	private int count = 0;

	synchronized int inc() {
		return ++count;
	}

	public class SyncThread implements Runnable {
		long startTime;
		SyncVsAtomicInEfficiency out;

		public SyncThread(SyncVsAtomicInEfficiency o, long startTime) {
			this.out = o;
			this.startTime = startTime;
		}

		@Override
		public void run() {
			int v = out.inc();
			while (v < TARGET_COUNT) {
				v = out.inc();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("SyncThread spend:" + (endTime - startTime) + "ms" + ", v=" + v);
		}
	}

	public class AtomicThread implements Runnable {
		long startTime;

		public AtomicThread(long startTime) {
			this.startTime = startTime;
		}

		@Override
		public void run() {
			int v = acount.incrementAndGet();
			while (v < TARGET_COUNT) {
				v = acount.incrementAndGet();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("AtomicThread spend:" + (endTime - startTime) + "ms" + ", v=" + v);
		}
	}

	@Test
	public void testSync() throws InterruptedException {
		ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
		long startTime = System.currentTimeMillis();
		SyncThread sync = new SyncThread(this, startTime);
		for (int i = 0; i < TASK_COUNT; i++) {
			exe.submit(sync);
		}
		Thread.sleep(10000);
	}

	@Test
	public void testAtomic() throws InterruptedException {
		ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
		long startTime = System.currentTimeMillis();
		AtomicThread atomic = new AtomicThread(startTime);
		for (int i = 0; i < TASK_COUNT; i++) {
			exe.submit(atomic);
		}
		Thread.sleep(10000);
	}
}