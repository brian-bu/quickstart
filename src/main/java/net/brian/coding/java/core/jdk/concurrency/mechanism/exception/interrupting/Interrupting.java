package net.brian.coding.java.core.jdk.concurrency.mechanism.exception.interrupting;

//: concurrency/Interrupting.java
import java.util.concurrent.*;
import java.io.*;

/**
 * 
 * �ж϶�sleep�ĵ��û��κ�Ҫ���׳�InterruptedException�ĵ��õ����ַ���
 *
 */
class SleepBlocked implements Runnable {
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			// �����run�����м��ϣ�Ϊ���������ַ�ʽ��ֹ����ʱ��ʱ��������״̬
			// �������ϸ���Ǵ����ִ��·��������ϸ��дcatch�Ӿ�����ȷ�������������ַ�ʽ���������쳣
			System.out.println("InterruptedException");
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
}

class IOBlocked implements Runnable {
	private InputStream in;

	public IOBlocked(InputStream is) {
		in = is;
	}

	public void run() {
		try {
			System.out.println("Waiting for read():");
			in.read();
		} catch (IOException e) {
			// ��һ�ַ��������Ľ���������catch�������һ���жϣ����Thread.interrupted��������true���Ͳ����쳣������һЩ�����߼��Ļָ�����
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted from blocked I/O");
			} else {
				throw new RuntimeException(e);
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}
}

class SynchronizedBlocked implements Runnable {
	public synchronized void f() {
		while (true) // Never releases lock
			Thread.yield();
	}

	public SynchronizedBlocked() {
		new Thread() {
			public void run() {
				f(); // Lock acquired by this thread
			}
		}.start();
	}

	public void run() {
		System.out.println("Trying to call f()");
		f();
		System.out.println("Exiting SynchronizedBlocked.run()");
	}
}

public class Interrupting {
	private static ExecutorService exec = Executors.newCachedThreadPool();

	// �����Thread��ֱ�Ӳ�����ת������ͨ��Executor��ִ�����в�����
	static void test(Runnable r) throws InterruptedException {
		// ����submit������executor����������Ϳ��Գ��и�����������ģ�����submit������һ������Future
		// �����Ϳ��Ե���cancel���ж�ĳ���ض�������cancel��һ���ж���Executor�����ĵ����̵߳ķ�ʽ
		// �ڽ��жϷ��͸�һ���߳�ͬʱ�����͸���һ���߳�ʱ�Ż�ͨ������Future�ķ�ʽ
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + r.getClass().getName());
		f.cancel(true); // Interrupts if running
		System.out.println("Interrupt sent to " + r.getClass().getName());
	}

	public static void main(String[] args) throws Exception {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0); // ... since last 2 interrupts failed
	}
}