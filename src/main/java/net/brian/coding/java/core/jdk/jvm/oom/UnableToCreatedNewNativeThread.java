package net.brian.coding.java.core.jdk.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * 
 * 本例使用虚拟机参数：-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/OneDrive/desktop/a.dump -Xmx20m -Xms5m
 * 控制台打印出的GC日志：
 *
 */
public class UnableToCreatedNewNativeThread {
	private static class SleepThread implements Runnable {
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		for(int i = 0; i < 150000; i++) {
			new Thread(new SleepThread(), "Thread" + i).start();
			System.out.println("Thread" + i + " created.");
		}
	}
}
