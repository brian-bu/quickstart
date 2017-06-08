package net.brian.coding.java.core.jdk.concurrency.mechanism.exception.interrupting;

//: concurrency/Interrupting.java
import java.util.concurrent.*;
import java.io.*;

/**
 * 
 * 中断对sleep的调用或任何要求抛出InterruptedException的调用的三种方法
 *
 */
class SleepBlocked implements Runnable {
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			// 任务的run方法中间打断，为了在以这种方式终止任务时及时返回良好状态
			// 你必须仔细考虑代码的执行路径，并仔细编写catch子句以正确清除所有事物，这种方式更加像抛异常
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
			// 第一种方法稍作改进，可以在catch块中添加一个判断：如果Thread.interrupted方法返回true，就不抛异常，进行一些程序逻辑的恢复处理
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

	// 避免对Thread的直接操作，转而尽量通过Executor来执行所有操作。
	static void test(Runnable r) throws InterruptedException {
		// 调用submit而不是executor来启动任务就可以持有该任务的上下文，并且submit将返回一个泛型Future
		// 这样就可以调用cancel来中断某个特定的任务，cancel是一种中断由Executor启动的单个线程的方式
		// 在将中断发送给一个线程同时不发送给另一个线程时才会通过捕获Future的方式
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