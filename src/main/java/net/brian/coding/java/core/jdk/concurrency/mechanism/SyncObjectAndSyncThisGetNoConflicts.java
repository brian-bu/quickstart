package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * synchronized (sharedObj)��ס����һ�����������ɵĶ���
 * ��synchronized (this)��ס���ǵ�ǰ��������ɵĶ���
 * ����ͬʱ����synchronized (this)�ķ���b��c�ʹ��ھ�̬����
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
 * log:(��γ���Ľ��ÿ�ζ��ǲ�һ���ģ����ǻ���������һ�����ɣ�a������˳��ȷ��������һ�������е�b������ִ�����֮����ִ��c����)
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