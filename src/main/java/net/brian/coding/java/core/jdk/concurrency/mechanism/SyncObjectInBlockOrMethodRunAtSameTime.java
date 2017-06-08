package net.brian.coding.java.core.jdk.concurrency.mechanism;
/**
 * 
 * ͬ��������ͬ���������໥�����ģ����Ǹ����в�ͬ�Ķ���������������ַ�ʽ��ͬʱ���е�ʱ�򲻻��������
 *
 */
class DualSynch {
	private Object syncObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObjectInBlockOrMethodRunAtSameTime {

	public static void main(String[] args) {
		// �����final��֤����������ǲ��ɱ�ģ�������֤�˲�ͬ���̵߳��÷�����ʱ��ʹ�õĶ���ͬһ������
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}
}