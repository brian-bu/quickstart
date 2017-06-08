package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * synchronized (sharedObj)锁住的是一个任意类生成的对象
 * 而synchronized (this)锁住的是当前这个类生成的对象
 * 所以同时包含synchronized (this)的方法b和c就存在静态条件
 *
 */
public class SyncObjectAndSyncThisGetNoConflicts {
	private Object sharedObj = new Object();

	public void a() {
		synchronized (sharedObj) {
			for (int i = 0; i < 5; i++) {
				System.out.println("a()");
				Thread.yield();
			}
		}
	}

	public void b() {
		System.out.println("Thread before in method b():: " + this);
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println("b()");
				Thread.yield();
			}
		}
	}

	public void c() {
		System.out.println("Thread before in method c():: " + this);
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println("c()");
				Thread.yield();
			}
		}
	}

	public void testDisObj(SyncObjectAndSyncThisGetNoConflicts demo, ExecutorService pool) {
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

	public static void main(String[] args) {
		final SyncObjectAndSyncThisGetNoConflicts demo = new SyncObjectAndSyncThisGetNoConflicts();
		ExecutorService pool = Executors.newCachedThreadPool();
		demo.testDisObj(demo, pool);
		pool.shutdown();
	}
}
/**
 * log:(这段程序的结果每次都是不一样的，但是基本还是有一个规律：a方法的顺序不确定，但是一定是所有的b方法都执行完成之后再执行c方法)
 *
 * a() 
 * Thread before in method b():: net.brian.jdk.concurrency.synchronization.SyncObjectAndSyncThis@1a7288d1 
 * b()
 * a() 
 * b() 
 * a() 
 * b() 
 * b() 
 * b() 
 * Thread before in method c():: net.brian.jdk.concurrency.synchronization.SyncObjectAndSyncThis@1a7288d1 
 * c()
 * a() 
 * c() 
 * a() 
 * c() 
 * c() 
 * c()
 */