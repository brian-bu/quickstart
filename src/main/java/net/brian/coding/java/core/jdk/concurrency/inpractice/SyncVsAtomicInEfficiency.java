package net.brian.coding.java.core.jdk.concurrency.inpractice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
/**
 * 
 * ������Դ�������Ĳ��м���
 * ����ͨ�����Ʊ�����������һϵ����ͬ��������
 * ��ͬ��ʱ��ͳ�Ʒ���System.currentTimeMillis����ͬ�Ĺ������
 * �Լ������߳��ڲ���SyncThread��AtomicThread�Ĳ�ͬʵ�֣�
 * ��ͬ��run����ʵ�֣�һ����++count��ͬ��������Ͻ���������һ����AtomicInteger�ṩ�Ļ�������
 * �����н��������ͬ���ķ���Ч��ҪԶ��������AtomicInteger
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