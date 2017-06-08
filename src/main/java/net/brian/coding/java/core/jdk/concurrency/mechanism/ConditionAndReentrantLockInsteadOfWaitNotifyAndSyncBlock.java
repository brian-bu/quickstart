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

// �������Car��������ع�������ReentrantLock����synchronizedͬ������
// ����Codition��signalAll��await����Object��notifyAll��wait
class Car {
	// ��ʹ�ò���ʱ����������Ϊprivate���Ƿǳ���Ҫ�ģ�����synchronized�ؼ��־Ͳ��ܷ�ֹ��������ֱ�ӷ����򣬾ͻ������ͻ
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;

	public void waxed() {
		// ����lock������finally�е�unlock���������ٽ���Դ��lock��unlock֮�������synchronized�����������һ����
		// ������synchronized���׳��쳣��ʱ���޷���д���������ŵ��˳�����ʹ��Lock�Ϳ�����finally������к��ʵı��룬���ŵ��˳�����
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