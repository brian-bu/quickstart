package net.brian.coding.java.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class ConcurrencyTools {
	
	private ConcurrencyTools() {}
	
	// 批量产生线程来模拟多线程环境
	public static void triggerFixedAmoutOfThreadsByThreadPool(Runnable t, int threadSize, int sleep) {
		if(threadSize <= 0)
			return;
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < threadSize; i++) {
			exec.execute(t);
		}
		if(sleep > 0) {
			try {
				TimeUnit.MICROSECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		exec.shutdown();
	}
	
	/**
	 * Effective Java 2th by Joshua Bloch
	 * 
	 * item73: Avoid thread groups
	 * 
	 * 这种创建线程组的方式早已经废弃了，现在通常使用线程池的方式，见triggerFixedAmoutOfThreadsByThreadPool
	 * 但是如果感兴趣可以对比一下线程池和线程组对于批量创建线程的异同
	 * 
	 * @param t
	 * @param threadSize
	 * @param sleep
	 */
	public static void triggerFixedAmoutOfThreadsByThreadGroup(Runnable t, int threadSize, int sleep) {
		if(threadSize <= 0 || sleep <= 0)
			return;
		// 开一个叫testgroup的线程组
		ThreadGroup group = new ThreadGroup("testgroup");

		for (int i = 0; i < threadSize; i++) {
			// 开threadSize个线程，并用数字给线程命名
			Thread th = new Thread(group, t, String.valueOf(i));
			th.start();
		}
		// 如果线程组还有活跃的线程，就休眠10毫秒
		while (group.activeCount() > 0) {
			try {
				TimeUnit.MICROSECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
