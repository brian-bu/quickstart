package net.brian.coding.java.core.jdk.concurrency.mechanism.scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
	static {
		try {
			System.setOut(new PrintStream(new File("Y:\\log\\tij\\SimplePriorities.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private int countDown = 5;
	@SuppressWarnings("unused")
	private volatile double d; // No optimization
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	public String toString() {
		// 相同值的countDown可以发现总是优先级高的那个线程最先执行完
		// 而不是先执行完countDown为5的然后再执行4
		return Thread.currentThread() + ": " + countDown;
	}

	public void run() {
		// 通过setPriority调整线程优先级，通过getPriority获取线程的优先级
		// 线程只有运行起来了才涉及到优先级的概念，因此通常改变优先级的语句都是写在run方法最开始
		Thread.currentThread().setPriority(priority);
		while (true) {
			// An expensive, interruptable operation:
			for (int i = 1; i < 10000000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 10000 == 0) {
					// 每当除1000余数为0，也就是是1000的整数倍时，就让线程做出让步，即使当前的线程还没执行完
					// 事实上每个线程都要阻塞99次才能最终执行完任务，所以 只要线程的执行时间足够长，让步的线程足够多，就会有越来越多的未执行完的线程进入阻塞状态
					// 而这个时候线程调度就会优先执行优先级较高的线程
					/**
					 * Effective Java 2th by Joshua Bloch
					 * 
					 * item72: Don’t depend on the thread scheduler
					 * 
					 * Thread.yield()唯一的用途就是在测试期间人为增加程序的并发性，在java规范中yield根本不做实质性工作
					 * 只将控制权返回给它的调用者，因此应该使用Thread.sleep(1)代替yield，千万不要用Thread.sleep(0)，它会立即返回
					 * 
					 * 不要让程序正确性依赖于线程调度器，否则结果得到的应用程序将既不健壮也不具有可移植性
					 * 作为推论，也不要用yield或者依赖线程优先级
					 * 线程优先级正确应用应该是提高一个已经能够正常工作的程序的服务质量，而不是用来“修正”原来本就不正确的程序
					 */
					Thread.yield();
				}
			}
			// 这列的this就是调用当前run方法的线程Thread，而toString()方法被重写，所以这里打印出的是线程对象的信息
			// 和TIJ书中的输出顺序不一致，因为这里涉及到每次运行时，线程的调度器对不同线程执行顺序不一样
			System.out.println("Thread object:: " + this);
			if (--countDown == 0){
				// 如果没有这个return，while循环将会一直执行下去直至程序崩溃，这里的处理方式是执行5次while循环然后退出
				return;
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		// 一共五个线程放到线程池里，控制台打出的线程编号从1到5
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
			// 输出的永远都是main，这里输出的是当前的线程，而当前的线程就是main方法
			// System.out.println("Creating thread:: " + Thread.currentThread().getName());
		}
		// 第六个线程，控制台打出的线程编号是6，虽然是最后被添加的，但是由于前五个线程的执行时间足够长
		// 所以等到执行第六个线程的时候前五个没有一个执行完的，此时第六个线程由于优先级较高，就在其它线程都做出让步时优先执行
		// 这也说明了在执行execute方法的同时，代码是继续向下执行的，而且还可以通过更多的execute方法向既有线程池继续添加新线程
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}