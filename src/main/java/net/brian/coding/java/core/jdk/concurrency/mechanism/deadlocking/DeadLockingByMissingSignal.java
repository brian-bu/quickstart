package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//: concurrency/NotifyVsNotifyAll.java

class Blocker {
	void waitingCall() {
		synchronized (this) {
			try {
				// TIJ中文第四版706页所讲的错失信号
				// 避免错失信号的解决方案就是：防止在在someCondition变量上产生竞争条件
				// 具体做法就是把while包裹在同步块内，
				while (!Thread.interrupted()) {
					wait();
					System.out.println(Thread.currentThread() + " ");
				}
			} catch (InterruptedException e) {
				// OK to exit this way
			}
		}
	}

	synchronized void prod() {
		notify();
	}

	synchronized void prodAll() {
		notifyAll();
	}
}

class DeadLockingBlocker {

	void waitingCall() {
		while (!Thread.interrupted()) {
			synchronized (this) {
				try {
					wait();
					System.out.println(Thread.currentThread() + " ");
				} catch (InterruptedException e) {
					// OK to exit this way
				}
			}
		}
	}

	synchronized void prod() {
		notify();
	}

	synchronized void prodAll() {
		notifyAll();
	}
}

class SampleTask implements Runnable {
//	static Blocker blocker = new Blocker();
	static DeadLockingBlocker blocker = new DeadLockingBlocker();
	public void run() {
		blocker.waitingCall();
	}
}

public class DeadLockingByMissingSignal {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SampleTask());
		}
		SampleTask.blocker.prodAll();
		exec.shutdownNow(); // Interrupt all tasks
	}
}