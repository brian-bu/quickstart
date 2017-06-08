package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.TimeUnit;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item66: Synchronize access to shared mutable data
 * 
 * ������̹߳���ɱ����ݵ�ʱ��ÿ��������д���ݵ��̶߳�����ִ��ͬ��
 * �߳�ͬ�������ã���֤һ���߳��������޸Ŀ��Ա���һ���̻߳�֪
 * ͨ��synchronized�������ʿɱ����ݻ���ͨ��volatile�ؼ������οɱ����ݴﵽ�߳�ͬ��
 * ���ֻ��Ҫ�߳�֮��Ľ���ͨ�ţ�������Ҫ���⣬volatile����һ�ֿ��Խ��ܵ�ͬ����ʽ
 * ��˱�����Ҫʾ��volatile������synchronized�ķ����������ʾ����
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.StopThreadByWhileLoopWithSync
 * ���⻹�����synchronized�����еĲ�ͬӦ�ó������ۣ�����
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.SyncObjectAndSyncThisGetNoConflicts
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.SyncObjectInBlockOrMethodRunAtSameTime
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.SyncObjInBlockWithDiffMonitor
 * 
 * volatile��synchronized��һ����volatile��ͨ���Լ��Ļ��Ʊ�֤�κ�һ���߳��ڶ�ȡ�����ʱ���ܿ�������ոձ�д���ֵ
 * ��synchronized�ı����ǻ��⣬Ҳ����˵��ֻ��һ���߳̿��Զ������в���
 * �߳�A��ĳ����ֵ���޸�,����û���������߳�B���ֳ���,��Ϊ����ʧ��
 * ��ȫ��ʧ�ܼ���
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify.UnsafeThreadPackagedByAtomicIntegerWithoutSync
 *
 */
public class StopThreadByWhileLoopWithVolatile {
	private static volatile boolean stopRequested;

	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				int i = 0;
				while (!stopRequested)
					i++;
			}
		});
		backgroundThread.start();

		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
}
}
