package net.brian.coding.java.core.jdk.concurrency.mechanism.exception.interrupting;

//: concurrency/InterruptingIdiom.java
import java.util.concurrent.*;

/**
 * 
 * 本例使用不同的延迟时间可以在不同的地点退出run方法用以响应interrupt方法
 * 这通常意味着所有需要清理的对象创建操作的后面都必须紧跟try-finally 从而使得无论run如何退出，清理工作都会发生
 * NeedsCleanup强调经由异常离开程序的时候，正确清理资源的必要性，实际程序中也可以没有这个类，但是try-finally是一定要有的
 *
 */
class NeedsCleanup {
	private final int id;

	public NeedsCleanup(int ident) {
		id = ident;
		System.out.println("NeedsCleanup " + id);
	}

	public void cleanup() {
		System.out.println("Cleaning up " + id);
	}
}

class Blocked3 implements Runnable {
	private volatile double d = 0.0;

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// student1，从这里开始到 point2之前
				NeedsCleanup n1 = new NeedsCleanup(1);
				// 定义了n1对象后立即进入一个try-finally确保n1对象被正确清理
				try {
					System.out.println("Sleeping");
					// 如果程序运行到这里之后调用了interrupt方法，则任务的run方法将以InterruptedException的方式退出
					// 这个时候仅仅创建了n1对象，还没有创建n2，所以我们通过了第一个try-finally确保了n1对象被正常清理
					TimeUnit.SECONDS.sleep(1);
					// point2:从这里开始之前，是通过sleep的作用导致程序阻塞一小段时间
					// 如果程序运行到这里之后调用了interrupt方法，则首先结束for循环的执行并清理所有本地对象，之后经由while顶部退出
					// 执行for循环的时候n2对象已经被创建，所以这个时候需要经由第二个try-finally进行程序的清理
					NeedsCleanup n2 = new NeedsCleanup(2);
					// 定义了n2对象后立即进入一个try-finally确保n2对象被正确清理
					try {
						System.out.println("Calculating");
						// 这里是一个非阻塞的数学计算，但是比较耗时，中途有可能被打断
						for (int i = 1; i < 2500000; i++)
							d = d + (Math.PI + Math.E) / d;
						System.out.println("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.out.println("Exiting via while() test");
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}
}

public class InterruptingIdiom {
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("usage: java InterruptingIdiom delay-in-mS");
			System.exit(1);
		}
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		t.interrupt();
	}
}