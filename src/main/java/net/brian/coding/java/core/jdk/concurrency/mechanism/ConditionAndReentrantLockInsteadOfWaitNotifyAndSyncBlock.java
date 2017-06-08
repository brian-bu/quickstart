//: concurrency/waxomatic2/WaxOMatic2.java
// Using Lock and Condition objects.
package net.brian.coding.java.core.jdk.concurrency.mechanism;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ConditionAndReentrantLockInsteadOfWaitNotifyAndSyncBlock {
	public static void main(String[] args) throws Exception {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

// 这里仅对Car的类进行重构，利用ReentrantLock代替synchronized同步机制
// 利用Codition的signalAll和await代替Object的notifyAll和wait
class Car {
	// 在使用并发时，将域设置为private的是非常重要的，否则synchronized关键字就不能防止其它任务直接访问域，就会产生冲突
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;

	public void waxed() {
		// 利用lock方法和finally中的unlock方法创建临界资源，lock和unlock之间的语句和synchronized语句块的作用是一样的
		// 但是用synchronized在抛出异常的时候无法编写程序来优雅的退出，而使用Lock就可以在finally语句块进行合适的编码，优雅的退出程序
		lock.lock();
		try {
			waxOn = true; // Ready to buff
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void buffed() {
		lock.lock();
		try {
			waxOn = false; // Ready for another coat of wax
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn == false)
				condition.await();
		} finally {
			lock.unlock();
		}
	}

	public void waitForBuffing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn == true)
				condition.await();
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car c) {
		car = c;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Wax On! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax On task");
	}
}

class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car c) {
		car = c;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();
				System.out.println("Wax Off! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax Off task");
	}
}