package net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item69: Prefer concurrency utilities to wait and notify
 * 
 * û���������´�����ʹ��wait��notify�������ά��ʹ��wait��notify�Ĵ��룬���ȷ��ʼ�������ñ�׼��ģʽ��whileѭ���ڲ�����wait
 * wait()����֮����Ӧ����ѭ�����ã�����Ϊ���̻߳�ȡ�� CPU��ʼִ�е�ʱ�������������ܻ�û�����㣬�����ڴ���ǰ��ѭ����������Ƿ���������
 * Ҳ����whileѭ��ȷ���̵߳���wait�ȴ�֮ǰ����������ȵ���notify/notifyAll������notify����wait���ã����޷���֤���̻߳�ӵȴ�������
 * ���ڻ���wait��Ӧ������ʹ��notifyAll������notify
 */
// TIJ�����½���ϰ21��
public class WaitAndNotifyDemo {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		Runnable1 runnable1 = new Runnable1();
		pool.execute(runnable1);
		// ȷ��������Runnable1
		TimeUnit.SECONDS.sleep(5);
		Runnable2 runnable2 = new Runnable2(runnable1);
		pool.execute(runnable2);
		pool.shutdown();
	}
}

class Runnable1 implements Runnable {

	@Override
	public void run() {
		try {
			// ���Runnable1��Runnable2����Runnable1�Ķ�����Ϊ������ô��һ��Ҫȷ��Runnable1������
			// ��������Runnable1����1����ȷ��Runnable2�����У����ǾͿ���Runnable2�Ȼ������Ȼ��ִ������3�������
			// Ȼ���ͷ���֮��Runnable1�Ż�ø�����Ȼ�����ͬ����ִ��wait������������ʱ�Ѿ�û���߳̿���notify��
			// �߳�2�Ѿ�ִ����ϣ�������������һֱwait��ȥ����˲��ܼ򵥵���Ϊһ����Runnable1������
			// ����Ҫ��Runnable1��ʼ����֮�󣬵ȵ�����wait����ʹ�õ�ǰ��Runnable1�߳��ó�����������Runnable2���ܿ�ʼ����
			// TimeUnit.SECONDS.sleep(1);
			synchronized (this) {
				System.out.println("Runnable1 Current Thread:: " + Thread.currentThread().getName());
				System.out.println("Runnable1 this:: " + this);
				wait();
				System.out.println("Runnable1 awaked!");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

class Runnable2 implements Runnable {
	private Runnable1 runnable = null;

	public Runnable2(Runnable1 runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {
		synchronized (runnable) {
			try {
				System.out.println("Runnable2 Current Thread:: " + Thread.currentThread().getName());
				System.out.println("Runnable2 runnable:: " + runnable);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Waking up Runnable1...");
			runnable.notify();
		}
	}

}