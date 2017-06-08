package net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify;

import java.util.Timer;
import java.util.TimerTask;
//: concurrency/AtomicityTest.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * ��������ͨ��AtomicInteger�Է��߳���İ�װȡ������synchronized�ؼ��ֽ����̰߳�ȫͬ���Ļ���
 * ���ǻ���ͨ��Ҫ�������Ļ��Ƹ���ȫһЩ��Ҫô��synchronized��Ҫô����ʽ��Lock��
 * Effective Java�Ա���Ҳ���ἰ���йػ���ʧ�ܺͰ�ȫ��ʧ��
 * ��ȫ��ʧ�ܣ��߳�A���߳�B��ȡĳ�����ֵ��д����ֵ�ڼ��ȡ����
 * ����ʧ�ܼ���
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.StopThreadByWhileLoopWithVolatile
 *
 */
public class UnsafeThreadPackagedByAtomicIntegerWithoutSync implements Runnable {
	// ����AtomicInteger�Ĺ�������i����ʼֵΪ0�������Ͳ���Ҫ������������������ж�����߳�ά��
	// ����ֻ����������AtomicInteger�ѹ���i�����в�������װ����ʹ��ﵽԭ���Ե�Ҫ��
	// ����AtomicInteger���б���i������������AtomicInteger������Ϊi����ʼֵ
	// ����AtomicInteger��get��������i��getter��ֵ�������ֵ��¶�����
	// ����AtomicInteger��addAndGet��getAndIncrement�ȷ�����ɲ�ͬ�����ԭ��������
	// private int i = 0;
	private AtomicInteger i = new AtomicInteger(0);

	// return i��ԭ���Բ���
	public int getValue() {
		return i.get();
	}

	// ����javap������Ϳ��Է���i++ʵ������һ��getfield��һ��putfield��ͬ���ú�Ľ���������ⲻ��һ��ԭ���ԵĲ���
	// �����i++����������ί�и�AtomicInteger��ɿ��Ա�֤������������ԭ���Բ���������ʡȥ���߳�ά����
	private void evenIncrement() {
		i.addAndGet(2);
		System.out.println("Output the value of i::" + i.get());
	}

	public void run() {
		while (true)
			evenIncrement();
	}

	public static void main(String[] args) {
		// ����������򾭸�װ�Ѿ�����̰߳�ȫ���࣬���Ի�һֱ���ż������ʧ�ܣ����Բ���ֹͣ
		// ����������һ������������5���������У�Ҳ���Կ����Ƕ�Timer���API��ʵ��
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
