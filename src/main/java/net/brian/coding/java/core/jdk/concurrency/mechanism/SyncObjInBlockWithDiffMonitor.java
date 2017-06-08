package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * 三个不同的方法都各含一个同步代码块，如果这些代码块使用的是同一个对象加锁，那么同时运行三个方法会产生线程阻塞
 * 如果这些代码块使用的是不同的对象加锁，那么同时运行三个方法不会产生线程阻塞
 *
 */
public class SyncObjInBlockWithDiffMonitor {
	// 三个方法各包含一个临界区，所有对该临界区的同步都是在同一个对象上的，这种情况下这些方法只能同时运行一个
	private Object sharedObj = new Object();
	private Object disObj1 = new Object();
	private Object disObj2 = new Object();

	public void a() {
		synchronized (sharedObj) {
			for (int i = 0; i < 5; i++) {
				System.out.println("a()");
				Thread.yield();
			}
		}
	}

	public void b() {
		synchronized (sharedObj) {
			for (int i = 0; i < 5; i++) {
				System.out.println("b()");
				Thread.yield();
			}
		}
	}

	public void c() {
		synchronized (sharedObj) {
			for (int i = 0; i < 5; i++) {
				System.out.println("c()");
				Thread.yield();
			}
		}
	}
	public void d() {
		synchronized (disObj1) {
			for (int i = 0; i < 5; i++) {
				System.out.println("d()");
				Thread.yield();
			}
		}
	}
	
	public void e() {
		synchronized (disObj2) {
			for (int i = 0; i < 5; i++) {
				System.out.println("e()");
				Thread.yield();
			}
		}
	}
	
	public void testSharedObj(SyncObjInBlockWithDiffMonitor demo, ExecutorService pool) {
		pool.execute(new Thread() {
			public void run() {
				demo.a();
			}
		});
		pool.execute(new Thread() {
			public void run() {
				demo.b();
			}
		});
		pool.execute(new Thread() {
			public void run() {
				demo.c();
			}
		});
	}
	public void testDisObj(SyncObjInBlockWithDiffMonitor demo, ExecutorService pool) {
		pool.execute(new Thread() {
			public void run() {
				demo.a();
			}
		});
		pool.execute(new Thread() {
			public void run() {
				demo.d();
			}
		});
		pool.execute(new Thread() {
			public void run() {
				demo.e();
			}
		});
	}

	public static void main(String[] args) {
		final SyncObjInBlockWithDiffMonitor demo = new SyncObjInBlockWithDiffMonitor();
		ExecutorService pool = Executors.newCachedThreadPool();
//		demo.testSharedObj(demo, pool);
		demo.testDisObj(demo, pool);
		pool.shutdown();
	}
}
