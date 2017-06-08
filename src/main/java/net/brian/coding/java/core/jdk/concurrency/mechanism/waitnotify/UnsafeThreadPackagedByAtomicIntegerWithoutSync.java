package net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify;

import java.util.Timer;
import java.util.TimerTask;
//: concurrency/AtomicityTest.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * 这里我们通过AtomicInteger对非线程类的包装取代了用synchronized关键字进行线程安全同步的机制
 * 但是还是通常要依赖锁的机制更安全一些（要么是synchronized，要么是显式的Lock）
 * Effective Java对本例也有提及：有关活性失败和安全性失败
 * 安全性失败：线程A在线程B读取某个域旧值和写回新值期间读取该域
 * 活性失败见：
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.StopThreadByWhileLoopWithVolatile
 *
 */
public class UnsafeThreadPackagedByAtomicIntegerWithoutSync implements Runnable {
	// 利用AtomicInteger的构造器给i赋初始值为0，这样就不需要对这个变量的自增进行额外的线程维护
	// 这里只不过是利用AtomicInteger把关于i的所有操作都封装起来使其达到原子性的要求
	// 利用AtomicInteger进行变量i的声明，利用AtomicInteger构造器为i赋初始值
	// 利用AtomicInteger的get方法返回i的getter的值并把这个值暴露给外界
	// 利用AtomicInteger的addAndGet、getAndIncrement等方法完成不同需求的原子性自增
	// private int i = 0;
	private AtomicInteger i = new AtomicInteger(0);

	// return i是原子性操作
	public int getValue() {
		return i.get();
	}

	// 利用javap反编译就可以发现i++实际上是一次getfield和一次putfield共同作用后的结果，所以这不是一个原子性的操作
	// 这里把i++自增的任务委托给AtomicInteger完成可以保证这种自增就是原子性操作，进而省去了线程维护。
	private void evenIncrement() {
		i.addAndGet(2);
		System.out.println("Output the value of i::" + i.get());
	}

	public void run() {
		while (true)
			evenIncrement();
	}

	public static void main(String[] args) {
		// 由于这个程序经改装已经变成线程安全的类，所以会一直输出偶数不会失败，所以不会停止
		// 因此这里添加一个方法让它在5秒后结束运行，也可以看作是对Timer这个API的实践
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Aborting...");
				System.exit(0);
			}
		}, 5000);
		ExecutorService exec = Executors.newCachedThreadPool();
		UnsafeThreadPackagedByAtomicIntegerWithoutSync at = new UnsafeThreadPackagedByAtomicIntegerWithoutSync();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println("Not even value:: " + val);
				System.exit(0);
			}
		}
	}
}
