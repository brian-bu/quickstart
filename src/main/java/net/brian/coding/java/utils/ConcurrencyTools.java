package net.brian.coding.java.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class ConcurrencyTools {
	
	private ConcurrencyTools() {}
	
	// ���������߳���ģ����̻߳���
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
	 * ���ִ����߳���ķ�ʽ���Ѿ������ˣ�����ͨ��ʹ���̳߳صķ�ʽ����triggerFixedAmoutOfThreadsByThreadPool
	 * �����������Ȥ���ԶԱ�һ���̳߳غ��߳���������������̵߳���ͬ
	 * 
	 * @param t
	 * @param threadSize
	 * @param sleep
	 */
	public static void triggerFixedAmoutOfThreadsByThreadGroup(Runnable t, int threadSize, int sleep) {
		if(threadSize <= 0 || sleep <= 0)
			return;
		// ��һ����testgroup���߳���
		ThreadGroup group = new ThreadGroup("testgroup");

		for (int i = 0; i < threadSize; i++) {
			// ��threadSize���̣߳��������ָ��߳�����
			Thread th = new Thread(group, t, String.valueOf(i));
			th.start();
		}
		// ����߳��黹�л�Ծ���̣߳�������10����
		while (group.activeCount() > 0) {
			try {
				TimeUnit.MICROSECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
