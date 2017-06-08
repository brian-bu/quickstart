package net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item68: Prefer executors and tasks to threads
 * 
 * ���ڡ�Prefer executors and tasks to threads����
 * a.����executorsָ����Executors�¶����һЩAPI
 * ���磬Executors.newSingleThreadExecutor()������һ�̵߳��̳߳أ����⻹�У�
 * Executors.newCachedThreadPool()����ΪС����������ط�����ʹ�ã������ص��Ǵ���һ�����̻߳������̳߳���
 * �����������̳߳��У����ύ������û���ųɶ��У�����ֱ�ӽ����߳�ִ�У����û���߳̿����򴴽��µ��߳�
 * ���ڴ��ط������ͻ���㣬���ط��������ʺ�����Executors.newFixedThreadPool()���ṩ�̶��߳���Ŀ���̳߳�
 * b.����tasks��ָ��Runnable��Callable�����ߺ����ƣ�����Callable��������������з���ֵ������
 * @see net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask.CallableDemo
 *
 * ����Executors��ܿ�Executors��Դ�룬�������ڲ���ͨ����������LinkedBlockingQueueʵ�ֵģ����ڹ������е����������
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify.ProducerConsumerByWaitNotify
 *
 * ��֮������Ӧ�þ�����ȥ��д�Լ��Ĺ������ж���ʹ��Executors��ܣ�����Ҳ������Ҫȥʹ��Thread�����߳�
 * ֮ǰ������Thread�������ǹ�����Ԫ������ִ�л��ơ�
 * ���ڣ�Thread����Ϊִ�л��ƣ�Ҳ���ǿ��Ը�Thread��Callable����Runnable���͵Ĳ�����Ϊ�̵߳�ִ�л���
 * ���Ƕ��ڹ�����Ԫ���Ѿ������Ƽ�ʹ��Thread������ʹ������Ҳ���ǡ�Prefer executors and tasks to threads���е�tasks
 *
 */
public class ThreadAndRunnableDemo {
	public static void main(String[] args) {
		// ����Thread�̳������ɵĶ����õ���Thread�Ĺ�����������������init�������Դ����߳�
		// ���ھ���Thread�����Կ���ֱ�ӵ���start����������ȱ������Ҫֱ�Ӽ̳�Thread
		ThreadDemo threadDemo = new ThreadDemo();
		// ���try-catch��Ҳ����ͬ���裬���뽫��Զ�����ߵ�catch����
		try{
			threadDemo.start();
		} catch (RuntimeException rte) {
			System.err.println("Hello Exception!");
		}
		
		// ����Runnable�����߳���ʵ����Ҫ�õ�Thread��Ĺ�������������β����ټ̳�Thread
		RunnableDemo runnableDemo = new RunnableDemo();
		// ����������ʽ������runһ���̣߳���Runnableʵ���ഴ���̵߳����ֲ�ͬ��ʽ
		// ���������������try-catch��������Զ����ִ��catch�����������ֱ��������׳��쳣
		try{
			new Thread(runnableDemo).start();
		} catch (RuntimeException rte) {
			System.err.println("Hello Exception!");
		}
		try{
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(runnableDemo);
		} catch (RuntimeException rte) {
			System.err.println("Hello Exception!");
		}
		// ��Ҫͨ��Thread��setDefaultUncaughtExceptionHandler�����쳣���
		// �����������������һ��Thread.UncaughtExceptionHandler��ʵ������Ϊ����
		// �����½�һ���࣬Ҳ������������������һ����������
		// ���������룬�Ϳ�����ȷ�����쳣�ˣ�������д�ڷ���uncaughtException��
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
			@Override
			public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
				System.out.println("Hello Exception!");
			}});
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(runnableDemo);
	}
}
class RunnableDemo implements Runnable {
	@Override
	public void run() {
		throw new RuntimeException();
	}
}
class ThreadDemo extends Thread {
	@Override
	public void run() {
		throw new RuntimeException();
	}
}