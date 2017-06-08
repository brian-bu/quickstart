package net.brian.coding.java.core.jdk.concurrency.mechanism.exception.interrupting;

//: concurrency/Interrupting2.java
// Interrupting a task blocked with a ReentrantLock.
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		// ��ȡ�����������������Lock�����ҴӲ��ͷ������
		lock.lock();
	}

	public void f() {
		try {
			// This will never be available to a second task
			lock.lockInterruptibly(); // Special call
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			// ����Ҫ���뵽���������л����Ѿ������������ڲ�ʱ�����������interrupt���������Ͳ�����ǰ�ж�
			// ������lockһֱ���������ձ�interrupt������ϣ���һ������Ͼͻ�������catch����
			// ע��interrupt�����������׳��쳣�������������е��̵߳���������������쳣
			// ���±����׳��쳣�����һ�ַ�ʽ���������ŵ��˳��쳣
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();

	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		// ����BlockedMutex��������һֱ���Ŷ��󲻷ţ���������ĵ��û�һֱ������ȥ
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
		// �������Ϊʲô��blocked.f()�ȷ���������Ϊblocked����һֱ�����ţ��޷�����ִ��
		// �����ʱ��ִ�е����interrupt�ж���BlockedMutex������������ʹ�������������ȥ
		// ע�͵���佫���³���һֱ��������
		t.interrupt();
	}
}