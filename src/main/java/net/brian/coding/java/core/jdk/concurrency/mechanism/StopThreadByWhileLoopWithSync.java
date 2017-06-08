package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.TimeUnit;
/**
 * 
 * 阻止一个线程妨碍另一个线程，建议做法是让第一个线程轮询一个boolean域stopRequested默认值false
 * 通过第二个线程将它设置为true，以表示第一个线程将终止自己
 * 本例通过synchronized修饰读写stopRequested的两个方法，做到读写都被同步，否则仅针对读或者写同步是无法起作用的
 * 另外还有volatile实现读写的内存可见性的例子，见：
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
