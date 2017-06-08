//: concurrency/CriticalSection.java
// Synchronizing blocks instead of entire methods. Also
// demonstrates protection of a non-thread-safe class
// with a thread-safe one.
package net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;

/**
 * 
 * 1.某人交给你一个非线程安全的类Pair，而你要在线程环境中使用它。
 * 2.通过使用临界区（即synchronized代码块）代替synchronized方法可以实现更细粒度的线程控制
 *
 */
class Pair { // Not thread-safe
	private int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Pair() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	public String toString() {
		return "x: " + x + ", y: " + y;
	}

	public class PairValuesNotEqualException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public PairValuesNotEqualException() {
			super("Pair values not equal: " + Pair.this);
		}
	}

	// Arbitrary invariant -- both variables must be equal:
	public void checkState() {
		if (x != y)
			throw new PairValuesNotEqualException();
	}
}

// Protect a Pair inside a thread-safe class:
abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
	// 抽象类中也是可以有synchronized方法的，但是接口里不可以有
	public synchronized Pair getPair() {
		// Make a copy to keep the original safe:
		// 保护性拷贝，@see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.DefensiveCopiesDemo
		return new Pair(p.getX(), p.getY());
	}

	// Assume this is a time consuming operation
	protected void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException ignore) {
		}
	}

	public abstract void increment();
}

// Synchronize the entire method:
class PairManager1 extends PairManager {
	// 方法被调用时，首先必须获得当前对象锁（monitor），若当前对象锁被其他线程持有，调用线程会等待，方法结束后，对象锁会被释放
	// synchronized关键字不属于方法特征签名的组成部分，所以可以在覆盖方法的时候加上去
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}

// Use a critical section:
class PairManager2 extends PairManager {
	public void increment() {
		Pair temp;
		// 与同步方法相比，同步块可以更为精确控制同步代码范围。一个小的同步代码非常有利于锁的快进快出，从而使系统拥有更高的吞吐量。
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

class PairManipulator implements Runnable {
	private PairManager pm;

	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}

	public void run() {
		while (true)
			pm.increment();
	}

	public String toString() {
		return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
	}
}

class PairChecker implements Runnable {
	private PairManager pm;

	public PairChecker(PairManager pm) {
		this.pm = pm;
	}

	public void run() {
		while (true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
	}
}

public class UnsafeThreadPackagedBySync {
	// Test the two different approaches:
	static void testApproaches(PairManager pman1, PairManager pman2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator pm1 = new PairManipulator(pman1), pm2 = new PairManipulator(pman2);
		PairChecker pcheck1 = new PairChecker(pman1), pcheck2 = new PairChecker(pman2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted");
		}
		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}

	public static void main(String[] args) {
		PairManager pman1 = new PairManager1(), pman2 = new PairManager2();
		testApproaches(pman1, pman2);
	}
} /*
	 * Output: (Sample) pm1: Pair: x: 15, y: 15 checkCounter = 272565 pm2: Pair:
	 * x: 16, y: 16 checkCounter = 3956974
	 */// :~
