package net.brian.coding.java.core.jdk.concurrency.mechanism.scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
	static {
		try {
			System.setOut(new PrintStream(new File("Y:\\log\\tij\\SimplePriorities.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private int countDown = 5;
	@SuppressWarnings("unused")
	private volatile double d; // No optimization
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	public String toString() {
		// ��ֵͬ��countDown���Է����������ȼ��ߵ��Ǹ��߳�����ִ����
		// ��������ִ����countDownΪ5��Ȼ����ִ��4
		return Thread.currentThread() + ": " + countDown;
	}

	public void run() {
		// ͨ��setPriority�����߳����ȼ���ͨ��getPriority��ȡ�̵߳����ȼ�
		// �߳�ֻ�����������˲��漰�����ȼ��ĸ�����ͨ���ı����ȼ�����䶼��д��run�����ʼ
		Thread.currentThread().setPriority(priority);
		while (true) {
			// An expensive, interruptable operation:
			for (int i = 1; i < 10000000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 10000 == 0) {
					// ÿ����1000����Ϊ0��Ҳ������1000��������ʱ�������߳������ò�����ʹ��ǰ���̻߳�ûִ����
					// ��ʵ��ÿ���̶߳�Ҫ����99�β�������ִ������������ ֻҪ�̵߳�ִ��ʱ���㹻�����ò����߳��㹻�࣬�ͻ���Խ��Խ���δִ������߳̽�������״̬
					// �����ʱ���̵߳��Ⱦͻ�����ִ�����ȼ��ϸߵ��߳�
					/**
					 * Effective Java 2th by Joshua Bloch
					 * 
					 * item72: Don��t depend on the thread scheduler
					 * 
					 * Thread.yield()Ψһ����;�����ڲ����ڼ���Ϊ���ӳ���Ĳ����ԣ���java�淶��yield��������ʵ���Թ���
					 * ֻ������Ȩ���ظ����ĵ����ߣ����Ӧ��ʹ��Thread.sleep(1)����yield��ǧ��Ҫ��Thread.sleep(0)��������������
					 * 
					 * ��Ҫ�ó�����ȷ���������̵߳��������������õ���Ӧ�ó��򽫼Ȳ���׳Ҳ�����п���ֲ��
					 * ��Ϊ���ۣ�Ҳ��Ҫ��yield���������߳����ȼ�
					 * �߳����ȼ���ȷӦ��Ӧ�������һ���Ѿ��ܹ����������ĳ���ķ���������������������������ԭ�����Ͳ���ȷ�ĳ���
					 */
					Thread.yield();
				}
			}
			// ���е�this���ǵ��õ�ǰrun�������߳�Thread����toString()��������д�����������ӡ�������̶߳������Ϣ
			// ��TIJ���е����˳��һ�£���Ϊ�����漰��ÿ������ʱ���̵߳ĵ������Բ�ͬ�߳�ִ��˳��һ��
			System.out.println("Thread object:: " + this);
			if (--countDown == 0){
				// ���û�����return��whileѭ������һֱִ����ȥֱ���������������Ĵ���ʽ��ִ��5��whileѭ��Ȼ���˳�
				return;
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		// һ������̷߳ŵ��̳߳������̨������̱߳�Ŵ�1��5
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
			// �������Զ����main������������ǵ�ǰ���̣߳�����ǰ���߳̾���main����
			// System.out.println("Creating thread:: " + Thread.currentThread().getName());
		}
		// �������̣߳�����̨������̱߳����6����Ȼ�������ӵģ���������ǰ����̵߳�ִ��ʱ���㹻��
		// ���Եȵ�ִ�е������̵߳�ʱ��ǰ���û��һ��ִ����ģ���ʱ�������߳��������ȼ��ϸߣ����������̶߳������ò�ʱ����ִ��
		// ��Ҳ˵������ִ��execute������ͬʱ�������Ǽ�������ִ�еģ����һ�����ͨ�������execute����������̳߳ؼ���������߳�
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}