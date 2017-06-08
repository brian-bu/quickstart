package net.brian.coding.java.core.jdk.concurrency.mechanism;
/**
 * 
 * 同步代码块和同步方法是相互独立的，它们各持有不同的对象锁，因此这两种方式在同时运行的时候不会产生阻塞
 *
 */
class DualSynch {
	private Object syncObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObjectInBlockOrMethodRunAtSameTime {

	public static void main(String[] args) {
		// 这里的final保证了这个对象是不可变的，进而保证了不同的线程调用方法的时候使用的都是同一个对象
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}
}