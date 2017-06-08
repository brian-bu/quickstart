package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.TimeUnit;
/**
 * 
 * ��ֹһ���̷߳�����һ���̣߳������������õ�һ���߳���ѯһ��boolean��stopRequestedĬ��ֵfalse
 * ͨ���ڶ����߳̽�������Ϊtrue���Ա�ʾ��һ���߳̽���ֹ�Լ�
 * ����ͨ��synchronized���ζ�дstopRequested������������������д����ͬ�����������Զ�����дͬ�����޷������õ�
 * ���⻹��volatileʵ�ֶ�д���ڴ�ɼ��Ե����ӣ�����
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.StopThreadByWhileLoopWithVolatile
 *
 */
public class StopThreadByWhileLoopWithSync {

	private static boolean stopRequested;

	private static synchronized void requestStop() {
		stopRequested = true;
	}

	private static synchronized boolean stopRequested() {
		return stopRequested;
	}

	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				int i = 0;
				while (!stopRequested())
					i++;
			}
		});
		backgroundThread.start();

		TimeUnit.SECONDS.sleep(1);
		requestStop();
	}
}
