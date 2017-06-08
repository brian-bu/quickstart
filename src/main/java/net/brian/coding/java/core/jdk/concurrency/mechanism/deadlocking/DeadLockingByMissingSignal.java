package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//: concurrency/NotifyVsNotifyAll.java

class Blocker {
	void waitingCall() {
		synchronized (this) {
			try {
				// TIJ���ĵ��İ�706ҳ�����Ĵ�ʧ�ź�
				// �����ʧ�źŵĽ���������ǣ���ֹ����someCondition�����ϲ�����������
				// �����������ǰ�while������ͬ�����ڣ�
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