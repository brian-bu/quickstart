package net.brian.coding.java.core.jdk.concurrency.inpractice;
// TIJ-4-P693
//: concurrency/OrnamentalGarden.java不仅演示了终止问题，而且还是一个资源共享的示例
import java.util.concurrent.*;
import java.util.*;

class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		// 这将导致有大约一半的时间产生让步，如果去掉本方法increment的synchronized关键字
		// 则将会产生多个任务同时访问并修改count域的情况（yield会让问题更快的发生）
		// 而最终程序将会崩溃
		if (rand.nextBoolean()) 
			Thread.yield();
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	}
}

class Entrance implements Runnable {
	private static Count count = new Count();
	// 这个field是属于Entrance这个类的，所以在虚拟机第一次加载这个类的时候会在堆中创建这个名为entrances的List
	// 如果是Entrance entrance = new Entrance(1);这种形式，那么每次新声明一个entrance都会创建一次这个field
	// 如果只是单纯的调用构造器即new Entrance(1)，则无论调用多少次，均只生成这一个field
	// 示例代码见StaticFieldDemo.java
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private int number = 0;
	// FIXME: 线程：都说final类不需要做同步，比如String、Integer等，但是如果一个field声明为final的也不需要同步？
	private final int id;
	// volatile和普通方法结合的时候本身已经是线程安全的了，因此这里canceled域和cancel方法组合可以确保canceled域的获取是线程安全的
	// 此机制还可以通过synchronized锁定cancel方法实现，但这样一来由于cancel方法是静态方法，它是属于整个类的，因此利用synchronized锁定这个方法就相当于锁定了整个类，锁的力度因此也会变大
	private static volatile boolean canceled = false;

	// Atomic operation on a volatile field:
	public static void cancel() {
		canceled = true;
	}

	public Entrance(int id) {
		this.id = id;
		// Keep this task in a list. Also prevents
		// garbage collection of dead tasks:
		entrances.add(this);
	}

	public void run() {
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		System.out.println("Stopping " + this);
	}

	public synchronized int getValue() {
		return number;
	}

	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for (Entrance entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}

public class OrnamentalGarden {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Entrance(i));
		// Run for a while, then stop and collect the data:
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
			System.out.println("Some tasks were not terminated!");
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}
}