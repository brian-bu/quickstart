package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.TimeUnit;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item66: Synchronize access to shared mutable data
 * 
 * 当多个线程共享可变数据的时候，每个读或者写数据的线程都必须执行同步
 * 线程同步的作用：保证一个线程所做的修改可以被另一个线程获知
 * 通过synchronized方法访问可变数据或者通过volatile关键字修饰可变数据达到线程同步
 * 如果只需要线程之间的交互通信，而不需要互斥，volatile就是一种可以接受的同步形式
 * 因此本例主要示范volatile，对于synchronized的方法详见如下示例：
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.StopThreadByWhileLoopWithSync
 * 此外还有针对synchronized锁进行的不同应用场景讨论，见：
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.SyncObjectAndSyncThisGetNoConflicts
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.SyncObjectInBlockOrMethodRunAtSameTime
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.SyncObjInBlockWithDiffMonitor
 * 
 * volatile和synchronized不一样，volatile是通过自己的机制保证任何一个线程在读取该域的时候都能看到最近刚刚被写入的值
 * 而synchronized的本质是互斥，也就是说，只有一个线程可以对它进行操作
 * 线程A对某变量值的修改,可能没有立即在线程B体现出来,称为活性失败
 * 安全性失败见：
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
