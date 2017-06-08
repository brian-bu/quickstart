package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * ������ͬ�ķ���������һ��ͬ������飬�����Щ�����ʹ�õ���ͬһ�������������ôͬʱ������������������߳�����
 * �����Щ�����ʹ�õ��ǲ�ͬ�Ķ����������ôͬʱ��������������������߳�����
 *
 */
public class SyncObjInBlockWithDiffMonitor {
	// ��������������һ���ٽ��������жԸ��ٽ�����ͬ��������ͬһ�������ϵģ������������Щ����ֻ��ͬʱ����һ��
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
