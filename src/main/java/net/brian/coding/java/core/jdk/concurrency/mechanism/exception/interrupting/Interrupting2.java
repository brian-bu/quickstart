package net.brian.coding.java.core.jdk.concurrency.mechanism.exception.interrupting;

//: concurrency/Interrupting2.java
// Interrupting a task blocked with a ReentrantLock.
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		// 获取所创建对象上自身的Lock，并且从不释放这个锁
		lock.lock();
	}

	public void f() {
		try {
			// This will never be available to a second task
			lock.lockInterruptibly(); // Special call
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			// 任务要进入到阻塞操作中或者已经在阻塞操作内部时，如果不调用interrupt方法阻塞就不会提前中断
			// 本例是lock一直被保持最终被interrupt方法打断，而一旦被打断就会进入这个catch块里
			// 注意interrupt方法本身不会抛出异常，而是正在运行的线程调用这个方法会抛异常
			// 如下便是抛出异常后处理的一种方式，可以优雅的退出异常
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();

	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		// 由于BlockedMutex构造器中一直锁着对象不放，所以这里的调用会一直阻塞下去
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
}

public class Interrupting2 {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		// 这个方法为什么比blocked.f()先发生？是因为blocked对象一直被锁着，无法向下执行
		// 而这个时候执行到这句interrupt中断了BlockedMutex构造器的锁，使程序继续运行下去
		// 注释掉这句将导致程序一直被阻塞。
		t.interrupt();
	}
}